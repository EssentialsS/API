package org.essentialss.api.message.placeholder.common;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.essentialss.api.utils.FriendlyString;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class DurationPlaceholder implements SPlaceHolder<Duration> {

    public static final String TAG_NAME = "left";
    public static final String TAG_TYPE = "duration";

    private final @NotNull String tag;
    private final @NotNull String type;

    public DurationPlaceholder() {
        this(TAG_NAME, TAG_TYPE);
    }

    public DurationPlaceholder(@NotNull String tag, @NotNull String type) {
        this.tag = tag;
        this.type = type;
    }

    @Override
    public @NotNull Component apply(@NotNull Component message, @NotNull Duration value) {
        return message.replaceText(TextReplacementConfig.builder().match(this.formattedPlaceholderTag()).replacement(FriendlyString.toString(value)).build());
    }

    @Override
    public @NotNull SPlaceHolder<Duration> copyWithTagType(@NotNull String placeholderTag) {
        return this.copyWithTagType(placeholderTag, this.type);
    }

    @Override
    public @NotNull String placeholderTagName() {
        return this.tag;
    }

    @Override
    public @NotNull String placeholderTagType() {
        return this.type;
    }

    @Override
    public @NotNull Class<Duration> type() {
        return Duration.class;
    }

    public @NotNull SPlaceHolder<Duration> copyWithTagType(@NotNull String placeholderTag, @NotNull String placeholderType) {
        return this.copyWithTagType(placeholderTag, placeholderType);
    }
}
