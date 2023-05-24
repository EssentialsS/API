package org.essentialss.api.message.adapters.world.unload;

import net.kyori.adventure.text.Component;
import org.essentialss.api.message.adapters.MessageAdapter;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.ResourceKey;

public interface UnloadedWorldMessageAdapter extends MessageAdapter {

    Component adaptMessage(@NotNull Component messageToAdapt, @NotNull ResourceKey worldKey);

    default Component adaptMessage(@NotNull ResourceKey worldKey) {
        return this.adaptMessage(this.defaultUnadaptedMessage(), worldKey);
    }

}
