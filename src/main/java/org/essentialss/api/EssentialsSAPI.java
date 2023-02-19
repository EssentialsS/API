package org.essentialss.api;

import org.essentialss.api.ban.SBanManager;
import org.essentialss.api.config.SConfigManager;
import org.essentialss.api.player.SPlayerManager;
import org.essentialss.api.utils.Singleton;
import org.essentialss.api.world.SWorldManager;
import org.jetbrains.annotations.NotNull;

public interface EssentialsSAPI {

    @NotNull Singleton<SWorldManager> worldManager();

    @NotNull Singleton<SPlayerManager> playerManager();

    @NotNull Singleton<SConfigManager> configManager();

    @NotNull Singleton<SBanManager> banManager();

    static EssentialsSAPI get() {
        return EssentialsSAPIGetter.API.get();
    }


}
