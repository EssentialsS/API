package org.essentialss.api.ban.data;

import org.essentialss.api.EssentialsSAPI;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.network.ServerSideConnection;

import java.time.LocalDateTime;

public interface IPBanData extends BanData<IPBanData> {

    @NotNull String hostName();

    @Override
    default boolean isBanned(@NotNull ServerSideConnection connection) {
        if (this.bannedUntil().map(until -> !until.isAfter(LocalDateTime.now())).orElse(false)) {
            EssentialsSAPI.get().banManager().get().unban(this);
            return false;
        }
        return connection.address().getHostName().equalsIgnoreCase(this.hostName());
    }
}
