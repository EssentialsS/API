package org.essentialss.api.message.adapters.player.listener.afk;

import net.kyori.adventure.text.Component;
import org.essentialss.api.message.adapters.MessageAdapter;
import org.essentialss.api.player.data.SGeneralPlayerData;
import org.jetbrains.annotations.NotNull;

public interface AwayFromKeyboardForTooLongMessageAdapter extends MessageAdapter {

    @NotNull Component adaptMessage(@NotNull Component messageToAdapt, @NotNull SGeneralPlayerData player);

    default @NotNull Component adaptMessage(@NotNull SGeneralPlayerData player) {
        return this.adaptMessage(this.defaultUnadaptedMessage(), player);
    }

}
