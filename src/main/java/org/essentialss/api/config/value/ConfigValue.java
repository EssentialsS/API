package org.essentialss.api.config.value;

import org.essentialss.api.config.SConfig;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

public interface ConfigValue<T> {

    @NotNull Object[] nodes();

    @SuppressWarnings("allow-nullable")
    @Nullable T parse(@NotNull ConfigurationNode root) throws SerializationException;

    @SuppressWarnings("allow-nullable")
    default @Nullable T parse(@NotNull SConfig config) throws SerializationException {
        try {
            return this.parse(config.configurationLoader().load());
        } catch (SerializationException e) {
            throw e;
        } catch (ConfigurateException e) {
            throw new RuntimeException(e);
        }
    }

    default void remove(@NotNull ConfigurationNode root) throws SerializationException {
        this.set(root, null);
    }

    void set(@NotNull ConfigurationNode root, @Nullable T value) throws SerializationException;
}
