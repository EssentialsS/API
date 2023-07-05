package org.essentialss.api.message.placeholder.common.console;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.essentialss.api.message.placeholder.SPlaceHolders;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.SystemSubject;

public class ConsoleNicknamePlaceholder implements SPlaceHolder<SystemSubject> {

    public static final String TAG_NAME = "nickname";

    @NotNull
    private final String tag;
    @NotNull
    private final String name;

    public ConsoleNicknamePlaceholder() {
        this(SPlaceHolders.PLAYER, TAG_NAME);
    }

    private ConsoleNicknamePlaceholder(@NotNull String tag, @NotNull String name) {
        this.tag = tag;
        this.name = name;
    }

    @Override
    @NotNull
    public Component apply(@NotNull Component message, @NotNull SystemSubject player) {
        return message.replaceText(TextReplacementConfig.builder().match(this.formattedPlaceholderTag()).replacement("console").build());
    }

    @Override
    @NotNull
    public SPlaceHolder<SystemSubject> copy(@NotNull String placeholderTag, @NotNull String placeholderName) {
        return new ConsoleNicknamePlaceholder(placeholderTag, placeholderName);
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
    public Class<SystemSubject> type() {
        return SystemSubject.class;
    }
}
