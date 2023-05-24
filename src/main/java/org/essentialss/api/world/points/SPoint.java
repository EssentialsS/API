package org.essentialss.api.world.points;

import org.essentialss.api.world.SWorldData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.world.Location;
import org.spongepowered.math.vector.Vector3d;

import java.util.Optional;

public interface SPoint {

    @NotNull
    OfflineLocation location();

    @NotNull
    default Vector3d position() {
        return this.location().position();
    }

    @NotNull
    default Optional<Location<?, ?>> spongeLocation() {
        return this.location().world().map(world -> world.location(this.position()));
    }

    @NotNull
    default SWorldData worldData() {
        return this.location().worldData();
    }
}
