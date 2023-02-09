package org.essentialss.api.world.points.jail;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.math.vector.Vector3d;

@SuppressWarnings("allow-nullable")
public class SJailSpawnPointBuilder {

    private Vector3d point;
    private String jailName;

    public @Nullable Vector3d point() {
        return this.point;
    }

    public @NotNull SJailSpawnPointBuilder setPoint(@NotNull Vector3d point) {
        this.point = point;
        return this;
    }

    public @Nullable String jailName() {
        return this.jailName;
    }

    public @NotNull SJailSpawnPointBuilder setJailName(@NotNull String jailName) {
        this.jailName = jailName;
        return this;
    }
}
