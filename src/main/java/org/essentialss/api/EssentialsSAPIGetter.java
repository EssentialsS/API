package org.essentialss.api;

import org.essentialss.api.utils.Singleton;
import org.spongepowered.api.Sponge;

import java.lang.reflect.InvocationTargetException;

public class EssentialsSAPIGetter {

    static final String THIS_PLUGIN_ID = "essentials-s";

    static final Singleton<EssentialsSAPI> API = new Singleton<>(() -> {
        return (EssentialsSAPI) Sponge
                .pluginManager()
                .plugin(THIS_PLUGIN_ID)
                .orElseThrow(() -> new RuntimeException("Cannot find plugin with id of " + THIS_PLUGIN_ID))
                .instance();
    });
}
