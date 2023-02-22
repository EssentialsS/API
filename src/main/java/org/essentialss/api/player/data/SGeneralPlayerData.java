package org.essentialss.api.player.data;

import org.essentialss.api.message.MessageData;
import org.essentialss.api.player.teleport.PlayerTeleportRequest;
import org.essentialss.api.player.teleport.TeleportRequest;
import org.essentialss.api.player.teleport.TeleportRequestBuilder;
import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.essentialss.api.world.SWorldData;
import org.essentialss.api.world.points.OfflineLocation;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.server.ServerLocation;
import org.spongepowered.math.vector.Vector3d;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;
import java.util.stream.Collectors;

public interface SGeneralPlayerData extends SGeneralOfflineData {

    PlayerTeleportRequest acceptTeleportRequest(@NotNull UUID playerId) throws IllegalStateException;

    default PlayerTeleportRequest acceptTeleportRequest(Player player) throws IllegalStateException {
        return this.acceptTeleportRequest(player.uniqueId());
    }

    @NotNull OptionalInt backTeleportIndex();

    PlayerTeleportRequest declineTeleportRequest(@NotNull UUID playerId) throws IllegalStateException;

    default PlayerTeleportRequest declineTeleportRequest(Player player) throws IllegalStateException {
        return this.declineTeleportRequest(player.uniqueId());
    }

    boolean isAwayFromKeyboard();

    void setAwayFromKeyboard(boolean afk);

    default Optional<PlayerTeleportRequest> playerTeleportRequest(@NotNull UUID sender) {
        return this.teleportRequests(PlayerTeleportRequest.class).parallelStream().filter(request -> request.sender().equals(sender)).findAny();
    }

    @NotNull TeleportRequest register(@NotNull TeleportRequestBuilder builder);

    @Override
    default void releaseFromJail() {
        this.releaseFromJail(this.world().spawnPoint(this.spongePlayer().position()).location());
    }

    void sendMessageTo(@NotNull MessageData data);

    void setBackTeleportIndex(int index);

    @NotNull Player spongePlayer();

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

    @NotNull UnmodifiableCollection<TeleportRequest> teleportRequests();

    default <R extends TeleportRequest> UnmodifiableCollection<R> teleportRequests(Class<R> clazz) {
        return new UnmodifiableCollection<>(this.teleportRequests().parallelStream().filter(clazz::isInstance).map(r -> (R) r).collect(Collectors.toList()));
    }

    @Override
    default @NotNull UUID uuid() {
        return this.spongePlayer().uniqueId();
    }

    @NotNull SWorldData world();
}
