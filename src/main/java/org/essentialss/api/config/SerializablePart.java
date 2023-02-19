package org.essentialss.api.config;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;

public interface SerializablePart<T> {

    void saveTo(@NotNull ConfigurationNode node, @Nullable T obj) throws ConfigurateException;

    @SuppressWarnings("allow-nullable")
    @Nullable T loadFrom(@NotNull ConfigurationNode node) throws ConfigurateException;

}
