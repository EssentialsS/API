package org.essentialss.api.player.data;

import net.kyori.adventure.text.Component;
import org.essentialss.api.config.Serializable;
import org.essentialss.api.message.MuteType;
import org.essentialss.api.player.data.module.ModuleData;
import org.essentialss.api.player.data.module.SerializableModuleData;
import org.essentialss.api.player.mail.MailMessage;
import org.essentialss.api.utils.arrays.OrderedUnmodifiableCollection;
import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.essentialss.api.utils.arrays.impl.SingleOrderedUnmodifiableCollection;
import org.essentialss.api.utils.arrays.impl.SingleUnmodifiableCollection;
import org.essentialss.api.world.points.OfflineLocation;
import org.essentialss.api.world.points.home.SHome;
import org.essentialss.api.world.points.home.SHomeBuilder;
import org.essentialss.api.player.mail.MailMessageBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mose.property.CollectionProperty;
import org.mose.property.Property;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.cause.entity.damage.DamageType;
import org.spongepowered.api.event.cause.entity.damage.DamageTypes;
import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.api.world.Location;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.LinkedTransferQueue;
import java.util.stream.Collectors;

public interface SGeneralUnloadedData extends Serializable {

    default void addBackTeleportLocation(@NotNull OfflineLocation location) {
        this.backTeleportLocationsProperty().add(location);
    }

    default void addBackTeleportLocation(@NotNull Location<?, ?> location) {
        this.addBackTeleportLocation(new OfflineLocation(location));
    }

    default void addImmuneTo(Collection<DamageType> types) {
        this.immuneToProperty().addAll(types);
    }

    default void addImmuneTo(DamageType... types) {
        this.addImmuneTo(Arrays.asList(types));
    }

    void addMailMessage(@NotNull MailMessageBuilder builder);

    @NotNull
    default OrderedUnmodifiableCollection<OfflineLocation> backTeleportLocations() {
        return this.backTeleportLocationsProperty().value().orElse(new SingleOrderedUnmodifiableCollection<>(Collections.emptyList()));
    }

    @NotNull
    CollectionProperty.Write<OfflineLocation, OrderedUnmodifiableCollection<OfflineLocation>> backTeleportLocationsProperty();

    default boolean canLooseItemsWhenUsed() {
        return this.canLooseItemsWhenUsedProperty().value().orElse(false);
    }

    <P extends Property.Write<Boolean, Boolean> & Property.NeverNull<Boolean, Boolean>> P canLooseItemsWhenUsedProperty();

    default void clearBackTeleportLocations() {
        this.setBackTeleportLocations(Collections.emptyList());
    }

    void deregister(@NotNull SHome home);

    default void deregisterData(@NotNull ResourceKey key) {
        List<ModuleData<?>> list = this
                .moduleDataProperty()
                .value()
                .orElse(new LinkedTransferQueue<>())
                .stream()
                .filter(data -> data.key().equals(key))
                .collect(Collectors.toList());
        this.moduleDataProperty().removeAll(list);
    }

    default void deregisterData(@NotNull ModuleData<?> data) {
        this.deregisterData(data.key());
    }

    @NotNull
    default Component displayName() {
        Optional<Component> component = this.displayNameProperty().value();
        return component.orElseGet(() -> Component.text(this.playerName()));
    }

    @NotNull
    Property.Write<Component, Component> displayNameProperty();

    default <T extends ModuleData<?>> Optional<T> getData(@NotNull ResourceKey clazz) {
        return this
                .moduleDataProperty()
                .value()
                .orElse(new LinkedTransferQueue<>())
                .stream()
                .filter(data -> data.key().equals(clazz))
                .findAny()
                .map(data -> (T) data);
    }

    default boolean hasGodMode() {
        return this.hasGodModeProperty().safeValue();
    }

    <P extends Property.ReadOnly<Boolean, Boolean> & Property.NeverNull<Boolean, Boolean>> P hasGodModeProperty();

    default boolean hasSetDisplayName() {
        return this.displayNameProperty().value().isPresent();
    }

    default Optional<SHome> home(@NotNull String homeName) {
        return this.homes().parallelStream().filter(home -> home.identifier().equalsIgnoreCase(homeName)).findAny();
    }

    @NotNull
    default UnmodifiableCollection<SHome> homes() {
        return this.homesProperty().value().orElse(new SingleUnmodifiableCollection<>(Collections.emptyList()));
    }

    CollectionProperty.ReadOnly<SHome, UnmodifiableCollection<SHome>> homesProperty();

    default UnmodifiableCollection<DamageType> immuneTo() {
        return this.immuneToProperty().value().orElse(new SingleUnmodifiableCollection<>(Collections.emptyList()));
    }

    CollectionProperty.Write<DamageType, UnmodifiableCollection<DamageType>> immuneToProperty();

    default boolean isCommandSpying() {
        return this.isCommandSpyingProperty().value().orElse(false);
    }

    default void setCommandSpying(boolean spying) {
        this.isCommandSpyingProperty().setValue(spying);
    }

    <P extends Property.Write<Boolean, Boolean> & Property.NeverNull<Boolean, Boolean>> P isCommandSpyingProperty();

    default boolean isImmuneTo(DamageType type) {
        return this.immuneTo().contains(type);
    }

    default boolean isInJail() {
        return this.isInJailProperty().value().orElse(false);
    }

    <P extends Property.ReadOnly<Boolean, Boolean> & Property.NeverNull<Boolean, Boolean>> P isInJailProperty();

    default boolean isPreventingTeleportRequests() {
        return this.isPreventingTeleportRequestsProperty().value().orElse(false);
    }

    <P extends Property.Write<Boolean, Boolean> & Property.NeverNull<Boolean, Boolean>> P isPreventingTeleportRequestsProperty();

    @NotNull
    default UnmodifiableCollection<MailMessage> mailMessages() {
        return this.mailMessagesProperty().value().orElse(new SingleUnmodifiableCollection<>(Collections.emptyList()));
    }

    CollectionProperty.ReadOnly<MailMessage, UnmodifiableCollection<MailMessage>> mailMessagesProperty();

    CollectionProperty.Write<ModuleData<?>, LinkedTransferQueue<ModuleData<?>>> moduleDataProperty();

    @NotNull
    default UnmodifiableCollection<MuteType> muteTypes() {
        return this.muteTypesProperty().value().orElse(new SingleUnmodifiableCollection<>(Collections.emptyList()));
    }

    CollectionProperty.Write<MuteType, UnmodifiableCollection<MuteType>> muteTypesProperty();

    @NotNull
    String playerName();

    default Optional<GameProfile> profile() {
        if (!Sponge.isServerAvailable()) {
            return Optional.empty();
        }
        return Sponge.server().gameProfileManager().cache().findById(this.uuid());
    }

    void register(@NotNull SHomeBuilder builder);

    default void registerOfflineData(@NotNull SerializableModuleData<?> moduleData) {
        this.moduleDataProperty().add(moduleData);
    }

    default Optional<LocalDateTime> releasedFromJailTime() {
        return this.releasedFromJailTimeProperty().value();
    }

    Property.ReadOnly<LocalDateTime, LocalDateTime> releasedFromJailTimeProperty();

    default void removeBackTeleportLocation(@NotNull OfflineLocation location) {
        this.backTeleportLocationsProperty().remove(location);
    }

    default void removeBackTeleportLocation(@NotNull Location<?, ?> location) {
        this.removeBackTeleportLocation(new OfflineLocation(location));
    }

    default void removeDisplayName() {
        this.setDisplayName(null);
    }

    default void removeImmuneTo(Collection<DamageType> types) {
        this.immuneToProperty().removeAll(types);
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

    default void setBackTeleportLocations(Collection<OfflineLocation> locations) {
        this.backTeleportLocationsProperty().removeAll(locations);
    }

    default void setCanLooseItemsWhenUsed(boolean check) {
        this.canLooseItemsWhenUsedProperty().setValue(check);
    }

    default void setDisplayName(@Nullable Component component) {
        this.displayNameProperty().setValue(component);
    }

    default void setGodMode() {
        Collection<DamageType> types = DamageTypes.registry().stream().collect(Collectors.toCollection(LinkedHashSet::new));
        types.remove(DamageTypes.VOID.get());
        this.setImmuneTo(types);
    }

    void setHomes(@NotNull Collection<SHomeBuilder> homes);

    default void setImmuneTo(Collection<DamageType> immuneTo) {
        this.immuneToProperty().setValue(immuneTo);
    }

    default void setImmuneTo(DamageType... types) {
        this.setImmuneTo(Arrays.asList(types));
    }

    default void setMuteTypes(@NotNull MuteType... types) {
        this.muteTypesProperty().setValue(types);
    }

    default void setMuted() {
        this.setMuteTypes(MuteType.values());
    }

    default void setPreventTeleportRequests(boolean prevent) {
        this.isPreventingTeleportRequestsProperty().setValue(prevent);
    }

    default void setUnlimitedFood(boolean value) {
        this.unlimitedFoodProperty().setValue(value);
    }

    default boolean unlimitedFood() {
        return this.unlimitedFoodProperty().safeValue();
    }

    <P extends Property.Write<Boolean, Boolean> & Property.NeverNull<Boolean, Boolean>> P unlimitedFoodProperty();

    @NotNull
    UUID uuid();

}
