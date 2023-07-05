package org.essentialss.api.events.player.teleport;

import org.essentialss.api.world.points.SPoint;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.world.Location;

public interface PlayerTeleportToPointEvent extends PlayerTeleportEvent, Cancellable {

    @Override
    @NotNull
    default Location<?, ?> newLocation() {
        return this.point().spongeLocation().orElseThrow(() -> new IllegalStateException("Could not world -> this shouldn't be possible"));
    }

    @NotNull
    SPoint point();
}
