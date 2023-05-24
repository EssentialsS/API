package org.essentialss.api.message;

import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class MessageData {

    @NotNull
    private final Component message;
    @NotNull
    private final MessageType type = MessageType.CHAT;


    public MessageData(@NotNull Component message) {
        this.message = message;
    }

    @NotNull
    public Component formattedMessage() {
        //TODO format
        return this.message;
    }

    @NotNull
    public Component originalMessage() {
        return this.message;
    }


}
