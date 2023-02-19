package org.essentialss.api.message;

import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class MessageData {

    private final @NotNull Component message;
    private @NotNull MessageType type = MessageType.CHAT;


    public MessageData(@NotNull Component message) {
        this.message = message;
    }

    public @NotNull Component originalMessage() {
        return this.message;
    }

    public @NotNull Component formattedMessage() {
        //TODO format
        return this.message;
    }


}
