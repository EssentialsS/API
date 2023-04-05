package org.essentialss.api.message.adapters.player.listener.spy;

import net.kyori.adventure.text.Component;
import org.essentialss.api.message.adapters.MessageAdapter;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.service.permission.Subject;

public interface CommandSpyMessageAdapter extends MessageAdapter {

    @NotNull Component adaptMessage(@NotNull Component messageToAdapt, @NotNull Subject commandSender, @NotNull String command, @NotNull String arguments);

    default @NotNull Component adaptMessage(@NotNull Subject subject, @NotNull String command, @NotNull String arguments) {
        return this.adaptMessage(this.unadaptedMessage(), subject, command, arguments);
    }

}
