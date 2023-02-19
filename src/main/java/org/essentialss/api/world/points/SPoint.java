package org.essentialss.api.world.points;

import org.essentialss.api.world.SWorldData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.world.Location;
import org.spongepowered.math.vector.Vector3d;

import java.util.Optional;

public interface SPoint {

    @NotNull OfflineLocation location();

    default @NotNull SWorldData worldData() {
        return this.location().worldData();
    }

    default @NotNull Vector3d position() {
        return this.location().position();
    }

    default @NotNull Optional<Location<?, ?>> spongeLocation() {
        return this.location().world().map(world -> world.location(this.position()));
    }
}
