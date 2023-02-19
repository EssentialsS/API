package org.essentialss.api.events.player.teleport;

import org.essentialss.api.world.points.SPoint;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.world.Location;

public interface PlayerTeleportToPointEvent extends PlayerTeleportEvent, Cancellable {

    @NotNull SPoint point();

    @Override
    default @NotNull Location<?, ?> newLocation() {
        return this
                .point()
                .spongeLocation()
                .orElseThrow(() -> new IllegalStateException("Could not world -> this shouldn't be possible"));
    }
}
