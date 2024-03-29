package org.essentialss.api.config.value;

import org.jetbrains.annotations.NotNull;

public class ConfigValueWrapper {

    @NotNull
    private final ConfigValue<?> configValue;

    public ConfigValueWrapper(@NotNull ConfigValue<?> configValue) {
        this.configValue = configValue;
    }

    public ConfigValue<?> get() {
        return this.configValue;
    }

}
