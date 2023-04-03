package org.essentialss.api.message.placeholder.wrapper;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.EnumSet;

public class EnumPlaceholder<E extends Enum<E>> implements SPlaceHolder<E> {

    private final @NotNull String tagName;
    private final @NotNull String tagType;
    private final @NotNull EnumSet<E> value;

    public EnumPlaceholder(String tagType, String tagName, Class<E> clazz) {
        this(tagType, tagName, EnumSet.allOf(clazz));
    }

    public EnumPlaceholder(String tagType, String tagName, Collection<E> value) {
        this(tagType, tagName, EnumSet.copyOf(value));
    }

    public EnumPlaceholder(@NotNull String tagType, @NotNull String tagName, @NotNull EnumSet<E> value) {
        this.value = value;
        this.tagName = tagName;
        this.tagType = tagType;
    }

    @Override
    public @NotNull Component apply(@NotNull Component message, @NotNull E value) {
        return message.replaceText(
                TextReplacementConfig.builder().match(this.formattedPlaceholderTag()).replacement(Component.text(value.name().toLowerCase())).build());
    }

    @Override
    public @NotNull SPlaceHolder<E> copy(@NotNull String placeholderTag, @NotNull String placeholderName) {
        return new EnumPlaceholder<>(placeholderTag, placeholderName, this.value);
    }

    @Override
    public @NotNull String placeholderTagName() {
        return this.tagName;
    }

    @Override
    public @NotNull String placeholderTagType() {
        return this.tagType;
    }

    @Override
    public @NotNull Class<?> type() {
        return this.value.iterator().next().getDeclaringClass();
    }
}
