package org.essentialss.api.message.adapters.player.command.mute;

import net.kyori.adventure.text.Component;
import org.essentialss.api.message.MuteType;
import org.essentialss.api.message.adapters.MessageAdapter;
import org.jetbrains.annotations.NotNull;

public interface YouAreNowMutedMessageAdapter extends MessageAdapter {

    @NotNull
    Component adaptMessage(@NotNull Component message, @NotNull MuteType... types);

    @NotNull
    default Component adaptMessage(@NotNull MuteType... types) {
        return this.adaptMessage(this.unadaptedMessage(), types);
    }
}
