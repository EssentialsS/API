package org.essentialss.api.ban.data;

import org.essentialss.api.config.ExternalLoadable;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.network.ServerSideConnection;

import java.time.LocalDateTime;
import java.util.Optional;

public interface BanData<Self> extends ExternalLoadable<Self> {

    Optional<LocalDateTime> bannedUntil();

    boolean isBanned(@NotNull ServerSideConnection connection);

    Optional<String> lastKnownUsername();
}
