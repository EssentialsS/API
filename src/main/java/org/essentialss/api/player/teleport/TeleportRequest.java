package org.essentialss.api.player.teleport;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.essentialss.api.player.data.SGeneralPlayerData;
import org.essentialss.api.utils.identifier.ObjectSender;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.world.Location;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Function;

public interface TeleportRequest extends ObjectSender {

    @NotNull TeleportRequestDirection directionOfTeleport();

    @NotNull Component senderFormattedDisplayName();

    @NotNull Function<SGeneralPlayerData, Location<?, ?>> teleportToLocation();

    @NotNull LocalDateTime sentTime();

    Optional<Duration> validForDuration();

    default Optional<LocalDateTime> expiresAt() {
        return this.validForDuration().map(duration -> this.sentTime().plus(duration));
    }

    @Override
    default @NotNull String senderDisplayName() {
        return PlainTextComponentSerializer.plainText().serialize(this.senderFormattedDisplayName());
    }
}
