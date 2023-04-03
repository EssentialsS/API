package org.essentialss.api.message.adapters.player.command;

import net.kyori.adventure.text.Component;
import org.essentialss.api.message.adapters.MessageAdapter;
import org.essentialss.api.player.data.SGeneralUnloadedData;
import org.jetbrains.annotations.NotNull;

public interface WhoIsMessageAdapter extends MessageAdapter {

    Component adaptMessage(@NotNull Component messageToAdapt, @NotNull SGeneralUnloadedData player);

    default Component adaptMessage(@NotNull SGeneralUnloadedData player) {
        return this.adaptMessage(this.unadaptedMessage(), player);
    }

}
