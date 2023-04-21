package org.essentialss.api.message.placeholder.common.player.chat;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.essentialss.api.message.placeholder.SPlaceHolders;
import org.jetbrains.annotations.NotNull;

public class MessagePlaceholder implements SPlaceHolder<Component> {

    public static final String TAG_NAME = "text";

    private final @NotNull String tag;
    private final @NotNull String name;

    public MessagePlaceholder() {
        this(SPlaceHolders.MESSAGE, TAG_NAME);
    }

    public MessagePlaceholder(@NotNull String tag, @NotNull String name) {
        this.tag = tag;
        this.name = name;
    }

    @Override
    public @NotNull Component apply(@NotNull Component message, @NotNull Component value) {
        return message.replaceText(TextReplacementConfig.builder().match(this.formattedPlaceholderTag()).replacement(value).build());
    }

    @Override
    public @NotNull SPlaceHolder<Component> copy(@NotNull String placeholderTag, @NotNull String placeholderName) {
        return new MessagePlaceholder(placeholderTag, placeholderName);
    }

    @Override
    public @NotNull String placeholderTagName() {
        return this.name;
    }

    @Override
    public @NotNull String placeholderTagType() {
        return this.tag;
    }

    @Override
    public @NotNull Class<?> type() {
        return Component.class;
    }
}
