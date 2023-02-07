package org.essentialss.api.world;

import org.essentialss.api.utils.CrossSpongePlatformUtils;
import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.essentialss.api.utils.identifier.StringIdentifier;
import org.essentialss.api.world.points.SPoint;
import org.essentialss.api.world.points.home.SHomeBuilder;
import org.essentialss.api.world.points.jail.SJailSpawnPoint;
import org.essentialss.api.world.points.jail.SJailSpawnPointBuilder;
import org.essentialss.api.world.points.spawn.SSpawnPoint;
import org.essentialss.api.world.points.spawn.SSpawnPointBuilder;
import org.essentialss.api.world.points.spawn.SSpawnType;
import org.essentialss.api.world.points.warp.SWarp;
import org.essentialss.api.world.points.warp.SWarpBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.event.Cause;
import org.spongepowered.api.service.context.Context;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.server.ServerWorld;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.math.vector.Vector3d;

import java.util.Optional;
import java.util.stream.Collectors;

public interface SWorldData extends StringIdentifier {

    @NotNull World<?, ?> spongeWorld();

    void reloadFromConfig() throws ConfigurateException;

    void clearPoints();

    void saveToConfig() throws ConfigurateException;

    @NotNull UnmodifiableCollection<SPoint> points();

    boolean register(@NotNull SHomeBuilder builder, boolean runEvent, @Nullable Cause cause);

    boolean register(@NotNull SSpawnPointBuilder builder, boolean runEvent, @Nullable Cause cause);

    boolean register(@NotNull SWarpBuilder builder, boolean runEvent, @Nullable Cause cause);

    boolean register(@NotNull SJailSpawnPointBuilder builder, boolean runEvent, @Nullable Cause cause);

    default boolean register(@NotNull SHomeBuilder builder, @NotNull Cause cause) {
        return this.register(builder, true, cause);
    }

    default boolean register(@NotNull SSpawnPointBuilder builder, @NotNull Cause cause) {
        return this.register(builder, true, cause);
    }

    default boolean register(@NotNull SWarpBuilder builder, @NotNull Cause cause) {
        return this.register(builder, true, cause);
    }

    default boolean register(@NotNull SJailSpawnPointBuilder builder, @NotNull Cause cause) {
        return this.register(builder, true, cause);
    }

    default boolean register(@NotNull SHomeBuilder builder) {
        return this.register(builder, true, CrossSpongePlatformUtils.spongeEngine().causeStackManager().currentCause());
    }

    default boolean register(@NotNull SSpawnPointBuilder builder) {
        return this.register(builder, true, CrossSpongePlatformUtils.spongeEngine().causeStackManager().currentCause());
    }

    default boolean register(@NotNull SWarpBuilder builder) {
        return this.register(builder, true, CrossSpongePlatformUtils.spongeEngine().causeStackManager().currentCause());
    }

    default boolean register(@NotNull SJailSpawnPointBuilder builder) {
        return this.register(builder, true, CrossSpongePlatformUtils.spongeEngine().causeStackManager().currentCause());
    }


    default <P extends SPoint> @NotNull UnmodifiableCollection<P> pointsOf(@NotNull Class<P> type) {
        return new UnmodifiableCollection<>(this
                                                    .points()
                                                    .parallelStream()
                                                    .filter(type::isInstance)
                                                    .map(point -> (P) point)
                                                    .collect(Collectors.toSet()));
    }

    default @NotNull UnmodifiableCollection<SJailSpawnPoint> jailPositions() {
        return new UnmodifiableCollection<>(this.pointsOf(SJailSpawnPoint.class));
    }

    default @NotNull Optional<SJailSpawnPoint> jailPosition(@NotNull String identifier) {
        return this
                .jailPositions()
                .parallelStream()
                .filter(jail -> jail.identifier().equalsIgnoreCase(identifier))
                .findAny();
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

    default @NotNull UnmodifiableCollection<SSpawnPoint> spawnPoints() {
        return new UnmodifiableCollection<>(this.pointsOf(SSpawnPoint.class));
    }

    default @NotNull SSpawnPoint spawnPoint(@NotNull Vector3d blockPosition) {
        return this.spawnPoint(blockPosition, true);
    }

    default @NotNull SSpawnPoint spawnPoint(@NotNull Vector3d blockPosition, boolean ignoreFirstLogin) {
        double distance = Integer.MAX_VALUE;
        SSpawnPoint point = null;
        for (SSpawnPoint spawn : this.spawnPoints()) {
            if (ignoreFirstLogin && (SSpawnType.FIRST_LOGIN == spawn.type())) {
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

    default @NotNull UnmodifiableCollection<SWarp> warps() {
        return new UnmodifiableCollection<>(this.pointsOf(SWarp.class));
    }

    default Optional<SWarp> warp(@NotNull String identifier) {
        return this.warps().parallelStream().filter(warp -> warp.identifier().equalsIgnoreCase(identifier)).findAny();
    }

    @Override
    default @NotNull String identifier() {
        if (this.spongeWorld() instanceof ServerWorld) {
            return ((ServerWorld) this.spongeWorld()).key().formatted();
        }
        Context context = this.spongeWorld().context();
        return context.toString();
    }
}
