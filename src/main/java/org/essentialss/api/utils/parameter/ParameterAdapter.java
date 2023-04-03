package org.essentialss.api.utils.parameter;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.command.parameter.Parameter;

public interface ParameterAdapter {

    <T> Parameter.Value.Builder<T> builder();

    @NotNull Class<?>[] types();

}
