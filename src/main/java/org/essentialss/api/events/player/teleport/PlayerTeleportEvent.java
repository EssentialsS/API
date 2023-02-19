package org.essentialss.api.events.player.teleport;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.world.Location;

public interface PlayerTeleportEvent extends Event {

    interface Mutable extends PlayerTeleportEvent, Cancellable {

        void setNewLocation(@NotNull Location<?, ?> location);

    }

    @NotNull Player player();

    @NotNull Location<?, ?> currentLocation();

    @NotNull Location<?, ?> newLocation();


}
