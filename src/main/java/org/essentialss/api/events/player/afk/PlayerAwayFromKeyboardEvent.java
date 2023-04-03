package org.essentialss.api.events.player.afk;

import org.essentialss.api.player.data.SGeneralPlayerData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.Event;

public interface PlayerAwayFromKeyboardEvent extends Event, Cancellable {

    @NotNull SGeneralPlayerData player();

}
