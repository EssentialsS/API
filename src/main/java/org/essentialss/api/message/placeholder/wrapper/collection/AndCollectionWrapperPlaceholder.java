package org.essentialss.api.message.placeholder.wrapper.collection;

import net.kyori.adventure.text.Component;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class AndCollectionWrapperPlaceholder<T> extends AbstractCollectionWrapperPlaceholder<T> {

    public AndCollectionWrapperPlaceholder(@NotNull SPlaceHolder<T> placeHolder) {
        this(placeHolder, null, null);
    }

    public AndCollectionWrapperPlaceholder(@NotNull SPlaceHolder<T> placeHolder, @Nullable String tagOverride, @Nullable String nameOverride) {
        super(placeHolder, tagOverride, nameOverride);
    }

    @Override
    public @NotNull SPlaceHolder<Collection<T>> copy(@Nullable String placeholderTag, @Nullable String placeholderName) {
        return new AndCollectionWrapperPlaceholder<>(this.placeholder(), placeholderTag, placeholderName);
    }

    @Override
    protected @NotNull Component suffixJoining() {
        return Component.text("and");
    }
}
