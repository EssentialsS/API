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

    private TeleportRequestDirection direction;
    private Object sender;
    private String senderDisplayName;
    private Function<SGeneralPlayerData, Location<?, ?>> to;
    private Duration validForLength;

    @Nullable
    public TeleportRequestDirection getDirection() {
        return this.direction;
    }

    @NotNull
    public TeleportRequestBuilder setDirection(@NotNull TeleportRequestDirection direction) {
        this.direction = direction;
        return this;
    }

    @Nullable
    public Object getSender() {
        return this.sender;
    }

    @NotNull
    public TeleportRequestBuilder setSender(@NotNull Object sender) {
        this.sender = sender;
        return this;
    }

    @NotNull
    public TeleportRequestBuilder setSender(@NotNull Nameable player) {
        this.sender = player;
        this.senderDisplayName = player.name();
        return this;
    }

    @Nullable
    public String getSenderDisplayName() {
        return this.senderDisplayName;
    }

    @NotNull
    public TeleportRequestBuilder setSenderDisplayName(@NotNull String senderDisplayName) {
        this.senderDisplayName = senderDisplayName;
        return this;
    }

    @Nullable
    public Function<SGeneralPlayerData, Location<?, ?>> getTo() {
        return this.to;
    }

    @NotNull
    public TeleportRequestBuilder setTo(@NotNull Function<SGeneralPlayerData, Location<?, ?>> to) {
        this.to = to;
        return this;
    }

    @Nullable
    public Duration getValidForLength() {
        return this.validForLength;
    }

    @NotNull
    public TeleportRequestBuilder setValidForLength(@Nullable Duration validForLength) {
        this.validForLength = validForLength;
        return this;
    }

    @NotNull
    public TeleportRequestBuilder requestTowardsHolder(@NotNull Nameable sender) {
        this.setSender(sender);
        this.to = ((player2) -> player2.spongePlayer().location());
        this.validForLength = Duration.ofMinutes(1);
        this.direction = TeleportRequestDirection.TOWARDS_REQUEST_HOLDER;
        return this;
    }

    @NotNull
    public TeleportRequestBuilder requestTowardsSender(@NotNull Player sender) {
        this.setSender(sender);
        this.to = ((player2) -> sender.location());
        this.validForLength = Duration.ofMinutes(1);
        this.direction = TeleportRequestDirection.TOWARDS_REQUEST_SENDER;
        return this;
    }

    @NotNull
    public TeleportRequestBuilder setNeverExpires() {
        return this.setValidForLength(null);
    }
}
