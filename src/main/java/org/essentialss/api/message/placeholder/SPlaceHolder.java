package org.essentialss.api.message.placeholder;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.jetbrains.annotations.NotNull;

public interface SPlaceHolder<T> {

    @NotNull Component apply(@NotNull Component message, @NotNull T value);

    default boolean canApply(@NotNull Component component) {
        return PlainTextComponentSerializer.plainText().serialize(component).contains(this.formattedPlaceholderTag());
    }

    @NotNull SPlaceHolder<T> copyWithTagType(@NotNull String placeholderTag);

    default String formattedPlaceholderTag() {
        return "%" + this.placeholderTag() + "%";
    }

    default String placeholderTag() {
        return this.placeholderTagType() + " " + this.placeholderTagName();
    }

    @NotNull String placeholderTagName();

    @NotNull String placeholderTagType();

    @NotNull Class<T> type();

}
