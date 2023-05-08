package org.essentialss.api.message.adapters.vanilla.player;

import net.kyori.adventure.text.Component;
import org.essentialss.api.message.adapters.vanilla.VanillaMessageAdapter;
import org.essentialss.api.player.data.SGeneralPlayerData;
import org.jetbrains.annotations.NotNull;

public interface PlayerJoinMessageAdapter extends VanillaMessageAdapter {

    @NotNull Component adaptMessage(@NotNull Component messageToAdapt, @NotNull SGeneralPlayerData playerData);

    default @NotNull Component adaptMessage(@NotNull SGeneralPlayerData playerData) {
        return this.adaptMessage(this.defaultUnadaptedMessage(), playerData);
    }
}
