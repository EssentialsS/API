package org.essentialss.api.world;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.world.World;

public interface SWorldManager {

    @NotNull SWorldData dataFor(@NotNull World<?, ?> worldData);

    @NotNull SWorldData dataFor(@NotNull ResourceKey worldKey);
}
