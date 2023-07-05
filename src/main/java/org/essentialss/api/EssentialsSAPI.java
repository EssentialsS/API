package org.essentialss.api;

import org.essentialss.api.ban.SBanManager;
import org.essentialss.api.config.SConfigManager;
import org.essentialss.api.kit.KitManager;
import org.essentialss.api.message.MessageManager;
import org.essentialss.api.player.SPlayerManager;
import org.essentialss.api.utils.Singleton;
import org.essentialss.api.utils.parameter.ParameterAdapter;
import org.essentialss.api.world.SWorldManager;
import org.essentialss.api.group.GroupManager;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.plugin.PluginContainer;

import java.util.Collection;

public interface EssentialsSAPI {

    @NotNull
    Singleton<SBanManager> banManager();

    @NotNull
    Singleton<SConfigManager> configManager();

    @NotNull
    PluginContainer container();

    @NotNull
    Singleton<GroupManager> groupManager();

    @NotNull
    Singleton<KitManager> kitManager();

    @NotNull
    Singleton<MessageManager> messageManager();

    @NotNull
    Collection<ParameterAdapter> parameterAdapters();

    @NotNull
    Singleton<SPlayerManager> playerManager();

    @NotNull
    Singleton<SWorldManager> worldManager();

    static EssentialsSAPI get() {
        return EssentialsSAPIGetter.API.get();
    }


}
