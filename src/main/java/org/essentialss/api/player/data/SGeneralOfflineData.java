package org.essentialss.api.player.data;

import org.essentialss.api.Serializable;
import org.essentialss.api.player.mail.MailMessage;
import org.essentialss.api.player.mail.MailMessageBuilder;
import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.essentialss.api.world.points.home.SHome;
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

    void releaseFromJail(@NotNull Location<?, ?> spawnTo);

    void releaseFromJail();

    void sendToJail(@NotNull SJailSpawnPoint point, @Nullable Duration length);

    @NotNull UnmodifiableCollection<SHome> homes();

    @NotNull LinkedList<Location<?, ?>> backTeleportLocations();

    void setBackTeleportLocations(Collection<Location<?, ?>> locations);

    void addBackTeleportLocation(@NotNull Location<?, ?> location);

    void removeBackTeleportLocation(@NotNull Location<?, ?> location);

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
