package org.essentialss.api.player;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.entity.living.player.Player;

public interface SPlayerManager {

    @NotNull SPlayerData dataFor(@NotNull Player player);

}
