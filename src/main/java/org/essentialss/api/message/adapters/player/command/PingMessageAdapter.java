package org.essentialss.api.message.adapters.player.command;

import net.kyori.adventure.text.Component;
import org.essentialss.api.message.adapters.MessageAdapter;
import org.essentialss.api.player.data.SGeneralPlayerData;
import org.jetbrains.annotations.NotNull;

public interface PingMessageAdapter extends MessageAdapter {

    Component adaptMessage(@NotNull Component messageToAdapt, @NotNull SGeneralPlayerData player);

    default Component adaptMessage(@NotNull SGeneralPlayerData player) {
        return this.adaptMessage(this.unadaptedMessage(), player);
    }

}
