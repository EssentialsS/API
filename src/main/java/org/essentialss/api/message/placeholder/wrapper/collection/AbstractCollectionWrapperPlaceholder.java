package org.essentialss.api.message.placeholder.wrapper.collection;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractCollectionWrapperPlaceholder<T> implements SPlaceHolder<Collection<T>> {

    private final SPlaceHolder<T> placeHolder;
    private final @Nullable String nameOverride;
    private final @Nullable String typeOverride;

    protected AbstractCollectionWrapperPlaceholder(@NotNull SPlaceHolder<T> placeHolder, @Nullable String tagOverride, @Nullable String nameOverride) {
        this.placeHolder = placeHolder;
        this.nameOverride = nameOverride;
        this.typeOverride = tagOverride;
    }

    @Override
    public @NotNull Component apply(@NotNull Component message, @NotNull Collection<T> values) {
        Component mix = null;
        List<T> valueList = new LinkedList<>(values);
        for (int index = 0; index < valueList.size(); index++) {
            T value = valueList.get(index);
            Component applied = this.placeHolder.apply(Component.text(this.placeHolder.formattedPlaceholderTag()), value);
            if (index == 0) {
                mix = applied;
                continue;
            }
            if (index == (values.size() - 1)) {
                mix = mix.append(this.suffixJoining()).append(Component.text(" ")).append(applied);
                continue;
            }
            mix = mix.append(Component.text(", ")).append(applied);
        }
        if (null == mix) {
            mix = Component.empty();
        }
        return message.replaceText(TextReplacementConfig.builder().match(this.formattedPlaceholderTag()).replacement(mix).build());
    }

    @Override
    public abstract @NotNull SPlaceHolder<Collection<T>> copy(@Nullable String placeholderTag, @Nullable String placeholderName);

    @Override
    public @NotNull SPlaceHolder<Collection<T>> copyWithTagName(@Nullable String placeholderName) {
        return this.copy(this.typeOverride, placeholderName);
    }

    @Override
    public @NotNull SPlaceHolder<Collection<T>> copyWithTagType(@Nullable String placeholderTag) {
        return this.copy(placeholderTag, this.nameOverride);
    }

    @Override
    public @NotNull String placeholderTagName() {
        if (null == this.nameOverride) {
            return this.placeHolder.placeholderTagName() + "'s";
        }
        return this.nameOverride;
    }

    @Override
    public @NotNull String placeholderTagType() {
        if (null == this.typeOverride) {
            return this.placeHolder.placeholderTagType();
        }
        return this.typeOverride;
    }

    @Override
    public @NotNull Class<Collection> type() {
        return Collection.class;
    }

    public @NotNull SPlaceHolder<T> placeholder() {
        return this.placeHolder;
    }

    protected abstract @NotNull Component suffixJoining();
}
