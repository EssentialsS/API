package org.essentialss.api.message.adapters.world.create;

import net.kyori.adventure.text.Component;
import org.essentialss.api.message.adapters.MessageAdapter;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.world.server.ServerWorld;

public interface CreatedWorldMessageAdapter extends MessageAdapter {

    Component adaptMessage(@NotNull Component messageToAdapt, @NotNull ServerWorld world);

    default Component adaptMessage(@NotNull ServerWorld world) {
        return this.adaptMessage(this.defaultUnadaptedMessage(), world);
    }

}
