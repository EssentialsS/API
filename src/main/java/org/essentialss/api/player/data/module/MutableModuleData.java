package org.essentialss.api.player.data.module;

import org.jetbrains.annotations.Nullable;

public interface MutableModuleData<T> extends ModuleData<T> {

    void set(@Nullable T value);

}
