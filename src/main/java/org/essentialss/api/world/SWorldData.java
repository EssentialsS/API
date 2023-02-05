package org.essentialss.api.world;

import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.essentialss.api.world.points.SPoint;
import org.essentialss.api.world.points.home.SHomeBuilder;
import org.essentialss.api.world.points.jail.SJailSpawnPoint;
import org.essentialss.api.world.points.spawn.SSpawnPoint;
import org.essentialss.api.world.points.spawn.SSpawnPointBuilder;
import org.essentialss.api.world.points.spawn.SSpawnType;
import org.essentialss.api.world.points.warp.SWarp;
import org.essentialss.api.world.points.warp.SWarpBuilder;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.world.World;
import org.spongepowered.math.vector.Vector3d;

import java.util.Optional;
import java.util.stream.Collectors;

public interface SWorldData {

    @NotNull World<?, ?> spongeWorld();

    @NotNull UnmodifiableCollection<SPoint> points();

    void register(@NotNull SHomeBuilder builder);

    void register(@NotNull SSpawnPointBuilder builder);

    void register(@NotNull SWarpBuilder builder);

    void register(@NotNull SJailSpawnPoint builder);

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

}
