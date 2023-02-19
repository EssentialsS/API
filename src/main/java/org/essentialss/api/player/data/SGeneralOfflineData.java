package org.essentialss.api.player.data;

import org.essentialss.api.player.mail.MailMessage;
import org.essentialss.api.player.mail.MailMessageBuilder;
import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.essentialss.api.world.points.OfflineLocation;
import org.essentialss.api.world.points.home.SHome;
import org.essentialss.api.world.points.home.SHomeBuilder;
import org.essentialss.api.world.points.jail.SJailSpawnPoint;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.world.Location;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public interface SGeneralOfflineData extends SGeneralUnloadedData {


    void releaseFromJail(@NotNull OfflineLocation location);

    default void releaseFromJail(Location<?, ?> loc) {
        this.releaseFromJail(new OfflineLocation(loc));
    }

    void releaseFromJail();

    void sendToJail(@NotNull SJailSpawnPoint point, @Nullable Duration length);

}
