package org.essentialss.api.player.data.module;

import org.essentialss.api.player.data.module.load.ModuleLoader;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.plugin.PluginContainer;

public interface SerializableModuleData<T> extends ModuleData<T> {

    @Override
    default @NotNull String identifier() {
        return this.loader().identifier();
    }

    @NotNull ModuleLoader<T, ? extends SerializableModuleData<T>> loader();

    @Override
    default @NotNull PluginContainer plugin() {
        return this.loader().plugin();
    }
}
