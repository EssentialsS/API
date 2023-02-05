package org.essentialss.api.utils.identifier;

import org.jetbrains.annotations.NotNull;

public interface ObjectSender {

    @NotNull Object sender();

    @NotNull String senderDisplayName();

}
