package org.essentialss.api.config.value;

import org.essentialss.api.config.SConfig;
import org.jetbrains.annotations.NotNull;

public interface SingleConfigValue<T> extends ConfigValue<T> {

    interface Default<T> extends SingleConfigValue<T>, DefaultConfigValue<T> {

    }
}
