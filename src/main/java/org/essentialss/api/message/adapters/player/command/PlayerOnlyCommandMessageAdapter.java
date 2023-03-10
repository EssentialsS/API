package org.essentialss.api.message.adapters.player.command;

import net.kyori.adventure.text.Component;
import org.essentialss.api.message.placeholder.MessageAdapter;
import org.jetbrains.annotations.NotNull;

public interface PlayerOnlyCommandMessageAdapter extends MessageAdapter {

    @NotNull Component adaptMessage(@NotNull Component message);

    default @NotNull Component adaptMessage() {
        return this.adaptMessage(this.defaultUnadaptedMessage());
    }
}
