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
import org.spongepowered.api.event.cause.entity.damage.DamageType;
import org.spongepowered.api.event.cause.entity.damage.DamageTypes;
import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.api.world.Location;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public interface SGeneralUnloadedData extends Serializable {

    void addBackTeleportLocation(@NotNull OfflineLocation location);

    default void addBackTeleportLocation(@NotNull Location<?, ?> location) {
        this.addBackTeleportLocation(new OfflineLocation(location));
    }

    default void addImmuneTo(Collection<DamageType> types) {
        Collection<DamageType> type = new LinkedHashSet<>(types);
        type.addAll(this.immuneTo());
        this.setImmuneTo(type);
    }

    default void addImmuneTo(DamageType... types) {
        this.addImmuneTo(Arrays.asList(types));
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

    UnmodifiableCollection<DamageType> immuneTo();

    boolean isCommandSpying();

    void setCommandSpying(boolean spying);

    default boolean isImmuneTo(DamageType type) {
        return this.immuneTo().contains(type);
    }

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

    default void removeImmuneTo(Collection<DamageType> types) {
        Collection<DamageType> collection = new LinkedHashSet<>(this.immuneTo());
        collection.removeAll(types);
        this.setImmuneTo(collection);
    }

    default void removeImmuneTo(DamageType... types) {
        this.removeImmuneTo(Arrays.asList(types));
    }

    default void removeImmunity() {
        this.setImmuneTo(Collections.emptyList());
    }

    void removeMessage(@NotNull MailMessage message);

    default void removeMuteTypes() {
        this.setMuteTypes();
    }

    void setBackTeleportLocations(Collection<OfflineLocation> locations);

    void setCanLooseItemsWhenUsed(boolean check);

    void setDisplayName(@Nullable Component component);

    default void setGodMode() {
        Collection<DamageType> types = new LinkedHashSet<>(DamageTypes.registry().stream().collect(Collectors.toSet()));
        types.remove(DamageTypes.VOID.get());
        this.setImmuneTo(types);
    }

    void setHomes(@NotNull Collection<SHomeBuilder> homes);

    void setImmuneTo(Collection<DamageType> immuneTo);

    default void setImmuneTo(DamageType... types) {
        this.setImmuneTo(Arrays.asList(types));
    }

    void setMuteTypes(@NotNull MuteType... types);

    default void setMuted() {
        this.setMuteTypes(MuteType.values());
    }

    void setPreventTeleportRequests(boolean prevent);

    @NotNull UUID uuid();

}
