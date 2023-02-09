package org.essentialss.api.config.value;

import org.essentialss.api.config.SConfig;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

public interface DefaultConfigValue<T> extends ConfigValue<T> {

    T defaultValue();

    default @NotNull T parseDefault(@NotNull ConfigurationNode root) {
        T value;
        try {
            value = this.parse(root);
        } catch (SerializationException e) {
            e.printStackTrace();
            return this.defaultValue();
        }
        if (null == value) {
            return this.defaultValue();
        }
        return value;
    }

    @SuppressWarnings("allow-nullable")
    default @NotNull T parseDefault(@NotNull SConfig config) {
        try {
            return this.parseDefault(config.configurationLoader().load());
        } catch (ConfigurateException e) {
            e.printStackTrace();
            return this.defaultValue();
        }
    }

}
