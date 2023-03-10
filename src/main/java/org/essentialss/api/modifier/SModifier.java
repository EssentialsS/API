package org.essentialss.api.modifier;

import org.essentialss.api.utils.identifier.StringIdentifier;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.plugin.PluginContainer;

import java.util.Optional;

public interface SModifier<T, V> extends StringIdentifier {

    Optional<V> get(@NotNull T target);

    default boolean hasApplied(@NotNull T target) {
        return this.get(target).isPresent();
    }

    default ResourceKey key() {
        return ResourceKey.of(this.plugin(), this.identifier());
    }

    PluginContainer plugin();

    void set(@NotNull T target, @NotNull V value);

}
