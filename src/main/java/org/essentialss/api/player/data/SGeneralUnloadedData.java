package org.essentialss.api.player.data;

import net.kyori.adventure.text.Component;
import org.essentialss.api.config.Serializable;
import org.essentialss.api.message.MuteType;
import org.essentialss.api.player.data.module.ModuleData;
import org.essentialss.api.player.data.module.SerializableModuleData;
import org.essentialss.api.player.mail.MailMessage;
import org.essentialss.api.player.mail.MailMessageBuilder;
import org.essentialss.api.utils.arrays.OrderedUnmodifiableCollection;
import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.essentialss.api.world.points.OfflineLocation;
import org.essentialss.api.world.points.home.SHome;
import org.essentialss.api.world.points.home.SHomeBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.api.world.Location;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

public interface SGeneralUnloadedData extends Serializable {

    void addBackTeleportLocation(@NotNull OfflineLocation location);

    default void addBackTeleportLocation(@NotNull Location<?, ?> location) {
        this.addBackTeleportLocation(new OfflineLocation(location));
    }

    void addMailMessage(@NotNull MailMessageBuilder builder);

    @NotNull OrderedUnmodifiableCollection<OfflineLocation> backTeleportLocations();

    boolean canLooseItemsWhenUsed();

    default void clearBackTeleportLocations() {
        this.setBackTeleportLocations(Collections.emptyList());
    }

    void deregister(@NotNull SHome home);

    void deregisterData(@NotNull ResourceKey key);

    default void deregisterData(@NotNull ModuleData<?> data) {
        this.deregisterData(data.key());
    }

    @NotNull Component displayName();

    <T extends ModuleData<?>> Optional<T> getData(@NotNull ResourceKey clazz);

    boolean hasSetDisplayName();

    default Optional<SHome> home(@NotNull String homeName) {
        return this.homes().parallelStream().filter(home -> home.identifier().equalsIgnoreCase(homeName)).findAny();
    }

    @NotNull UnmodifiableCollection<SHome> homes();

    boolean isCommandSpying();

    void setCommandSpying(boolean spying);

    boolean isInJail();

    boolean isPreventingTeleportRequests();

    @NotNull UnmodifiableCollection<MailMessage> mailMessages();

    @NotNull UnmodifiableCollection<MuteType> muteTypes();

    @NotNull String playerName();

    default Optional<GameProfile> profile() {
        if (!Sponge.isServerAvailable()) {
            return Optional.empty();
        }
        return Sponge.server().gameProfileManager().cache().findById(this.uuid());
    }

    void register(@NotNull SHomeBuilder builder);

    void registerOfflineData(@NotNull SerializableModuleData<?> moduleData);

    Optional<LocalDateTime> releasedFromJailTime();

    void removeBackTeleportLocation(@NotNull OfflineLocation location);

    default void removeBackTeleportLocation(@NotNull Location<?, ?> location) {
        this.removeBackTeleportLocation(new OfflineLocation(location));
    }

    default void removeDisplayName() {
        this.setDisplayName(null);
    }

    void removeMessage(@NotNull MailMessage message);

    default void removeMuteTypes() {
        this.setMuteTypes();
    }

    void setBackTeleportLocations(Collection<OfflineLocation> locations);

    void setCanLooseItemsWhenUsed(boolean check);

    void setDisplayName(@Nullable Component component);

    void setHomes(@NotNull Collection<SHomeBuilder> homes);

    void setMuteTypes(@NotNull MuteType... types);

    default void setMuted() {
        this.setMuteTypes(MuteType.values());
    }

    void setPreventTeleportRequests(boolean prevent);

    @NotNull UUID uuid();

}
