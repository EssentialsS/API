package org.essentialss.api.world.points;

import org.essentialss.api.world.SWorldData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.world.Location;
import org.spongepowered.math.vector.Vector3d;

public interface SPoint {

    @NotNull SWorldData worldData();

    @NotNull Vector3d position();

    default @NotNull Location<?, ?> location() {
        return this.worldData().spongeWorld().location(this.position());
    }
}
