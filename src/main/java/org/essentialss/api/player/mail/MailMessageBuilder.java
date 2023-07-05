package org.essentialss.api.player.mail;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.util.Nameable;

@SuppressWarnings("allow-nullable")
public class MailMessageBuilder {

    private Component message;
    private Object sender;
    private String senderDisplayName;

    @Nullable
    public Component message() {
        return this.message;
    }

    @NotNull
    public MailMessageBuilder message(Component message) {
        this.message = message;
        return this;
    }

    public MailMessageBuilder sender(@NotNull Object sender) {
        this.sender = sender;
        return this;
    }

    @Nullable
    public Object sender() {
        return this.sender;
    }

    public MailMessageBuilder sender(@NotNull Nameable player) {
        this.sender((Object) player);
        return this.senderDisplayName(player.name());
    }

    @Nullable
    public String senderDisplayName() {
        return this.senderDisplayName;
    }

    public MailMessageBuilder senderDisplayName(@NotNull String senderDisplayName) {
        this.senderDisplayName = senderDisplayName;
        return this;
    }
}
