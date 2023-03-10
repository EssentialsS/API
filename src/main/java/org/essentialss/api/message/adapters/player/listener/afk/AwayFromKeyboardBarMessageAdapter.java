package org.essentialss.api.message.adapters.player.listener.afk;

import net.kyori.adventure.text.Component;
import org.essentialss.api.message.placeholder.MessageAdapter;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public interface AwayFromKeyboardBarMessageAdapter extends MessageAdapter {

    @NotNull Component adaptMessage(@NotNull Component messageToAdapt, @NotNull Duration timeLeft);

    default @NotNull Component adaptMessage(@NotNull Duration timeLeft) {
        return this.adaptMessage(this.defaultUnadaptedMessage(), timeLeft);
    }

}
