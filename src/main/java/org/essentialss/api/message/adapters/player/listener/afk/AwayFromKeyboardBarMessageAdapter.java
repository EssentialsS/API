package org.essentialss.api.message.adapters.player.listener.afk;

import net.kyori.adventure.text.Component;
import org.essentialss.api.message.adapters.MessageAdapter;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public interface AwayFromKeyboardBarMessageAdapter extends MessageAdapter.Enabled {

    @NotNull
    Component adaptMessage(@NotNull Component messageToAdapt, @NotNull Duration timeLeft);

    @NotNull
    default Component adaptMessage(@NotNull Duration timeLeft) {
        return this.adaptMessage(this.unadaptedMessage(), timeLeft);
    }

}
