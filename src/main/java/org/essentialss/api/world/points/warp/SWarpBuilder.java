package org.essentialss.api.world.points.warp;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.math.vector.Vector3d;

@SuppressWarnings("allow-nullable")
public class SWarpBuilder {

    private String name;
    private Vector3d point;

    @Nullable
    public String name() {
        return this.name;
    }

    @Nullable
    public Vector3d point() {
        return this.point;
    }

    @NotNull
    public SWarpBuilder setName(@NotNull String name) {
        this.name = name;
        return this;
    }

    @NotNull
    public SWarpBuilder setPoint(@NotNull Vector3d point) {
        this.point = point;
        return this;
    }
}
