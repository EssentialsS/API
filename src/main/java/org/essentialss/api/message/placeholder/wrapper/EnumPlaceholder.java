package org.essentialss.api.message.placeholder.wrapper;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.EnumSet;

@SuppressWarnings("i-am-message-adapter")
public class EnumPlaceholder<E extends Enum<E>> implements SPlaceHolder<E> {

    @NotNull
    private final String tagName;
    @NotNull
    private final String tagType;
    @NotNull
    private final EnumSet<E> value;

    public EnumPlaceholder(String tagType, String tagName, Class<E> clazz) {
        this(tagType, tagName, EnumSet.allOf(clazz));
    }

    public EnumPlaceholder(String tagType, String tagName, Collection<E> value) {
        this(tagType, tagName, EnumSet.copyOf(value));
    }

    private EnumPlaceholder(@NotNull String tagType, @NotNull String tagName, @NotNull EnumSet<E> value) {
        this.value = value;
        this.tagName = tagName;
        this.tagType = tagType;
    }

    @Override
    @NotNull
    public Component apply(@NotNull Component message, @NotNull E value) {
        return message.replaceText(
                TextReplacementConfig.builder().match(this.formattedPlaceholderTag()).replacement(Component.text(value.name().toLowerCase())).build());
    }

    @Override
    @NotNull
    public SPlaceHolder<E> copy(@NotNull String placeholderTag, @NotNull String placeholderName) {
        return new EnumPlaceholder<>(placeholderTag, placeholderName, this.value);
    }

    @Override
    @NotNull
    public String placeholderTagName() {
        return this.tagName;
    }

    @Override
    @NotNull
    public String placeholderTagType() {
        return this.tagType;
    }

    @Override
    @NotNull
    public Class<?> type() {
        return this.value.iterator().next().getDeclaringClass();
    }
}
