package org.essentialss.api.ban.data;

import org.essentialss.api.config.ExternalLoadable;
import org.essentialss.api.config.Serializable;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.network.ServerSideConnection;

import java.time.LocalDateTime;
import java.util.Optional;

public interface BanData<Self> extends ExternalLoadable<Self> {

    boolean isBanned(@NotNull ServerSideConnection connection);

    Optional<String> lastKnownUsername();

    Optional<LocalDateTime> bannedUntil();
}
