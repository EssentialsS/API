package org.essentialss.api.world.points.jail;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.math.vector.Vector3d;

@SuppressWarnings("allow-nullable")
public class SJailSpawnPointBuilder {

    private String jailName;
    private Vector3d point;

    @Nullable
    public String jailName() {
        return this.jailName;
    }

    @Nullable
    public Vector3d point() {
        return this.point;
    }

    @NotNull
    public SJailSpawnPointBuilder setJailName(@NotNull String jailName) {
        this.jailName = jailName;
        return this;
    }

    @NotNull
    public SJailSpawnPointBuilder setPoint(@NotNull Vector3d point) {
        this.point = point;
        return this;
    }
}
