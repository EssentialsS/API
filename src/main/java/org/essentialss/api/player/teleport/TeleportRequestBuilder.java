package org.essentialss.api.player.teleport;

import org.essentialss.api.player.data.SGeneralPlayerData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.util.Nameable;
import org.spongepowered.api.world.Location;

import java.time.Duration;
import java.util.function.Function;

@SuppressWarnings("allow-nullable")
public class TeleportRequestBuilder {

    private Function<SGeneralPlayerData, Location<?, ?>> to;
    private TeleportRequestDirection direction;
    private Object sender;
    private String senderDisplayName;
    private Duration validForLength;

    public @Nullable Function<SGeneralPlayerData, Location<?, ?>> getTo() {
        return this.to;
    }

    public @NotNull TeleportRequestBuilder setTo(@NotNull Function<SGeneralPlayerData, Location<?, ?>> to) {
        this.to = to;
        return this;
    }

    public @Nullable TeleportRequestDirection getDirection() {
        return this.direction;
    }

    public @NotNull TeleportRequestBuilder setDirection(@NotNull TeleportRequestDirection direction) {
        this.direction = direction;
        return this;
    }

    public @Nullable Object getSender() {
        return this.sender;
    }

    public @NotNull TeleportRequestBuilder setSender(@NotNull Object sender) {
        this.sender = sender;
        return this;
    }

    public @NotNull TeleportRequestBuilder setSender(@NotNull Nameable player) {
        this.sender = player;
        this.senderDisplayName = player.name();
        return this;
    }

    public @Nullable String getSenderDisplayName() {
        return this.senderDisplayName;
    }

    public @NotNull TeleportRequestBuilder setSenderDisplayName(@NotNull String senderDisplayName) {
        this.senderDisplayName = senderDisplayName;
        return this;
    }

    public @Nullable Duration getValidForLength() {
        return this.validForLength;
    }

    public @NotNull TeleportRequestBuilder setValidForLength(@Nullable Duration validForLength) {
        this.validForLength = validForLength;
        return this;
    }

    public @NotNull TeleportRequestBuilder setNeverExpires() {
        return this.setValidForLength(null);
    }

    public @NotNull TeleportRequestBuilder requestTo(@NotNull Player player) {
        this.setSender(player);
        this.to = ((player2) -> player.location());
        this.validForLength = Duration.ofMinutes(1);
        this.direction = TeleportRequestDirection.TO;
        return this;
    }

    public @NotNull TeleportRequestBuilder requestFrom(@NotNull Nameable player) {
        this.setSender(player);
        this.to = ((player2) -> player2.spongePlayer().location());
        this.validForLength = Duration.ofMinutes(1);
        this.direction = TeleportRequestDirection.FROM;
        return this;
    }
}
