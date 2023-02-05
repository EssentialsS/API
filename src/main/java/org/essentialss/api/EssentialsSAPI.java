package org.essentialss.api;

import org.essentialss.api.player.SPlayerManager;
import org.essentialss.api.utils.Singleton;
import org.essentialss.api.world.SWorldManager;
import org.jetbrains.annotations.NotNull;

public interface EssentialsSAPI {

    @NotNull Singleton<SWorldManager> worldManager();

    @NotNull Singleton<SPlayerManager> playerManager();

}
