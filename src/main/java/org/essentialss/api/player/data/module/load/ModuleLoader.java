package org.essentialss.api.player.data.module.load;

import org.essentialss.api.player.data.module.SerializableModuleData;
import org.essentialss.api.utils.identifier.StringIdentifier;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.plugin.PluginContainer;

public interface ModuleLoader<T, M extends SerializableModuleData<T>> extends StringIdentifier {

    @NotNull M load(@NotNull ConfigurationNode node) throws ConfigurateException;

    @NotNull PluginContainer plugin();

    void save(@NotNull M module, @NotNull ConfigurationNode node) throws ConfigurateException;

}
