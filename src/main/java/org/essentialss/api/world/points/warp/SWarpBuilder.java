package org.essentialss.api.world.points.warp;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.math.vector.Vector3d;

@SuppressWarnings("allow-nullable")
public class SWarpBuilder {

    private Vector3d point;
    private String name;

    public @Nullable Vector3d point() {
        return this.point;
    }

    public @NotNull SWarpBuilder setPoint(@NotNull Vector3d point) {
        this.point = point;
        return this;
    }

    public @Nullable String name() {
        return this.name;
    }

    public @NotNull SWarpBuilder setName(@NotNull String name) {
        this.name = name;
        return this;
    }
}
