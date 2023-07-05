package org.essentialss.api;

import org.essentialss.api.utils.Singleton;
import org.spongepowered.api.Sponge;

final class EssentialsSAPIGetter {

    private static final String THIS_PLUGIN_ID = "essentials-s";

    static final Singleton<EssentialsSAPI> API = new Singleton<>(() -> (EssentialsSAPI) Sponge
            .pluginManager()
            .plugin(THIS_PLUGIN_ID)
            .orElseThrow(() -> new RuntimeException("Cannot find plugin with id of " + THIS_PLUGIN_ID))
            .instance());

    private EssentialsSAPIGetter() {
        throw new RuntimeException("Should not create");
    }
}
