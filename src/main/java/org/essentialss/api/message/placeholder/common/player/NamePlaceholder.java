package org.essentialss.api.message.placeholder.common.player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.essentialss.api.player.data.SGeneralUnloadedData;
import org.jetbrains.annotations.NotNull;

public class NamePlaceholder implements SPlaceHolder<SGeneralUnloadedData> {

    public static final String TAG_NAME = "name";
    public static final String TAG_TYPE = "player";

    private final @NotNull String tag;

    public NamePlaceholder() {
        this(TAG_TYPE);
    }

    public NamePlaceholder(@NotNull String tag) {
        this.tag = tag;
    }

    @Override
    public @NotNull Component apply(@NotNull Component message, @NotNull SGeneralUnloadedData player) {
        return message.replaceText(TextReplacementConfig.builder().match(this.formattedPlaceholderTag()).replacement(player.playerName()).build());
    }

    @Override
    public @NotNull SPlaceHolder<SGeneralUnloadedData> copyWithTagType(@NotNull String placeholderTag) {
        return new NamePlaceholder(placeholderTag);
    }

    @Override
    public @NotNull String placeholderTagName() {
        return TAG_NAME;
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
