package org.essentialss.api.player.mail;

import net.kyori.adventure.text.Component;
import org.essentialss.api.utils.identifier.ObjectSender;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public interface MailMessage extends ObjectSender {

    @NotNull Component message();

    @NotNull LocalDateTime sentAt();

}
