package org.essentialss.api.player.data;

import net.kyori.adventure.bossbar.BossBar;
import org.essentialss.api.message.MessageData;
import org.essentialss.api.modifier.SPlayerModifier;
import org.essentialss.api.modifier.SPlayerModifiers;
import org.essentialss.api.player.data.module.ModuleData;
import org.essentialss.api.player.teleport.PlayerTeleportRequest;
import org.essentialss.api.player.teleport.TeleportRequest;
import org.essentialss.api.player.teleport.TeleportRequestBuilder;
import org.essentialss.api.utils.arrays.OrderedUnmodifiableCollection;
import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.essentialss.api.utils.arrays.impl.SingleOrderedUnmodifiableCollection;
import org.essentialss.api.utils.arrays.impl.SingleUnmodifiableCollection;
import org.essentialss.api.world.SWorldData;
import org.essentialss.api.world.points.OfflineLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mose.property.CollectionProperty;
import org.mose.property.Property;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.server.ServerLocation;
import org.spongepowered.math.vector.Vector3d;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public interface SGeneralPlayerData extends SGeneralOfflineData {
    
    PlayerTeleportRequest acceptTeleportRequest(@NotNull UUID playerId) throws IllegalStateException;

    default PlayerTeleportRequest acceptTeleportRequest(Player player) throws IllegalStateException {
        return this.acceptTeleportRequest(player.uniqueId());
    }

    default Collection<SPlayerModifier<?>> appliedAwayFromKeyboardModifiers() {
        return this.awayFromKeyboardModifierProperties().value().orElseGet(Collections::emptyList);
    }

    CollectionProperty.Write<SPlayerModifier<?>, Collection<SPlayerModifier<?>>> awayFromKeyboardModifierProperties();

    @NotNull
    default OptionalInt backTeleportIndex() {
        return this.backTeleportIndexProperty().value().map(OptionalInt::of).orElse(OptionalInt.empty());
    }

    Property.Write<Integer, Integer> backTeleportIndexProperty();

    default Optional<BossBar> barUntilKick() {
        return this.barUntilKickedProperty().value();
    }

    Property.ReadOnly<BossBar, BossBar> barUntilKickedProperty();

    PlayerTeleportRequest declineTeleportRequest(@NotNull UUID playerId) throws IllegalStateException;

    default PlayerTeleportRequest declineTeleportRequest(Player player) throws IllegalStateException {
        return this.declineTeleportRequest(player.uniqueId());
    }

    default boolean hasShownAwayFromKeyboardMessage() {
        return this.hasShownAwayFromKeyboardMessageProperty().value().orElse(false);
    }

    <P extends Property.Write<Boolean, Boolean> & Property.NeverNull<Boolean, Boolean>> P hasShownAwayFromKeyboardMessageProperty();

    default boolean isShowingAwayFromKeyboard() {
        Optional<Boolean> opValue = this.isShowingAwayFromKeyboardProperty().value();
        return opValue.orElse(false);
    }

    <P extends Property.ReadOnly<Boolean, Boolean> & Property.NeverNull<Boolean, Boolean>> P isShowingAwayFromKeyboardProperty();

    Property.Write<LocalDateTime, LocalDateTime> lastActionProperty();

    default LocalDateTime lastPlayerAction() {
        return this.lastActionProperty().value().orElseGet(LocalDateTime::now);
    }

    default void playerAction() {
        this.lastActionProperty().setValue(LocalDateTime.now());
    }

    default Optional<PlayerTeleportRequest> playerTeleportRequest(@NotNull UUID sender) {
        return this.teleportRequests(PlayerTeleportRequest.class).parallelStream().filter(request -> request.sender().equals(sender)).findAny();
    }

    @NotNull
    TeleportRequest register(@NotNull TeleportRequestBuilder builder);

    default void registerData(@NotNull ModuleData<?> moduleData) {
        this.moduleDataProperty().add(moduleData);
    }

    @Override
    default void releaseFromJail() {
        this.releaseFromJail(this.world().spawnPoint(this.spongePlayer().position()).location());
    }

    default void removeBarUntilKick() {
        this.setBarUntilKick(null);
    }

    void sendMessageTo(@NotNull MessageData data);

    default void setAwayFromKeyboard() {
        if (SPlayerModifiers.VISIBILITY.hasApplied(this.spongePlayer())) {
            this.setAwayFromKeyboardSince(null, SPlayerModifiers.VISIBILITY);
            return;
        }
        this.setAwayFromKeyboardSince(null);
    }

    default void setAwayFromKeyboard(SPlayerModifier<?>... modifiers) {
        this.setAwayFromKeyboardSince(null, modifiers);
    }

    void setAwayFromKeyboardSince(@Nullable LocalDateTime since, Collection<SPlayerModifier<?>> modifiers);

    default void setAwayFromKeyboardSince(@Nullable LocalDateTime since, SPlayerModifier<?>... modifiers) {
        this.setAwayFromKeyboardSince(since, Arrays.asList(modifiers));
    }

    default void setBackTeleportIndex(int index) {
        this.backTeleportIndexProperty().setValue(index);
    }

    void setBarUntilKick(@Nullable BossBar bar);

    void setNextToKeyboard();

    default void setShownAwayFromKeyboardMessage(boolean value) {
        hasShownAwayFromKeyboardMessageProperty().setValue(value);
    }

    @NotNull
    Player spongePlayer();

    default void teleport(@NotNull Vector3d position) {
        this.addBackTeleportLocation(this.spongePlayer().location());
        this.spongePlayer().setPosition(position);
    }

    default void teleport(OfflineLocation location) {
        Optional<Location<?, ?>> opLoc = location.location();
        if (!opLoc.isPresent()) {
            throw new IllegalStateException("World has not loaded");
        }
        Location<?, ?> loc = opLoc.get();
        Optional<ServerLocation> opServerLocation = loc.onServer();
        if (opServerLocation.isPresent()) {
            this.addBackTeleportLocation(this.spongePlayer().location());
            this.spongePlayer().setLocation(opServerLocation.get());
            return;
        }
        if (Sponge.isClientAvailable() && Sponge.client().world().map(clientWorld -> clientWorld.equals(loc.world())).orElse(false)) {
            this.addBackTeleportLocation(this.spongePlayer().location());
            this.spongePlayer().setPosition(loc.position());
            return;
        }
        throw new IllegalStateException("Cannot teleport, world has not loaded");
    }

    default void teleport(@NotNull Location<?, ?> location) {
        Optional<ServerLocation> opServerLocation = location.onServer();
        Player player = this.spongePlayer();
        if (opServerLocation.isPresent()) {
            this.addBackTeleportLocation(player.location());
            player.setLocation(location.onServer().orElseThrow(() -> new IllegalStateException("Player is server player but cant create server location")));
            return;
        }
        if (location.world().equals(player.world())) {
            this.teleport(location.position());
            return;
        }
        throw new IllegalStateException("World has not been loaded. Cannot teleport");

    }

    @NotNull
    default UnmodifiableCollection<TeleportRequest> teleportRequests() {
        return this.teleportRequestsProperty().value().orElseGet(() -> new SingleOrderedUnmodifiableCollection<>(Collections.emptyList()));
    }

    default <R extends TeleportRequest> UnmodifiableCollection<R> teleportRequests(Class<R> clazz) {
        return new SingleUnmodifiableCollection<>(
                this.teleportRequests().parallelStream().filter(clazz::isInstance).map(r -> (R) r).collect(Collectors.toList()));
    }

    CollectionProperty.ReadOnly<TeleportRequest, OrderedUnmodifiableCollection<TeleportRequest>> teleportRequestsProperty();

    @Override
    @NotNull
    default UUID uuid() {
        return this.spongePlayer().uniqueId();
    }

    @NotNull
    SWorldData world();
}
