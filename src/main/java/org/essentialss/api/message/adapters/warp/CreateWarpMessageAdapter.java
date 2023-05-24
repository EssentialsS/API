package org.essentialss.api.message.adapters.warp;

import net.kyori.adventure.text.Component;
import org.essentialss.api.message.adapters.MessageAdapter;
import org.essentialss.api.world.points.warp.SWarp;
import org.jetbrains.annotations.NotNull;

public interface CreateWarpMessageAdapter extends MessageAdapter {

    @NotNull
    Component adaptMessage(@NotNull Component messageToAdapt, @NotNull SWarp warp);

    @NotNull
    default Component adaptMessage(@NotNull SWarp warp) {
        return this.adaptMessage(this.defaultUnadaptedMessage(), warp);
    }
}
