package org.essentialss.api.config.value;

import java.util.List;

public interface CollectionConfigValue<T> extends ConfigValue<List<T>> {

    interface Default<T> extends CollectionConfigValue<T>, DefaultConfigValue<List<T>> {

    }
}
