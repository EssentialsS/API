package org.essentialss.api.message.placeholder.common.console;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.essentialss.api.message.placeholder.SPlaceHolders;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.SystemSubject;

public class ConsoleNicknamePlaceholder implements SPlaceHolder<SystemSubject> {

    public static final String TAG_NAME = "nickname";

    private final @NotNull String tag;
    private final @NotNull String name;

    public ConsoleNicknamePlaceholder() {
        this(SPlaceHolders.PLAYER, TAG_NAME);
    }

    public ConsoleNicknamePlaceholder(@NotNull String tag, @NotNull String name) {
        this.tag = tag;
        this.name = name;
    }

    @Override
    public @NotNull Component apply(@NotNull Component message, @NotNull SystemSubject player) {
        return message.replaceText(TextReplacementConfig.builder().match(this.formattedPlaceholderTag()).replacement("console").build());
    }

    @Override
    public @NotNull SPlaceHolder<SystemSubject> copy(@NotNull String placeholderTag, @NotNull String placeholderName) {
        return new ConsoleNicknamePlaceholder(placeholderTag, placeholderName);
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
    public @NotNull Class<SystemSubject> type() {
        return SystemSubject.class;
    }
}
