package org.essentialss.api.utils;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.essentialss.api.EssentialsSAPI;
import org.essentialss.api.config.value.ConfigValue;
import org.essentialss.api.config.value.ConfigValueWrapper;
import org.essentialss.api.message.adapters.MessageAdapter;
import org.essentialss.api.player.data.SGeneralPlayerData;
import org.essentialss.api.player.data.SGeneralUnloadedData;
import org.essentialss.api.utils.parameter.ParameterAdapter;
import org.essentialss.api.world.SWorldManager;
import org.essentialss.api.world.points.spawn.SSpawnType;
import org.essentialss.api.world.points.warp.SWarp;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandCompletion;
import org.spongepowered.api.command.exception.ArgumentParseException;
import org.spongepowered.api.command.parameter.CommandContext;
import org.spongepowered.api.command.parameter.Parameter;
import org.spongepowered.api.command.parameter.managed.ValueCompleter;
import org.spongepowered.api.command.parameter.managed.ValueParser;
import org.spongepowered.api.service.permission.Subject;
import org.spongepowered.api.world.Locatable;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.client.ClientWorld;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.function.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SParameters {

    private static final int NO_INDEX_OF_MAGIC_VALUE = -1;

    private static final ValueCompleter WORLD_COMPLETER = (context, currentInput) -> {
        if (Sponge.isServerAvailable()) {
            return Sponge.server().worldManager().worlds().stream().map(world -> CommandCompletion.of(world.key().formatted())).collect(Collectors.toList());
        }
        return Collections.singletonList(CommandCompletion.of("this"));
    };

    private static final ValueParser<String> IP_V4 = (parameterKey, reader, context) -> {
        String original = reader.parseString();

        String[] split = original.split(Pattern.quote("."));
        if (4 != split.length) {
            throw reader.createException(Component.text("Invalid IPV4. Too many or not enough dots"));
        }

        for (String splitNumber : split) {
            try {
                int number = Integer.parseInt(splitNumber);
                if (Constants.ZERO > number) {
                    throw reader.createException(Component.text("Invalid IPV4. '" + splitNumber + "' cannot be less then 0"));
                }
                if (Constants.UNSIGNED_BYTE_MAX < number) {
                    throw reader.createException(Component.text("Invalid IPV4. '" + splitNumber + "' cannot be greater than 255"));
                }
            } catch (NumberFormatException e) {
                throw reader.createException(Component.text("Invalid IPV4. '" + splitNumber + "' is not a number"));
            }
        }

        return Optional.of(original);
    };

    private static final ValueParser<String> IP_V6 = (parameterKey, reader, context) -> {
        String original = reader.parseString();
        Pattern ipv6Pattern = Pattern.compile("([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}", Pattern.CASE_INSENSITIVE);
        if (!ipv6Pattern.matcher(original).matches()) {
            throw reader.createException(Component.text("IPV6 is invalid"));
        }
        return Optional.of(original);
    };

    private static final ValueParser<String> URL = (parameterKey, reader, context) -> {
        String original = reader.parseString();
        try {
            new URI(original);
        } catch (URISyntaxException e) {
            throw reader.createException(Component.text(e.getMessage()));
        }
        return Optional.of(original);
    };

    private static final ValueCompleter HOSTNAME_COMPLETER = (context, currentInput) -> {
        if (!Sponge.isServerAvailable()) {
            return Collections.emptyList();
        }
        return Sponge
                .server()
                .onlinePlayers()
                .stream()
                .map(player -> player.connection().address().getHostName())
                .map(CommandCompletion::of)
                .collect(Collectors.toList());
    };

    private SParameters() {
        throw new RuntimeException("Should not create");
    }

    @SafeVarargs
    private static <T> ValueParser<T> combine(@NotNull ValueParser<T>... parsers) {
        return (parameterKey, reader, context) -> {
            ArgumentParseException exception = null;

            for (ValueParser<T> parser : parsers) {
                try {
                    return parser.parseValue(parameterKey, reader.immutable().mutable(), context);
                } catch (ArgumentParseException e) {
                    exception = e;
                }
            }
            if (null == exception) {
                return Optional.empty();
            }
            throw exception;
        };
    }

    public static Parameter.Value.Builder<String> commandParameter() {
        return commandParameter((cause, input) -> cause.subject(), (cause, input) -> cause.cause().audience());
    }

    public static Parameter.Value.Builder<String> commandParameter(@NotNull BiFunction<CommandContext, String, Subject> subjectGetter,
                                                                   @NotNull BiFunction<CommandContext, String, Audience> audienceGetter) {
        return Parameter.remainingJoinedStrings().completer((context, currentInput) -> {
            int lastSpace = currentInput.lastIndexOf(" ");

            String commandMinusLastInput = "";
            if (NO_INDEX_OF_MAGIC_VALUE != lastSpace) {
                commandMinusLastInput = currentInput.substring(0, lastSpace);
            }
            final String finalCommandMinusLastInput = commandMinusLastInput;
            return Sponge
                    .server()
                    .commandManager()
                    .complete(subjectGetter.apply(context, currentInput), audienceGetter.apply(context, currentInput), currentInput)
                    .stream()
                    .map(commandCompletion -> CommandCompletion.of(finalCommandMinusLastInput + " " + commandCompletion.completion(),
                                                                   commandCompletion.tooltip().orElse(null)))
                    .collect(Collectors.toList());
        });
    }

    public static Parameter.Value.Builder<ConfigValueWrapper> configValue(Supplier<Collection<ConfigValue<?>>> values) {
        return Parameter
                .builder(ConfigValueWrapper.class)
                .addParser((parameterKey, reader, context) -> {
                    String value = reader.parseUnquotedString();
                    Collection<ConfigValue<?>> configValues = values.get();
                    return configValues
                            .stream()
                            .filter(node -> Arrays.stream(node.nodes()).map(Object::toString).collect(Collectors.joining(".")).equalsIgnoreCase(value))
                            .findAny()
                            .map(ConfigValueWrapper::new);
                })
                .completer((context, currentInput) -> values
                        .get()
                        .stream()
                        .map(v -> Arrays.stream(v.nodes()).map(Object::toString).collect(Collectors.joining(".")))
                        .sorted(Comparator.naturalOrder())
                        .map(CommandCompletion::of)
                        .collect(Collectors.toList()));
    }

    public static Parameter.Value.Builder<String> hostname() {
        return Parameter.string().completer(HOSTNAME_COMPLETER).addParser(combine(IP_V4, IP_V6, URL));
    }

    public static Parameter.Value.Builder<Double> location(boolean blockLocation, @NotNull Function<Location<?, ?>, Double> function) {
        return Parameter.doubleNumber().completer((context, currentInput) -> {
            if (!(context.subject() instanceof Locatable)) {
                return Collections.emptyList();
            }
            Locatable locatable = (Locatable) context.subject();
            String number = blockLocation ? function.apply(locatable.location()).toString() : (function.apply(locatable.location()).intValue() + "");
            return Collections.singletonList(CommandCompletion.of(number));
        });
    }

    private static <T extends Number> List<CommandCompletion> locationSuggestion(@NotNull CommandContext context,
                                                                                 @NotNull Function<Location<?, ?>, T> toNumber) {
        if (!(context.subject() instanceof Locatable)) {
            return Collections.emptyList();
        }
        return Collections.singletonList(
                CommandCompletion.of(toNumber.apply(((Locatable) context.subject()).location()).toString(), Component.text("current location")));
    }

    public static Parameter.Value.Builder<MessageAdapter> messageAdapter() {
        return messageAdapter(() -> EssentialsSAPI.get().messageManager().get().adapters().all().collect(Collectors.toList()));
    }

    public static Parameter.Value.Builder<MessageAdapter> messageAdapter(@NotNull Supplier<Collection<MessageAdapter>> supplier) {
        Collection<MessageAdapter> adapters = supplier.get();
        return Parameter
                .builder(MessageAdapter.class)
                .addParser((parameterKey, reader, context) -> {
                    String value = reader.parseUnquotedString();
                    return adapters
                            .stream()
                            .filter(adapter -> Arrays
                                    .stream(adapter.configValue().nodes())
                                    .map(Object::toString)
                                    .collect(Collectors.joining("."))
                                    .equalsIgnoreCase(value))
                            .findAny();
                })
                .completer((context, currentInput) -> adapters
                        .stream()
                        .map(adapter -> Arrays.stream(adapter.configValue().nodes()).map(Object::toString).collect(Collectors.joining(".")))
                        .sorted(Comparator.naturalOrder())
                        .map(CommandCompletion::of)
                        .collect(Collectors.toList()));
    }

    private static ValueParser<SGeneralUnloadedData> nickNameParser(@NotNull Predicate<SGeneralUnloadedData> accept) {
        return nickNameParser(() -> EssentialsSAPI.get().playerManager().get().unloadedDataForAll(), accept);
    }

    private static <T extends SGeneralUnloadedData> ValueParser<T> nickNameParser(@NotNull Supplier<Collection<T>> players, Predicate<T> accept) {
        return (parameterKey, reader, context) -> {
            String input = reader.parseString();
            return players.get().stream().filter(player -> {
                if (player.playerName().equalsIgnoreCase(input)) {
                    return true;
                }
                Component displayName = player.displayName();
                String displayNamePlain = PlainTextComponentSerializer.plainText().serialize(displayName);

                return displayNamePlain.equalsIgnoreCase(input);
            }).filter(accept).findAny();

        };
    }

    private static ValueCompleter nicknameSuggestion(boolean nicknameOnly, @NotNull BiPredicate<CommandContext, SGeneralUnloadedData> accept) {
        return (context, currentInput) -> {
            Collection<SGeneralUnloadedData> playerData = EssentialsSAPI.get().playerManager().get().unloadedDataForAll();
            return playerData.stream().filter(player -> {
                if (player.hasSetDisplayName()) {
                    return PlainTextComponentSerializer.plainText().serialize(player.displayName()).toLowerCase().startsWith(currentInput.toLowerCase());
                }
                if (nicknameOnly) {
                    return false;
                }

                return player.playerName().toLowerCase().startsWith(currentInput.toLowerCase());
            }).filter(p -> accept.test(context, p)).flatMap(playerData2 -> {
                CommandCompletion completion = CommandCompletion.of(PlainTextComponentSerializer.plainText().serialize(playerData2.displayName()),
                                                                    playerData2.displayName());
                if (nicknameOnly) {
                    return Stream.of(completion);
                }
                if (!playerData2.hasSetDisplayName()) {
                    return Stream.of(completion);
                }
                return Stream.of(completion, CommandCompletion.of(playerData2.playerName()));
            }).collect(Collectors.toList());
        };
    }

    public static Parameter.Value.Builder<SGeneralUnloadedData> offlinePlayersNickname(boolean nicknameOnly, @NotNull Predicate<SGeneralUnloadedData> accept) {
        return offlinePlayersNickname(nicknameOnly, accept, (context, p) -> accept.test(p));
    }

    @SuppressWarnings("WeakerAccess")
    public static Parameter.Value.Builder<SGeneralUnloadedData> offlinePlayersNickname(boolean nicknameOnly,
                                                                                       @NotNull Predicate<SGeneralUnloadedData> accept,
                                                                                       @NotNull BiPredicate<CommandContext, SGeneralUnloadedData> acceptSuggestion) {
        return Parameter.builder(SGeneralUnloadedData.class).addParser(nickNameParser(accept)).completer(nicknameSuggestion(nicknameOnly, acceptSuggestion));
    }

    public static Parameter.Value.Builder<SGeneralPlayerData> onlinePlayer(@NotNull Predicate<SGeneralPlayerData> accept) {
        return onlinePlayer(accept, (context, data) -> accept.test(data));
    }

    public static Parameter.Value.Builder<SGeneralPlayerData> onlinePlayer(@NotNull Predicate<SGeneralPlayerData> accept,
                                                                           @NotNull BiPredicate<CommandContext, SGeneralPlayerData> acceptSuggestion) {
        return Parameter.builder(SGeneralPlayerData.class).completer(nicknameSuggestion(false, (context, unloaded) -> {
            if (!(unloaded instanceof SGeneralPlayerData)) {
                return false;
            }
            return acceptSuggestion.test(context, (SGeneralPlayerData) unloaded);
        })).addParser(nickNameParser(() -> {
            if (Sponge.isServerAvailable()) {
                return Sponge
                        .server()
                        .onlinePlayers()
                        .stream()
                        .map(player -> EssentialsSAPI.get().playerManager().get().dataFor(player))
                        .collect(Collectors.toList());
            }
            return Sponge
                    .client()
                    .player()
                    .map(player -> Collections.singletonList(EssentialsSAPI.get().playerManager().get().dataFor(player)))
                    .orElseGet(Collections::emptyList);
        }, accept));
    }

    public static <T> Optional<Parameter.Value.Builder<T>> parameterFor(@NotNull Class<?> type) {
        return EssentialsSAPI
                .get()
                .parameterAdapters()
                .stream()
                .filter(adapter -> Arrays.stream(adapter.types()).anyMatch(type2 -> type2.isAssignableFrom(type)))
                .findAny()
                .map(ParameterAdapter::builder);
    }

    public static Parameter.Value.Builder<SSpawnType> spawnType() {
        return Parameter.enumValue(SSpawnType.class);
    }

    public static Parameter.Value.Builder<SWarp> warp() {
        return Parameter.builder(SWarp.class).addParser((parameterKey, reader, context) -> {
            SWorldManager worldManager = EssentialsSAPI.get().worldManager().get();
            String currentInput = reader.parseString();
            if (Sponge.isServerAvailable()) {
                return Sponge
                        .server()
                        .worldManager()
                        .worlds()
                        .stream()
                        .flatMap(spongeWorld -> worldManager.dataFor(spongeWorld).warps().stream())
                        .filter(warp -> warp.identifier().equalsIgnoreCase(currentInput))
                        .findAny();
            }
            Optional<ClientWorld> opWorld = Sponge.client().world();
            if (!opWorld.isPresent()) {
                throw new RuntimeException("Client is used but could not find world");
            }
            return worldManager
                    .dataFor(opWorld.get())
                    .warps()
                    .stream()
                    .filter(warp -> warp.identifier().toLowerCase().startsWith(currentInput.toLowerCase()))
                    .findAny();
        }).completer((context, currentInput) -> {
            SWorldManager worldManager = EssentialsSAPI.get().worldManager().get();
            if (Sponge.isServerAvailable()) {
                return Sponge
                        .server()
                        .worldManager()
                        .worlds()
                        .stream()
                        .flatMap(spongeWorld -> worldManager.dataFor(spongeWorld).warps().stream())
                        .filter(warp -> warp.identifier().toLowerCase().startsWith(currentInput.toLowerCase()))
                        .map(warp -> CommandCompletion.of(warp.identifier()))
                        .sorted(Comparator.comparing(CommandCompletion::completion))
                        .collect(Collectors.toList());
            }
            Optional<ClientWorld> opWorld = Sponge.client().world();
            if (!opWorld.isPresent()) {
                throw new RuntimeException("Client is used but could not find world");
            }
            return worldManager
                    .dataFor(opWorld.get())
                    .warps()
                    .stream()
                    .filter(warp -> warp.identifier().toLowerCase().startsWith(currentInput.toLowerCase()))
                    .map(warp -> CommandCompletion.of(warp.identifier()))
                    .sorted(Comparator.comparing(CommandCompletion::completion))
                    .collect(Collectors.toList());
        });
    }
}
