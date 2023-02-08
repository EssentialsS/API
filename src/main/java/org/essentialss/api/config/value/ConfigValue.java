package org.essentialss.api.config.value;

import org.essentialss.api.config.SConfig;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import javax.naming.ConfigurationException;

public interface ConfigValue<T> {

    @NotNull Object[] nodes();

    @SuppressWarnings("allow-nullable")
    @Nullable T parse(@NotNull ConfigurationNode root) throws SerializationException;

    void set(@NotNull ConfigurationNode root, @Nullable T value) throws SerializationException;

    default void remove(@NotNull ConfigurationNode root) throws SerializationException {
        this.set(root, null);
    }
}
