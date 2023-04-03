package org.essentialss.api.utils.friendly;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public interface FriendlyString {

    @NotNull Class<?>[] supportedTypes();

    @NotNull Component toFriendlyComponent(@NotNull Object value);

    @NotNull String toFriendlyString(@NotNull Object value);

}
