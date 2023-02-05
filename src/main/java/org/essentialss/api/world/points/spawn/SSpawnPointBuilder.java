package org.essentialss.api.world.points.spawn;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.math.vector.Vector3d;

@SuppressWarnings("allow-nullable")
public class SSpawnPointBuilder {

    private Vector3d point;
    private SSpawnType spawnType;

    public @Nullable Vector3d point() {
        return this.point;
    }

    public @NotNull SSpawnPointBuilder point(@NotNull Vector3d point) {
        this.point = point;
        return this;
    }

    public @Nullable SSpawnType spawnType() {
        return this.spawnType;
    }

    public @NotNull SSpawnPointBuilder spawnType(@NotNull SSpawnType spawnType) {
        this.spawnType = spawnType;
        return this;
    }
}
