package org.essentialss.api.player.data.module;

import org.essentialss.api.utils.identifier.StringIdentifier;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.plugin.PluginContainer;

import java.util.Optional;

public interface ModuleData<T> extends StringIdentifier {

    default ResourceKey key() {
        return ResourceKey.of(this.plugin(), this.identifier());
    }

    @NotNull PluginContainer plugin();

    Optional<T> value();

}
