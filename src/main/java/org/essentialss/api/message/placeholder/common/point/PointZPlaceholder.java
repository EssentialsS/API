package org.essentialss.api.message.placeholder.common.point;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.essentialss.api.message.placeholder.SPlaceHolders;
import org.essentialss.api.world.points.SPoint;
import org.jetbrains.annotations.NotNull;

public class PointZPlaceholder implements SPlaceHolder<SPoint> {


    public static final String TAG_NAME = "z exact";

    private final @NotNull String tag;
    private final @NotNull String name;

    public PointZPlaceholder() {
        this(SPlaceHolders.POINT, TAG_NAME);
    }

    public PointZPlaceholder(@NotNull String tag, @NotNull String name) {
        this.tag = tag;
        this.name = name;
    }

    @Override
    public @NotNull Component apply(@NotNull Component message, @NotNull SPoint value) {
        return message.replaceText(TextReplacementConfig.builder().match(this.formattedPlaceholderTag()).replacement(value.position().z() + "").build());
    }

    @Override
    public @NotNull SPlaceHolder<SPoint> copy(@NotNull String placeholderTag, @NotNull String placeholderName) {
        return new PointZPlaceholder(placeholderTag, placeholderName);
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
    public @NotNull Class<SPoint> type() {
        return SPoint.class;
    }
}
