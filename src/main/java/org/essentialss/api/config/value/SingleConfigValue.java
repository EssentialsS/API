package org.essentialss.api.config.value;

public interface SingleConfigValue<T> extends ConfigValue<T> {

    interface Default<T> extends SingleConfigValue<T>, DefaultConfigValue<T> {

    }
}
