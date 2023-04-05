package org.essentialss.api.message.placeholder.common.point;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.essentialss.api.message.placeholder.SPlaceHolders;
import org.essentialss.api.utils.identifier.StringIdentifier;
import org.essentialss.api.world.points.SPoint;
import org.jetbrains.annotations.NotNull;

public class PointNamePlaceholder implements SPlaceHolder<SPoint> {

    public static final String TAG_NAME = "name";

    private final @NotNull String tag;
    private final @NotNull String type;

    public PointNamePlaceholder() {
        this(SPlaceHolders.POINT, TAG_NAME);
    }

    public PointNamePlaceholder(@NotNull String tag, @NotNull String type) {
        this.tag = tag;
        this.type = type;
    }

    @Override
    public @NotNull Component apply(@NotNull Component message, @NotNull SPoint value) {
        String replace;
        if (value instanceof StringIdentifier) {
            replace = ((StringIdentifier) value).identifier();
        } else {
            return message;
        }
        return message.replaceText(TextReplacementConfig.builder().match(this.formattedPlaceholderTag()).replacement(replace).build());
    }

    @Override
    public @NotNull SPlaceHolder<SPoint> copy(@NotNull String placeholderTag, @NotNull String placeholderName) {
        return new PointNamePlaceholder(placeholderTag, placeholderName);
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
