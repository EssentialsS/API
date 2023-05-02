package org.essentialss.api.player;

import org.essentialss.api.EssentialsSAPI;
import org.essentialss.api.player.data.SGeneralOfflineData;
import org.essentialss.api.player.data.SGeneralPlayerData;
import org.essentialss.api.player.data.SGeneralUnloadedData;
import org.essentialss.api.player.data.module.load.ModuleLoader;
import org.essentialss.api.utils.arrays.impl.SingleUnmodifiableCollection;
import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.api.user.UserManager;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public interface SPlayerManager {

    default UnmodifiableCollection<SGeneralPlayerData> allPlayerData() {
        if (!Sponge.isServerAvailable()) {
            if (Sponge.isClientAvailable()) {
                return Sponge
                        .client()
                        .player()
                        .map(player -> new SingleUnmodifiableCollection<>(Collections.singleton(this.dataFor(player))))
                        .orElseGet(() -> new SingleUnmodifiableCollection<>(Collections.emptyList()));
            }
            return new SingleUnmodifiableCollection<>(Collections.emptyList());
        }
        return new SingleUnmodifiableCollection<>(Sponge.server().onlinePlayers().stream().map(this::dataFor).collect(Collectors.toList()));
    }

    @NotNull SGeneralOfflineData dataFor(@NotNull User user);

    @NotNull SGeneralUnloadedData dataFor(@NotNull GameProfile profile);

    default Optional<SGeneralUnloadedData> dataFor(@NotNull UUID uuid) {
        if (!Sponge.isServerAvailable()) {
            return Optional.empty();
        }
        return Sponge.server().gameProfileManager().cache().findById(uuid).map(this::dataFor);
    }

    @NotNull SGeneralPlayerData dataFor(@NotNull Player player);

    @NotNull Collection<ModuleLoader<?, ?>> dataLoaders();

    void register(@NotNull ModuleLoader<?, ?> loader);

    default UnmodifiableCollection<SGeneralUnloadedData> unloadedDataForAll() {
        if (!Sponge.isServerAvailable()) {
            return new SingleUnmodifiableCollection<>(Collections.emptyList());
        }
        UserManager manager = Sponge.server().userManager();
        return new SingleUnmodifiableCollection<>(manager.streamAll().map(this::dataFor).collect(Collectors.toList()));
    }

    default CompletableFuture<UnmodifiableCollection<SGeneralOfflineData>> userDataForAll() {
        if (!Sponge.isServerAvailable()) {
            CompletableFuture<UnmodifiableCollection<SGeneralOfflineData>> future = new CompletableFuture<>();
            future.complete(new SingleUnmodifiableCollection<>(Collections.emptyList()));
            return future;
        }
        UserManager manager = Sponge.server().userManager();
        CompletableFuture[] users = manager.streamAll().map(manager::load).toArray(CompletableFuture[]::new);
        return CompletableFuture.allOf(users).thenApply(v -> {
            Collection<SGeneralOfflineData> ret = new LinkedHashSet<>();
            for (CompletableFuture future : users) {
                Optional<User> opUser;
                try {
                    opUser = (Optional<User>) future.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
                if (!opUser.isPresent()) {
                    continue;
                }
                User user = opUser.get();
                Optional<SGeneralPlayerData> opPlayerData = user.player().map(player -> EssentialsSAPI.get().playerManager().get().dataFor(player));

                SGeneralOfflineData userData = opPlayerData
                        .map(playerData -> (SGeneralOfflineData) playerData)
                        .orElseGet(() -> EssentialsSAPI.get().playerManager().get().dataFor(user));
                ret.add(userData);
            }
            return new SingleUnmodifiableCollection<>(ret);
        });
    }

}
