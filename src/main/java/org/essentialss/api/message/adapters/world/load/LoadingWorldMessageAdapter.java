package org.essentialss.api.message.adapters.world.load;

import net.kyori.adventure.text.Component;
import org.essentialss.api.message.adapters.MessageAdapter;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.ResourceKey;

public interface LoadingWorldMessageAdapter extends MessageAdapter {

    Component adaptMessage(@NotNull Component messageToAdapt, @NotNull ResourceKey worldKey);

    default Component adaptMessage(@NotNull ResourceKey key) {
        return this.adaptMessage(this.defaultUnadaptedMessage(), key);
    }

}
