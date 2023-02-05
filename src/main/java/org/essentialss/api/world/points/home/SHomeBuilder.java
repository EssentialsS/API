package org.essentialss.api.world.points.home;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.math.vector.Vector3d;

@SuppressWarnings("allow-nullable")
public class SHomeBuilder {

    private Vector3d point;
    private String home;

    public @Nullable Vector3d point() {
        return this.point;
    }

    public @NotNull SHomeBuilder point(@NotNull Vector3d point) {
        this.point = point;
        return this;
    }

    public @Nullable String home() {
        return this.home;
    }

    public @NotNull SHomeBuilder home(@NotNull String home) {
        this.home = home;
        return this;
    }
}
