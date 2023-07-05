package org.essentialss.api.message.placeholder.common.player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.essentialss.api.message.placeholder.SPlaceHolders;
import org.essentialss.api.player.data.SGeneralUnloadedData;
import org.jetbrains.annotations.NotNull;

public class NicknamePlaceholder implements SPlaceHolder<SGeneralUnloadedData> {

    public static final String TAG_NAME = "nickname";

    @NotNull
    private final String tag;
    @NotNull
    private final String name;

    public NicknamePlaceholder() {
        this(SPlaceHolders.PLAYER, TAG_NAME);
    }

    private NicknamePlaceholder(@NotNull String tag, @NotNull String name) {
        this.tag = tag;
        this.name = name;
    }

    @Override
    @NotNull
    public Component apply(@NotNull Component message, @NotNull SGeneralUnloadedData player) {
        return message.replaceText(TextReplacementConfig.builder().match(this.formattedPlaceholderTag()).replacement(player.displayName()).build());
    }

    @Override
    @NotNull
    public SPlaceHolder<SGeneralUnloadedData> copy(@NotNull String placeholderTag, @NotNull String placeholderName) {
        return new NicknamePlaceholder(placeholderTag, placeholderName);
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
    public Class<SGeneralUnloadedData> type() {
        return SGeneralUnloadedData.class;
    }
}
