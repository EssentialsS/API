package org.essentialss.api.world;

import org.essentialss.api.config.Serializable;
import org.essentialss.api.utils.CrossSpongePlatformUtils;
import org.essentialss.api.utils.arrays.impl.SingleUnmodifiableCollection;
import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.essentialss.api.utils.identifier.StringIdentifier;
import org.essentialss.api.world.points.OfflineLocation;
import org.essentialss.api.world.points.SPoint;
import org.essentialss.api.world.points.jail.SJailSpawnPoint;
import org.essentialss.api.world.points.jail.SJailSpawnPointBuilder;
import org.essentialss.api.world.points.spawn.SSpawnPoint;
import org.essentialss.api.world.points.spawn.SSpawnPointBuilder;
import org.essentialss.api.world.points.spawn.SSpawnType;
import org.essentialss.api.world.points.warp.SWarp;
import org.essentialss.api.world.points.warp.SWarpBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.event.Cause;
import org.spongepowered.api.world.World;
import org.spongepowered.math.vector.Vector3d;

import java.util.Optional;
import java.util.stream.Collectors;

public interface SWorldData extends StringIdentifier, Serializable {

    void clearPoints();

    boolean deregister(@NotNull SSpawnPoint builder, boolean runEvent, @Nullable Cause cause);

    boolean deregister(@NotNull SWarp builder, boolean runEvent, @Nullable Cause cause);

    boolean deregister(@NotNull SJailSpawnPoint builder, boolean runEvent, @Nullable Cause cause);

    default boolean deregister(@NotNull SSpawnPoint builder, @NotNull Cause cause) {
        return this.deregister(builder, true, cause);
    }

    default boolean deregister(@NotNull SWarp builder, @NotNull Cause cause) {
        return this.deregister(builder, true, cause);
    }

    default boolean deregister(@NotNull SJailSpawnPoint builder, @NotNull Cause cause) {
        return this.deregister(builder, true, cause);
    }

    default boolean deregister(@NotNull SSpawnPoint builder) {
        return this.deregister(builder, true, CrossSpongePlatformUtils.spongeEngine().causeStackManager().currentCause());
    }

    default boolean deregister(@NotNull SWarp builder) {
        return this.deregister(builder, true, CrossSpongePlatformUtils.spongeEngine().causeStackManager().currentCause());
    }

    default boolean deregister(@NotNull SJailSpawnPoint builder) {
        return this.deregister(builder, true, CrossSpongePlatformUtils.spongeEngine().causeStackManager().currentCause());
    }

    boolean isWorld(@NotNull World<?, ?> world);

    default @NotNull Optional<SJailSpawnPoint> jailPosition(@NotNull String identifier) {
        return this.jailPositions().parallelStream().filter(jail -> jail.identifier().equalsIgnoreCase(identifier)).findAny();
    }

    default @NotNull Optional<SJailSpawnPoint> jailPosition(@NotNull Vector3d blockPosition) {
        double distance = Integer.MAX_VALUE;
        SJailSpawnPoint point = null;
        for (SJailSpawnPoint jail : this.jailPositions()) {
            double jailDistance = jail.position().distanceSquared(blockPosition);
            if (jailDistance < distance) {
                point = jail;
                distance = jailDistance;
            }
        }
        return Optional.ofNullable(point);
    }

    default @NotNull UnmodifiableCollection<SJailSpawnPoint> jailPositions() {
        return new SingleUnmodifiableCollection<>(this.pointsOf(SJailSpawnPoint.class));
    }

    default OfflineLocation offlineLocation(@NotNull Vector3d position) {
        return new OfflineLocation(this, position);
    }

    @NotNull UnmodifiableCollection<SPoint> points();

    default <P extends SPoint> @NotNull UnmodifiableCollection<P> pointsOf(@NotNull Class<P> type) {
        return new SingleUnmodifiableCollection<>(this.points().parallelStream().filter(type::isInstance).map(point -> (P) point).collect(Collectors.toSet()));
    }

    Optional<SSpawnPoint> register(@NotNull SSpawnPointBuilder builder, boolean runEvent, @Nullable Cause cause);

    Optional<SWarp> register(@NotNull SWarpBuilder builder, boolean runEvent, @Nullable Cause cause);

    Optional<SJailSpawnPoint> register(@NotNull SJailSpawnPointBuilder builder, boolean runEvent, @Nullable Cause cause);

    default Optional<SSpawnPoint> register(@NotNull SSpawnPointBuilder builder, @NotNull Cause cause) {
        return this.register(builder, true, cause);
    }

    default Optional<SWarp> register(@NotNull SWarpBuilder builder, @NotNull Cause cause) {
        return this.register(builder, true, cause);
    }

    default Optional<SJailSpawnPoint> register(@NotNull SJailSpawnPointBuilder builder, @NotNull Cause cause) {
        return this.register(builder, true, cause);
    }

    default Optional<SSpawnPoint> register(@NotNull SSpawnPointBuilder builder) {
        return this.register(builder, true, CrossSpongePlatformUtils.spongeEngine().causeStackManager().currentCause());
    }

    default Optional<SWarp> register(@NotNull SWarpBuilder builder) {
        return this.register(builder, true, CrossSpongePlatformUtils.spongeEngine().causeStackManager().currentCause());
    }

    default Optional<SJailSpawnPoint> register(@NotNull SJailSpawnPointBuilder builder) {
        return this.register(builder, true, CrossSpongePlatformUtils.spongeEngine().causeStackManager().currentCause());
    }

    default @NotNull SSpawnPoint spawnPoint(@NotNull Vector3d blockPosition) {
        return this.spawnPoint(blockPosition, true);
    }

    default @NotNull SSpawnPoint spawnPoint(@NotNull Vector3d blockPosition, boolean ignoreFirstLogin) {
        double distance = Integer.MAX_VALUE;
        SSpawnPoint point = null;
        for (SSpawnPoint spawn : this.spawnPoints()) {
            if (ignoreFirstLogin && (1 == spawn.types().size()) && spawn.types().contains(SSpawnType.FIRST_LOGIN)) {
                continue;
            }
            double jailDistance = spawn.position().distanceSquared(blockPosition);
            if (jailDistance < distance) {
                point = spawn;
                distance = jailDistance;
            }
        }
        if (null == point) {
            throw new IllegalStateException("World has no spawn points. This should not be possible");
        }
        return point;
    }

    default @NotNull UnmodifiableCollection<SSpawnPoint> spawnPoints() {
        return new SingleUnmodifiableCollection<>(this.pointsOf(SSpawnPoint.class));
    }

    @NotNull Optional<World<?, ?>> spongeWorld();

    default Optional<SWarp> warp(@NotNull String identifier) {
        return this.warps().parallelStream().filter(warp -> warp.identifier().equalsIgnoreCase(identifier)).findAny();
    }

    default @NotNull UnmodifiableCollection<SWarp> warps() {
        return new SingleUnmodifiableCollection<>(this.pointsOf(SWarp.class));
    }

    Optional<ResourceKey> worldKey();
}
