package org.essentialss.api.config.value;

import org.essentialss.api.config.SConfig;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ConfigValue<T> {

    @NotNull Object[] nodes();

    @SuppressWarnings("allow-nullable")
    @Nullable T parse(@NotNull SConfig config);

    void set(@NotNull SConfig config, @Nullable T value);

    default void remove(@NotNull SConfig config) {
        this.set(config, null);
    }
}
