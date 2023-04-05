package org.essentialss.api.message.placeholder.common;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.essentialss.api.utils.friendly.FriendlyStrings;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class DurationPlaceholder implements SPlaceHolder<Duration> {

    public static final String TAG_NAME = "left";
    public static final String TAG_TYPE = "duration";

    private final @NotNull String tag;
    private final @NotNull String name;

    public DurationPlaceholder() {
        this(TAG_NAME, TAG_TYPE);
    }

    public DurationPlaceholder(@NotNull String tag, @NotNull String name) {
        this.tag = tag;
        this.name = name;
    }

    @Override
    public @NotNull Component apply(@NotNull Component message, @NotNull Duration value) {
        return message.replaceText(
                TextReplacementConfig.builder().match(this.formattedPlaceholderTag()).replacement(FriendlyStrings.DURATION.toFriendlyString(value)).build());
    }

    @Override
    public @NotNull SPlaceHolder<Duration> copy(@NotNull String placeholderTag, @NotNull String placeholderType) {
        return new DurationPlaceholder(placeholderTag, placeholderType);
    }

    @Override
    public @NotNull String placeholderTagName() {
        return this.tag;
    }

    @Override
    public @NotNull String placeholderTagType() {
        return this.name;
    }

    @Override
    public @NotNull Class<Duration> type() {
        return Duration.class;
    }
}
