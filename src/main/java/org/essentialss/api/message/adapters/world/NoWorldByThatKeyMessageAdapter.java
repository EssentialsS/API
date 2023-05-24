package org.essentialss.api.message.adapters.world;

import net.kyori.adventure.text.Component;
import org.essentialss.api.message.adapters.MessageAdapter;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.ResourceKey;

public interface NoWorldByThatKeyMessageAdapter extends MessageAdapter {

    @NotNull
    Component adaptMessage(@NotNull Component messageToAdapt, @NotNull ResourceKey key);

    @NotNull
    default Component adaptMessage(@NotNull ResourceKey key) {
        return this.adaptMessage(this.defaultUnadaptedMessage(), key);
    }
}
