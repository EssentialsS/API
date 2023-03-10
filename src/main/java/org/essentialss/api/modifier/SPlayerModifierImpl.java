package org.essentialss.api.modifier;

import org.essentialss.api.EssentialsSAPI;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Key;
import org.spongepowered.api.data.value.Value;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.plugin.PluginContainer;

import java.util.Optional;

class SPlayerModifierImpl<T> implements SPlayerModifier<T> {

    private final @NotNull String keyName;
    private final @NotNull Key<Value<T>> key;

    SPlayerModifierImpl(@NotNull String keyName, @NotNull Key<Value<T>> key) {
        this.key = key;
        this.keyName = keyName;
    }

    @Override
    public Optional<T> get(@NotNull Player target) {
        return target.get(this.key);
    }

    @Override
    public PluginContainer plugin() {
        return EssentialsSAPI.get().container();
    }

    @Override
    public void set(@NotNull Player target, @NotNull T value) {
        target.offer(this.key, value);
    }

    @Override
    public @NotNull String identifier() {
        return this.keyName;
    }
}
