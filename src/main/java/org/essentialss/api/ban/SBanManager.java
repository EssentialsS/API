package org.essentialss.api.ban;

import org.essentialss.api.ban.data.AccountBanData;
import org.essentialss.api.ban.data.BanData;
import org.essentialss.api.ban.data.IPBanData;
import org.essentialss.api.ban.data.MacAddressBanData;
import org.essentialss.api.config.BanConfig;
import org.essentialss.api.config.Serializable;
import org.essentialss.api.utils.Singleton;
import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.network.ServerSideConnection;
import org.spongepowered.api.profile.GameProfile;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public interface SBanManager extends Serializable {

    UnmodifiableCollection<BanData<?>> banData();

    Singleton<BanConfig> banConfig();

    AccountBanData banAccount(@NotNull GameProfile account, @Nullable LocalDateTime dateTime);

    IPBanData banIp(@NotNull String hostname, @Nullable String lastKnown, @Nullable LocalDateTime dateTime);

    MacAddressBanData banMacAddress(byte[] address, @Nullable String lastKnown, @Nullable LocalDateTime dateTime);

    void unban(@NotNull BanData<?> data);

    default Collection<BanData<?>> banByAll(@NotNull ServerPlayer player) throws SocketException {
        return this.banByAll(player, null);
    }

    default Collection<BanData<?>> banByAll(@NotNull ServerPlayer player, @Nullable LocalDateTime dateTime)
            throws SocketException {
        AccountBanData account = this.banAccount(player.profile(), dateTime);
        IPBanData ipBan = this.banIp(player.connection(), dateTime);
        MacAddressBanData macBan = this.banMacAddress(player.connection(), dateTime);
        return Arrays.asList(account, ipBan, macBan);
    }

    default AccountBanData banAccount(@NotNull GameProfile account) {
        return this.banAccount(account, null);
    }

    default IPBanData banIp(@NotNull String hostname) {
        return this.banIp(hostname, null);
    }

    default IPBanData banIp(@NotNull String hostname, @Nullable String lastKnown) {
        return this.banIp(hostname, lastKnown, null);
    }

    default IPBanData banIp(@NotNull ServerSideConnection player, @Nullable LocalDateTime until) {
        return this.banIp(player.address().getHostName(), player.profile().name().orElse(null), until);
    }

    default IPBanData banIp(@NotNull ServerSideConnection player) {
        return this.banIp(player, null);
    }

    default MacAddressBanData banMacAddress(byte[] address) {
        return this.banMacAddress(address, null);
    }

    default MacAddressBanData banMacAddress(byte[] address, @Nullable String lastKnown) {
        return this.banMacAddress(address, lastKnown, null);
    }

    default MacAddressBanData banMacAddress(@NotNull ServerSideConnection player) throws SocketException {
        return this.banMacAddress(player, null);
    }

    default MacAddressBanData banMacAddress(@NotNull ServerSideConnection player, @Nullable LocalDateTime until)
            throws SocketException {
        NetworkInterface netInterface = NetworkInterface.getByInetAddress(player.address().getAddress());
        byte[] address = netInterface.getHardwareAddress();
        return this.banMacAddress(address, player.profile().name().orElse(null), until);
    }

    default boolean isBanned(@NotNull ServerSideConnection connection) {
        return this.banData().parallelStream().anyMatch(data -> data.isBanned(connection));
    }

    default <B extends BanData<?>> UnmodifiableCollection<B> banData(Class<B> clazz) {
        return new UnmodifiableCollection<>(
                this.banData().stream().filter(clazz::isInstance).map(data -> (B) data).collect(Collectors.toList()));
    }

    default UnmodifiableCollection<BanData<?>> banData(@NotNull ServerSideConnection connection) {
        return new UnmodifiableCollection<>(
                this.banData().parallelStream().filter(data -> data.isBanned(connection)).collect(Collectors.toList()));
    }

}
