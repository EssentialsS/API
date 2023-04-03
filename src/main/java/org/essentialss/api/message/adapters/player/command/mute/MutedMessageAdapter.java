package org.essentialss.api.message.adapters.player.command.mute;

import net.kyori.adventure.text.Component;
import org.essentialss.api.message.MuteType;
import org.essentialss.api.message.adapters.MessageAdapter;
import org.essentialss.api.player.data.SGeneralUnloadedData;
import org.jetbrains.annotations.NotNull;

public interface MutedMessageAdapter extends MessageAdapter {

    @NotNull Component adaptMessage(@NotNull Component message, @NotNull SGeneralUnloadedData playerData, @NotNull MuteType... types);

    default @NotNull Component adaptMessage(@NotNull SGeneralUnloadedData playerData, @NotNull MuteType... types) {
        return this.adaptMessage(this.defaultUnadaptedMessage(), playerData, types);
    }

}
