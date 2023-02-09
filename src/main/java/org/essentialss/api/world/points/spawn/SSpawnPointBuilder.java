package org.essentialss.api.world.points.spawn;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.math.vector.Vector3d;

import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings("allow-nullable")
public class SSpawnPointBuilder {

    private Vector3d point;
    private Collection<SSpawnType> spawnType;

    public @Nullable Vector3d point() {
        return this.point;
    }

    public @NotNull SSpawnPointBuilder setPoint(@NotNull Vector3d point) {
        this.point = point;
        return this;
    }

    public @Nullable Collection<SSpawnType> spawnTypes() {
        return this.spawnType;
    }

    public @NotNull SSpawnPointBuilder setSpawnTypes(@NotNull Collection<SSpawnType> spawnType) {
        this.spawnType = spawnType;
        return this;
    }

    public @NotNull SSpawnPointBuilder setSpawnTypes(@NotNull SSpawnType... spawnTypes) {
        this.spawnType = Arrays.asList(spawnTypes);
        return this;
    }
}
