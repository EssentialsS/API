package org.essentialss.api.world;

import org.essentialss.api.world.warp.SPoint;
import org.essentialss.api.world.warp.SSpawnPoint;
import org.essentialss.api.world.warp.SWarp;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.world.World;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public interface SWorldData {

    @NotNull World<?, ?> spongeWorld();

    @NotNull Collection<SPoint> points();

    default <P extends SPoint> @NotNull Collection<P> pointsOf(@NotNull Class<P> type) {
        return points().parallelStream().filter(type::isInstance).map(point -> (P) point).collect(Collectors.toSet());
    }

    default @NotNull Collection<SSpawnPoint> spawns(){
        return pointsOf(SSpawnPoint.class);
    }

    default @NotNull Collection<SWarp> warps() {
        return pointsOf(SWarp.class);
    }

    default Optional<SWarp> warp(@NotNull String identifier) {
        return this.warps().parallelStream().filter(warp -> warp.identifier().equalsIgnoreCase(identifier)).findAny();
    }

}
