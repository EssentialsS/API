package org.essentialss.api.message.placeholder.common.point;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.essentialss.api.world.points.SPoint;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.essentialss.api.message.placeholder.SPlaceHolders;
import org.jetbrains.annotations.NotNull;

public class PointYPlaceholder implements SPlaceHolder<SPoint> {


    public static final String TAG_NAME = "y exact";

    @NotNull
    private final String tag;
    @NotNull
    private final String name;

    public PointYPlaceholder() {
        this(SPlaceHolders.POINT, TAG_NAME);
    }

    private PointYPlaceholder(@NotNull String tag, @NotNull String name) {
        this.tag = tag;
        this.name = name;
    }

    @Override
    @NotNull
    public Component apply(@NotNull Component message, @NotNull SPoint value) {
        return message.replaceText(
                TextReplacementConfig.builder().match(this.formattedPlaceholderTag()).replacement(String.valueOf(value.position().y())).build());
    }

    @Override
    @NotNull
    public SPlaceHolder<SPoint> copy(@NotNull String placeholderTag, @NotNull String placeholderName) {
        return new PointYPlaceholder(placeholderTag, placeholderName);
    }

    @Override
    @NotNull
    public String placeholderTagName() {
        return this.tag;
    }

    @Override
    @NotNull
    public String placeholderTagType() {
        return this.name;
    }

    @Override
    @NotNull
    public Class<SPoint> type() {
        return SPoint.class;
    }
}
