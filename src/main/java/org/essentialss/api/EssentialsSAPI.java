package org.essentialss.api;

import org.essentialss.api.ban.SBanManager;
import org.essentialss.api.config.SConfigManager;
import org.essentialss.api.message.MessageManager;
import org.essentialss.api.player.SPlayerManager;
import org.essentialss.api.utils.Singleton;
import org.essentialss.api.world.SWorldManager;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.plugin.PluginContainer;

public interface EssentialsSAPI {

    @NotNull Singleton<SBanManager> banManager();

    @NotNull Singleton<SConfigManager> configManager();

    @NotNull PluginContainer container();

    @NotNull Singleton<MessageManager> messageManager();

    @NotNull Singleton<SPlayerManager> playerManager();

    @NotNull Singleton<SWorldManager> worldManager();

    static EssentialsSAPI get() {
        return EssentialsSAPIGetter.API.get();
    }


}
