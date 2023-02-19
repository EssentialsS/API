package org.essentialss.api.config;

import org.jetbrains.annotations.NotNull;

public interface ExternalLoadable<T> {

    @NotNull SerializablePart<T> loader();

}
