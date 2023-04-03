package org.essentialss.api.events.player.afk;

import net.kyori.adventure.text.Component;
import org.essentialss.api.player.data.SGeneralPlayerData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.Event;

public interface PlayerKickedForIdlingEvent extends Event, Cancellable {

    @NotNull Component kickMessage();

    @NotNull Component originalKickMessage();

    @NotNull SGeneralPlayerData player();

    void setKickMessage(@NotNull Component kickMessage);

}
