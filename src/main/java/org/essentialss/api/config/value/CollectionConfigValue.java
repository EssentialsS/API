package org.essentialss.api.config.value;

import org.essentialss.api.config.SConfig;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface CollectionConfigValue<T> extends ConfigValue<List<T>> {

    @Override
    List<T> parse(@NotNull SConfig config);

    interface Default<T> extends CollectionConfigValue<T>, DefaultConfigValue<List<T>> {

    }
}
