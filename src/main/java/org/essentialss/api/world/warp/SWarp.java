package org.essentialss.api.world.warp;

import org.essentialss.api.world.SWorldData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.world.Location;
import org.spongepowered.math.vector.Vector3d;

public interface SWarp extends SPoint {

    @NotNull String identifier();
}
