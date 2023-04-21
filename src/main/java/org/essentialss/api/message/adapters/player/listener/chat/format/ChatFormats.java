package org.essentialss.api.message.adapters.player.listener.chat.format;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.essentialss.api.EssentialsSAPI;
import org.essentialss.api.message.placeholder.SPlaceHolders;
import org.essentialss.api.player.data.SGeneralPlayerData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;

public enum ChatFormats implements ChatFormat {
    AUDIENCE_NAME((player, playerData, audience, message, original) -> {
        if (audience instanceof Player) {
            SGeneralPlayerData audienceData = EssentialsSAPI.get().playerManager().get().dataFor((Player) audience);
            return SPlaceHolders.PLAYER_NAME.apply(message, audienceData);
        }
        if (audience.equals(Sponge.systemSubject())) {
            return SPlaceHolders.CONSOLE_NAME.apply(message, Sponge.systemSubject());
        }
        return message;
    }),
    AUDIENCE_NICKNAME((player, playerData, audience, message, original) -> {
        if (audience instanceof Player) {
            SGeneralPlayerData audienceData = EssentialsSAPI.get().playerManager().get().dataFor((Player) audience);
            return SPlaceHolders.PLAYER_NICKNAME.apply(message, audienceData);
        }
        if (audience.equals(Sponge.systemSubject())) {
            return SPlaceHolders.CONSOLE_NICKNAME.apply(message, Sponge.systemSubject());
        }
        return message;
    }),
    SENDER_NICKNAME((player, playerData, audience, message, original) -> SPlaceHolders.PLAYER_NICKNAME.copyWithTagType("my").apply(message, playerData)),
    SENDER_PING((player, playerData, audience, message, original) -> SPlaceHolders.PLAYER_PING.copyWithTagType("my").apply(message, player));

    private final ChatFormat wrapper;

    ChatFormats(ChatFormat wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public @NotNull Component adapt(@NotNull ServerPlayer player,
                                    @NotNull SGeneralPlayerData playerData,
                                    @NotNull Audience audience,
                                    @NotNull Component message,
                                    @NotNull Component originalMessage) {
        return this.wrapper.adapt(player, playerData, audience, message, originalMessage);
    }
}
