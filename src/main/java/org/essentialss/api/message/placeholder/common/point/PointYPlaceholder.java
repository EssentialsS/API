package org.essentialss.api.message.placeholder.common.point;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.essentialss.api.world.points.SPoint;
import org.jetbrains.annotations.NotNull;

public class PointYPlaceholder implements SPlaceHolder<SPoint> {


    public static final String TAG_NAME = "y exact";
    public static final String TAG_TYPE = "point";

    private final @NotNull String tag;
    private final @NotNull String type;

    public PointYPlaceholder() {
        this(TAG_NAME, TAG_TYPE);
    }

    public PointYPlaceholder(@NotNull String tag, @NotNull String type) {
        this.tag = tag;
        this.type = type;
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
        return this.type;
    }

    @Override
    public @NotNull Class<SPoint> type() {
        return SPoint.class;
    }
}
