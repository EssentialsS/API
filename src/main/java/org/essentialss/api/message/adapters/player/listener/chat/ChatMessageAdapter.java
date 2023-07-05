package org.essentialss.api.message.adapters.player.listener.chat;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.essentialss.api.message.adapters.player.listener.chat.format.ChatFormat;
import org.essentialss.api.message.adapters.MessageAdapter;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;

import java.util.Collection;

public interface ChatMessageAdapter extends MessageAdapter.Enabled {

    Component adaptMessage(@NotNull Component unformatted, @NotNull ServerPlayer player, @NotNull Audience receiver, @NotNull Component message);

    default Component adaptMessage(@NotNull ServerPlayer player, @NotNull Audience receiver, @NotNull Component message) {
        return this.adaptMessage(this.unadaptedMessage(), player, receiver, message);
    }

    Component formatMessage(@NotNull ServerPlayer player, @NotNull Audience receiver, @NotNull Component message, @NotNull Component originalMessage);

    default Component formatMessage(@NotNull ServerPlayer player, @NotNull Audience receiver, @NotNull Component message) {
        return this.formatMessage(player, receiver, message, message);
    }

    Collection<ChatFormat> formatters();

    void register(@NotNull ChatFormat chatFormat);

    @Deprecated
    default boolean shouldUseComponentOverride() {
        return this.isEnabled();
    }

}
