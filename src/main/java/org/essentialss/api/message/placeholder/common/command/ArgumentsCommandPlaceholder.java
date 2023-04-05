package org.essentialss.api.message.placeholder.common.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.essentialss.api.message.placeholder.SPlaceHolders;
import org.jetbrains.annotations.NotNull;

public class ArgumentsCommandPlaceholder implements SPlaceHolder<String> {

    public static final @NotNull String TAG_NAME = "arguments";

    private final @NotNull String tagName;
    private final @NotNull String tagType;

    public ArgumentsCommandPlaceholder() {
        this(SPlaceHolders.COMMAND, TAG_NAME);
    }

    public ArgumentsCommandPlaceholder(@NotNull String tagType, @NotNull String tagName) {
        this.tagName = tagName;
        this.tagType = tagType;
    }

    @Override
    public @NotNull Component apply(@NotNull Component message, @NotNull String value) {
        return message.replaceText(TextReplacementConfig.builder().match(this.formattedPlaceholderTag()).replacement(value).build());
    }

    @Override
    public @NotNull SPlaceHolder<String> copy(@NotNull String placeholderTag, @NotNull String placeholderName) {
        return new ArgumentsCommandPlaceholder(placeholderTag, placeholderName);
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
        return String.class;
    }
}
