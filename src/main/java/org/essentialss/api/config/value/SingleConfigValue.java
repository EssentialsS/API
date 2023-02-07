package org.essentialss.api.config.value;

import org.essentialss.api.config.SConfig;
import org.jetbrains.annotations.NotNull;

public interface SingleConfigValue<T> extends ConfigValue<T> {

    @Override
    T parse(@NotNull SConfig config);

    interface Default<T> extends SingleConfigValue<T>, DefaultConfigValue<T> {

    }
}
