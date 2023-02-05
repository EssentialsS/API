package org.essentialss.api.player.mail;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.util.Nameable;

@SuppressWarnings("allow-nullable")
public class MailMessageBuilder {

    private Object sender;
    private String senderDisplayName;
    private Component message;

    public @Nullable Object sender() {
        return this.sender;
    }

    public MailMessageBuilder sender(@NotNull Nameable player) {
        this.sender((Object) player);
        return this.senderDisplayName(player.name());
    }

    public MailMessageBuilder sender(@NotNull Object sender) {
        this.sender = sender;
        return this;
    }

    public @Nullable String senderDisplayName() {
        return this.senderDisplayName;
    }

    public MailMessageBuilder senderDisplayName(@NotNull String senderDisplayName) {
        this.senderDisplayName = senderDisplayName;
        return this;
    }

    public @Nullable Component message() {
        return this.message;
    }

    public @NotNull MailMessageBuilder message(Component message) {
        this.message = message;
        return this;
    }
}
