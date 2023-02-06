package org.essentialss.api;

import org.essentialss.api.player.SPlayerManager;
import org.essentialss.api.utils.Singleton;
import org.essentialss.api.world.SWorldManager;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.Sponge;

import java.lang.reflect.InvocationTargetException;

public interface EssentialsSAPI {

    @NotNull Singleton<SWorldManager> worldManager();

    @NotNull Singleton<SPlayerManager> playerManager();

    static EssentialsSAPI get() {
        return EssentialsSAPIGetter.API.get();
    }


}
