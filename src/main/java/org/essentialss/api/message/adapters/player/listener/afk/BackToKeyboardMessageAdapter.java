package org.essentialss.api.message.adapters.player.listener.afk;

import net.kyori.adventure.text.Component;
import org.essentialss.api.message.placeholder.MessageAdapter;
import org.essentialss.api.player.data.SGeneralPlayerData;
import org.jetbrains.annotations.NotNull;

public interface BackToKeyboardMessageAdapter extends MessageAdapter {

    @NotNull Component adaptMessage(@NotNull Component messageToAdapt, @NotNull SGeneralPlayerData backPlayer);

    default @NotNull Component adaptMessage(@NotNull SGeneralPlayerData backPlayer) {
        return this.adaptMessage(this.defaultUnadaptedMessage(), backPlayer);
    }

}
