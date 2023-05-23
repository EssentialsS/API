package org.essentialss.api.world.points.spawn;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.math.vector.Vector3d;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.function.Supplier;

@SuppressWarnings("allow-nullable")
public class SSpawnPointBuilder {

    private Supplier<Vector3d> point;
    private Collection<SSpawnType> spawnType = new LinkedHashSet<>();

    @Deprecated
    public @Nullable Vector3d point() {
        if (this.point == null) {
            return null;
        }
        return this.point.get();
    }

    public @Nullable Supplier<Vector3d> position() {
        return this.point;
    }

    @Deprecated
    public @NotNull SSpawnPointBuilder setPoint(@NotNull Vector3d point) {
        return setPosition(point);
    }

    public @NotNull SSpawnPointBuilder setPosition(Supplier<Vector3d> position) {
        this.point = position;
        return this;
    }

    public @NotNull SSpawnPointBuilder setPosition(@NotNull Vector3d position) {
        this.point = () -> position;
        return this;
    }

    public @NotNull SSpawnPointBuilder setSpawnTypes(@NotNull Collection<SSpawnType> spawnType) {
        this.spawnType = spawnType;
        return this;
    }

    public @NotNull SSpawnPointBuilder setSpawnTypes(@NotNull SSpawnType... spawnTypes) {
        this.spawnType = Arrays.asList(spawnTypes);
        return this;
    }

    public @NotNull Collection<SSpawnType> spawnTypes() {
        return this.spawnType;
    }
}
