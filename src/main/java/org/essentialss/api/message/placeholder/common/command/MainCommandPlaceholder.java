package org.essentialss.api.message.placeholder.common.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.essentialss.api.message.placeholder.SPlaceHolders;
import org.jetbrains.annotations.NotNull;

public class MainCommandPlaceholder implements SPlaceHolder<String> {

    @NotNull
    public static final String TAG_NAME = "main";

    @NotNull
    private final String tagName;
    @NotNull
    private final String tagType;

    public MainCommandPlaceholder() {
        this(SPlaceHolders.COMMAND, TAG_NAME);
    }

    private MainCommandPlaceholder(@NotNull String tagType, @NotNull String tagName) {
        this.tagName = tagName;
        this.tagType = tagType;
    }

    @Override
    @NotNull
    public Component apply(@NotNull Component message, @NotNull String value) {
        return message.replaceText(TextReplacementConfig.builder().match(this.formattedPlaceholderTag()).replacement(value).build());
    }

    @Override
    @NotNull
    public SPlaceHolder<String> copy(@NotNull String placeholderTag, @NotNull String placeholderName) {
        return new MainCommandPlaceholder(placeholderTag, placeholderName);
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
        return String.class;
    }
}
