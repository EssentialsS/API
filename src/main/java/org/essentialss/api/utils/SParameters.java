package org.essentialss.api.utils;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.essentialss.api.EssentialsSAPI;
import org.essentialss.api.player.data.SGeneralPlayerData;
import org.essentialss.api.world.SWorldManager;
import org.essentialss.api.world.points.spawn.SSpawnType;
import org.essentialss.api.world.points.warp.SWarp;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandCompletion;
import org.spongepowered.api.command.parameter.CommandContext;
import org.spongepowered.api.command.parameter.Parameter;
import org.spongepowered.api.command.parameter.managed.ValueCompleter;
import org.spongepowered.api.service.permission.Subject;
import org.spongepowered.api.world.Locatable;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.client.ClientWorld;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SParameters {

    private static final int NO_INDEX_OF_MAGIC_VALUE = -1;

    private static ValueCompleter WORLD_COMPLETER = (context, currentInput) -> {
        if (Sponge.isServerAvailable()) {
            return Sponge
                    .server()
                    .worldManager()
                    .worlds()
                    .stream()
                    .map(world -> CommandCompletion.of(world.key().formatted()))
                    .collect(Collectors.toList());
        }
        return Collections.singletonList(CommandCompletion.of("this"));
    };

    private static <T extends Number> List<CommandCompletion> locationSuggestion(CommandContext context,
                                                                                 Function<Location<?, ?>, T> toNumber) {
        if (!(context.subject() instanceof Locatable)) {
            return Collections.emptyList();
        }
        return Collections.singletonList(
                CommandCompletion.of(toNumber.apply(((Locatable) context.subject()).location()).toString(),
                                     Component.text("current location")));
    }

    public static Parameter.Value.Builder<SSpawnType> spawnType() {
        return Parameter.enumValue(SSpawnType.class);
    }

    public static Parameter.Value.Builder<SGeneralPlayerData> onlinePlayer() {
        return Parameter.builder(SGeneralPlayerData.class)
                //TODO -> parser
                .completer((context, currentInput) -> {
                    Collection<SGeneralPlayerData> playerData;
                    if (Sponge.isServerAvailable()) {
                        playerData = Sponge
                                .server()
                                .onlinePlayers()
                                .stream()
                                .map(player -> EssentialsSAPI.get().playerManager().get().dataFor(player))
                                .collect(Collectors.toList());
                    } else if (Sponge.isClientAvailable()) {
                        playerData = Sponge
                                .client()
                                .player()
                                .map(player -> Collections.singletonList(
                                        EssentialsSAPI.get().playerManager().get().dataFor(player)))
                                .orElse(Collections.emptyList());
                    } else {
                        return Collections.emptyList();
                    }
                    return playerData
                            .stream()
                            .filter(player -> {
                                if (player.spongePlayer().name().toLowerCase().startsWith(currentInput.toLowerCase())) {
                                    return true;
                                }
                                return PlainTextComponentSerializer
                                        .plainText()
                                        .serialize(player.displayName())
                                        .toLowerCase()
                                        .startsWith(currentInput.toLowerCase());
                            })
                            .flatMap(playerData2 -> Stream.of(CommandCompletion.of(
                                                                      PlainTextComponentSerializer.plainText().serialize(playerData2.displayName()),
                                                                      playerData2.displayName()),
                                                              CommandCompletion.of(playerData2.spongePlayer().name())))
                            .collect(Collectors.toList());
                });
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

    public static Parameter.Value.Builder<Double> location(boolean blockLocation,
                                                           Function<Location<?, ?>, Double> function) {
        return Parameter.doubleNumber().completer((context, currentInput) -> {
            if (!(context.subject() instanceof Locatable)) {
                return Collections.emptyList();
            }
            Locatable locatable = (Locatable) context.subject();
            String number = blockLocation ? function.apply(locatable.location()).toString() : (
                    function.apply(locatable.location()).intValue() + "");
            return Collections.singletonList(CommandCompletion.of(number));
        });
    }

    public static Parameter.Value.Builder<String> commandParameter() {
        return commandParameter((cause, input) -> cause.subject(), (cause, input) -> cause.cause().audience());
    }

    public static Parameter.Value.Builder<String> commandParameter(BiFunction<CommandContext, String, Subject> subjectGetter,
                                                                   BiFunction<CommandContext, String, Audience> audienceGetter) {
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
                    .complete(subjectGetter.apply(context, currentInput), audienceGetter.apply(context, currentInput),
                              currentInput)
                    .stream()
                    .map(commandCompletion -> CommandCompletion.of(
                            finalCommandMinusLastInput + " " + commandCompletion.completion(),
                            commandCompletion.tooltip().orElse(null)))
                    .collect(Collectors.toList());
        });
    }
}
