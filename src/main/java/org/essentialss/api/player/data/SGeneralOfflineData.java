package org.essentialss.api.player.data;

import org.essentialss.api.Serializable;
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

public interface SGeneralOfflineData extends Serializable {

    @NotNull UUID uuid();

    boolean canLooseItemsWhenUsed();

    void setCanLooseItemsWhenUsed(boolean check);

    boolean muted();

    void setMuted(boolean mute);

    boolean isInJail();

    boolean isPreventingTeleportRequests();

    void setPreventTeleportRequests(boolean prevent);

    Optional<LocalDateTime> releasedFromJailTime();

    void releaseFromJail(@NotNull OfflineLocation location);

    default void releaseFromJail(Location<?, ?> loc) {
        this.releaseFromJail(new OfflineLocation(loc));
    }

    void releaseFromJail();

    void sendToJail(@NotNull SJailSpawnPoint point, @Nullable Duration length);

    @NotNull UnmodifiableCollection<SHome> homes();

    void register(@NotNull SHomeBuilder builder);

    void deregister(@NotNull SHome home);

    @NotNull LinkedList<OfflineLocation> backTeleportLocations();

    void setBackTeleportLocations(Collection<OfflineLocation> locations);

    void addBackTeleportLocation(@NotNull OfflineLocation location);

    default void addBackTeleportLocation(@NotNull Location<?, ?> location) {
        this.addBackTeleportLocation(new OfflineLocation(location));
    }

    void removeBackTeleportLocation(@NotNull OfflineLocation location);

    default void removeBackTeleportLocation(@NotNull Location<?, ?> location) {
        this.removeBackTeleportLocation(new OfflineLocation(location));
    }

    default void clearBackTeleportLocations() {
        this.setBackTeleportLocations(Collections.emptyList());
    }

    @NotNull UnmodifiableCollection<MailMessage> mailMessages();

    void removeMessage(@NotNull MailMessage message);

    void addMailMessage(@NotNull MailMessageBuilder builder);

    default Optional<SHome> home(@NotNull String homeName) {
        return this.homes().parallelStream().filter(home -> home.identifier().equalsIgnoreCase(homeName)).findAny();
    }

}
