package org.essentialss.api.player;

import org.essentialss.api.player.data.SGeneralOfflineData;
import org.essentialss.api.player.data.SGeneralPlayerData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;

public interface SPlayerManager {

    @NotNull SGeneralPlayerData dataFor(@NotNull Player player);

    @NotNull SGeneralOfflineData dataFor(@NotNull User user);

}
