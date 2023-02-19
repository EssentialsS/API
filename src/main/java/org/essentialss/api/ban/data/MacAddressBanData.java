package org.essentialss.api.ban.data;

import org.essentialss.api.EssentialsSAPI;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.network.ServerSideConnection;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.util.Arrays;

public interface MacAddressBanData extends BanData<MacAddressBanData> {
    byte[] macAddress();

    @Override
    default boolean isBanned(@NotNull ServerSideConnection connection) {
        if (this.bannedUntil().map(until -> !until.isAfter(LocalDateTime.now())).orElse(false)) {
            EssentialsSAPI.get().banManager().get().unban(this);
            return false;
        }
        try {
            NetworkInterface netInterface = NetworkInterface.getByInetAddress(connection.address().getAddress());
            byte[] macAddress = netInterface.getHardwareAddress();
            return Arrays.equals(this.macAddress(), macAddress);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

    }
}
