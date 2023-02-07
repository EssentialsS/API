package org.essentialss.api.config.value;

import org.essentialss.api.config.SConfig;
import org.jetbrains.annotations.NotNull;

public interface DefaultConfigValue<T> extends ConfigValue<T>{

    T defaultValue();

    default @NotNull T parseDefault(@NotNull SConfig config){
        T value = this.parse(config);
        if(null == value){
            return this.defaultValue();
        }
        return value;
    }

}
