package org.essentialss.api.player.teleport;

import org.essentialss.api.EssentialsSAPI;
import org.essentialss.api.player.data.SGeneralPlayerData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import java.util.Optional;
import java.util.UUID;

public interface PlayerTeleportRequest extends TeleportRequest {

    @Override
    @NotNull
    UUID sender();

    @NotNull
    default Optional<Player> senderAsPlayer() {
        if (!Sponge.isServerAvailable()) {
            if (Sponge.isClientAvailable()) {
                return Sponge.client().player().filter(player -> player.uniqueId().equals(this.sender())).map(p -> p);
            }
            return Optional.empty();
        }
        return Sponge.server().player(this.sender()).map(p -> p);
    }

    @NotNull
    default Optional<SGeneralPlayerData> senderAsPlayerData() {
        return this.senderAsPlayer().map(p -> EssentialsSAPI.get().playerManager().get().dataFor(p));
    }
}
