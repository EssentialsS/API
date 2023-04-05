package org.essentialss.api.message.placeholder.common.player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.essentialss.api.message.placeholder.SPlaceHolders;
import org.essentialss.api.player.data.SGeneralUnloadedData;
import org.jetbrains.annotations.NotNull;

public class NicknamePlaceholder implements SPlaceHolder<SGeneralUnloadedData> {

    public static final String TAG_NAME = "nickname";

    private final @NotNull String tag;
    private final @NotNull String name;

    public NicknamePlaceholder() {
        this(SPlaceHolders.PLAYER, TAG_NAME);
    }

    public NicknamePlaceholder(@NotNull String tag, @NotNull String name) {
        this.tag = tag;
        this.name = name;
    }

    @Override
    public @NotNull Component apply(@NotNull Component message, @NotNull SGeneralUnloadedData player) {
        return message.replaceText(TextReplacementConfig.builder().match(this.formattedPlaceholderTag()).replacement(player.displayName()).build());
    }

    @Override
    public @NotNull SPlaceHolder<SGeneralUnloadedData> copy(@NotNull String placeholderTag, @NotNull String placeholderName) {
        return new NicknamePlaceholder(placeholderTag, placeholderName);
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
    public @NotNull Class<SGeneralUnloadedData> type() {
        return SGeneralUnloadedData.class;
    }
}
