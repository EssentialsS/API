package org.essentialss.api.ban.data;

import org.essentialss.api.EssentialsSAPI;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.network.ServerSideConnection;
import org.spongepowered.api.profile.GameProfile;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AccountBanData extends BanData<AccountBanData> {

    GameProfile profile();

    @Override
    default Optional<String> lastKnownUsername() {
        return this.profile().name();
    }

    @Override
    default boolean isBanned(@NotNull ServerSideConnection connection) {
        if (this.bannedUntil().map(until -> !until.isAfter(LocalDateTime.now())).orElse(false)) {
            EssentialsSAPI.get().banManager().get().unban(this);
            return false;
        }
        return this.profile().equals(connection.profile());
    }
}
