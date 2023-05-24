package org.essentialss.api.message.placeholder.common.player.chat;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.essentialss.api.message.placeholder.SPlaceHolders;
import org.jetbrains.annotations.NotNull;

public class MessagePlaceholder implements SPlaceHolder<Component> {

    public static final String TAG_NAME = "text";

    @NotNull
    private final String tag;
    @NotNull
    private final String name;

    public MessagePlaceholder() {
        this(SPlaceHolders.MESSAGE, TAG_NAME);
    }

    private MessagePlaceholder(@NotNull String tag, @NotNull String name) {
        this.tag = tag;
        this.name = name;
    }

    @Override
    @NotNull
    public Component apply(@NotNull Component message, @NotNull Component value) {
        return message.replaceText(TextReplacementConfig.builder().match(this.formattedPlaceholderTag()).replacement(value).build());
    }

    @Override
    @NotNull
    public SPlaceHolder<Component> copy(@NotNull String placeholderTag, @NotNull String placeholderName) {
        return new MessagePlaceholder(placeholderTag, placeholderName);
    }

    @Override
    @NotNull
    public String placeholderTagName() {
        return this.name;
    }

    @Override
    @NotNull
    public String placeholderTagType() {
        return this.tag;
    }

    @Override
    @NotNull
    public Class<?> type() {
        return Component.class;
    }
}
