package org.essentialss.api.player;

import org.essentialss.api.EssentialsSAPI;
import org.essentialss.api.player.data.SGeneralOfflineData;
import org.essentialss.api.player.data.SGeneralPlayerData;
import org.essentialss.api.player.data.SGeneralUnloadedData;
import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.api.user.UserManager;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public interface SPlayerManager {

    @NotNull SGeneralPlayerData dataFor(@NotNull Player player);

    @NotNull SGeneralOfflineData dataFor(@NotNull User user);

    @NotNull SGeneralUnloadedData dataFor(@NotNull GameProfile profile);

    default UnmodifiableCollection<SGeneralUnloadedData> unloadedDataForAll() {
        if (!Sponge.isServerAvailable()) {
            return new UnmodifiableCollection<>(Collections.emptyList());
        }
        UserManager manager = Sponge.server().userManager();
        return new UnmodifiableCollection<>(manager.streamAll().map(this::dataFor).collect(Collectors.toList()));
    }

    default CompletableFuture<UnmodifiableCollection<SGeneralOfflineData>> userDataForAll() {
        if (!Sponge.isServerAvailable()) {
            CompletableFuture<UnmodifiableCollection<SGeneralOfflineData>> future = new CompletableFuture<>();
            future.complete(new UnmodifiableCollection<>(Collections.emptyList()));
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
                Optional<SGeneralPlayerData> opPlayerData = user
                        .player()
                        .map(player -> EssentialsSAPI.get().playerManager().get().dataFor(player));

                SGeneralOfflineData userData = opPlayerData
                        .map(playerData -> (SGeneralOfflineData) playerData)
                        .orElseGet(() -> EssentialsSAPI.get().playerManager().get().dataFor(user));
                ret.add(userData);
            }
            return new UnmodifiableCollection<>(ret);
        });
    }

}
