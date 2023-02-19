package org.essentialss.api.player.data;

import net.kyori.adventure.text.Component;
import org.essentialss.api.config.Serializable;
import org.essentialss.api.player.mail.MailMessage;
import org.essentialss.api.player.mail.MailMessageBuilder;
import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.essentialss.api.world.points.OfflineLocation;
import org.essentialss.api.world.points.home.SHome;
import org.essentialss.api.world.points.home.SHomeBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.api.world.Location;

import java.time.LocalDateTime;
import java.util.*;

public interface SGeneralUnloadedData extends Serializable {

    @NotNull Component displayName();

    boolean hasSetDisplayName();

    void setDisplayName(@Nullable Component component);

    String playerName();

    @NotNull UUID uuid();

    boolean canLooseItemsWhenUsed();

    void setCanLooseItemsWhenUsed(boolean check);

    boolean muted();

    void setMuted(boolean mute);

    boolean isInJail();

    boolean isPreventingTeleportRequests();

    void setPreventTeleportRequests(boolean prevent);

    Optional<LocalDateTime> releasedFromJailTime();

    @NotNull UnmodifiableCollection<SHome> homes();

    void register(@NotNull SHomeBuilder builder);

    void deregister(@NotNull SHome home);

    void setHomes(@NotNull Collection<SHomeBuilder> homes);

    @NotNull LinkedList<OfflineLocation> backTeleportLocations();

    void setBackTeleportLocations(Collection<OfflineLocation> locations);

    void addBackTeleportLocation(@NotNull OfflineLocation location);

    default void addBackTeleportLocation(@NotNull Location<?, ?> location) {
        this.addBackTeleportLocation(new OfflineLocation(location));
    }

    void removeBackTeleportLocation(@NotNull OfflineLocation location);

    @NotNull UnmodifiableCollection<MailMessage> mailMessages();

    void removeMessage(@NotNull MailMessage message);

    void addMailMessage(@NotNull MailMessageBuilder builder);

    default Optional<SHome> home(@NotNull String homeName) {
        return this.homes().parallelStream().filter(home -> home.identifier().equalsIgnoreCase(homeName)).findAny();
    }


    default void removeBackTeleportLocation(@NotNull Location<?, ?> location) {
        this.removeBackTeleportLocation(new OfflineLocation(location));
    }

    default void clearBackTeleportLocations() {
        this.setBackTeleportLocations(Collections.emptyList());
    }

    default void removeDisplayName() {
        this.setDisplayName(null);
    }

    default Optional<GameProfile> profile() {
        if (!Sponge.isServerAvailable()) {
            return Optional.empty();
        }
        return Sponge.server().gameProfileManager().cache().findById(this.uuid());
    }

}
