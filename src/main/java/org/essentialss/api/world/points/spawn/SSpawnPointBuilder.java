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
    @Nullable
    public Vector3d point() {
        if (null == this.point) {
            return null;
        }
        return this.point.get();
    }

    @Nullable
    public Supplier<Vector3d> position() {
        return this.point;
    }

    @Deprecated
    @NotNull
    public SSpawnPointBuilder setPoint(@NotNull Vector3d point) {
        return this.setPosition(point);
    }

    @NotNull
    public SSpawnPointBuilder setPosition(Supplier<Vector3d> position) {
        this.point = position;
        return this;
    }

    @NotNull
    public SSpawnPointBuilder setPosition(@NotNull Vector3d position) {
        this.point = () -> position;
        return this;
    }

    @NotNull
    public SSpawnPointBuilder setSpawnTypes(@NotNull Collection<SSpawnType> spawnType) {
        this.spawnType = spawnType;
        return this;
    }

    @NotNull
    public SSpawnPointBuilder setSpawnTypes(@NotNull SSpawnType... spawnTypes) {
        this.spawnType = Arrays.asList(spawnTypes);
        return this;
    }

    @NotNull
    public Collection<SSpawnType> spawnTypes() {
        return this.spawnType;
    }
}
