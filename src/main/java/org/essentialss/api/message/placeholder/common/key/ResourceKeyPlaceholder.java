package org.essentialss.api.message.placeholder.common.key;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.essentialss.api.message.placeholder.SPlaceHolders;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.ResourceKey;

public class ResourceKeyPlaceholder implements SPlaceHolder<ResourceKey> {

    public static final String TAG_NAME = "key";


    @NotNull
    private final String tag;
    @NotNull
    private final String name;

    public ResourceKeyPlaceholder() {
        this(TAG_NAME, SPlaceHolders.KEY);
    }

    private ResourceKeyPlaceholder(String tag, String name) {
        this.tag = tag;
        this.name = name;
    }

    @NotNull
    @Override
    public Component apply(@NotNull Component message, @NotNull ResourceKey value) {
        return message.replaceText(TextReplacementConfig.builder().match(this.formattedPlaceholderTag()).replacement(value.formatted()).build());
    }

    @NotNull
    @Override
    public SPlaceHolder<ResourceKey> copy(@NotNull String placeholderTag, @NotNull String placeholderName) {
        return new ResourceKeyPlaceholder(placeholderTag, placeholderName);
    }

    @NotNull
    @Override
    public String placeholderTagName() {
        return this.tag;
    }

    @NotNull
    @Override
    public String placeholderTagType() {
        return this.name;
    }

    @NotNull
    @Override
    public Class<?> type() {
        return ResourceKey.class;
    }
}
