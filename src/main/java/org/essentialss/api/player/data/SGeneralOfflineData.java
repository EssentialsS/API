package org.essentialss.api.player.data;

import org.essentialss.api.world.points.OfflineLocation;
import org.essentialss.api.world.points.jail.SJailSpawnPoint;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.world.Location;

import java.time.Duration;

public interface SGeneralOfflineData extends SGeneralUnloadedData {


    void releaseFromJail(@NotNull OfflineLocation location);

    default void releaseFromJail(Location<?, ?> loc) {
        this.releaseFromJail(new OfflineLocation(loc));
    }

    void releaseFromJail();

    void sendToJail(@NotNull SJailSpawnPoint point, @Nullable Duration length);

}
