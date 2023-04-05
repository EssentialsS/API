package org.essentialss.api.message.placeholder.common.point;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.essentialss.api.message.placeholder.SPlaceHolders;
import org.essentialss.api.world.points.SPoint;
import org.jetbrains.annotations.NotNull;

public class PointYPlaceholder implements SPlaceHolder<SPoint> {


    public static final String TAG_NAME = "y exact";

    private final @NotNull String tag;
    private final @NotNull String name;

    public PointYPlaceholder() {
        this(SPlaceHolders.POINT, TAG_NAME);
    }

    public PointYPlaceholder(@NotNull String tag, @NotNull String name) {
        this.tag = tag;
        this.name = name;
    }

    @Override
    public @NotNull Component apply(@NotNull Component message, @NotNull SPoint value) {
        return message.replaceText(TextReplacementConfig.builder().match(this.formattedPlaceholderTag()).replacement(value.position().y() + "").build());
    }

    @Override
    public @NotNull SPlaceHolder<SPoint> copy(@NotNull String placeholderTag, @NotNull String placeholderName) {
        return new PointYPlaceholder(placeholderTag, placeholderName);
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
    public @NotNull Class<SPoint> type() {
        return SPoint.class;
    }
}
