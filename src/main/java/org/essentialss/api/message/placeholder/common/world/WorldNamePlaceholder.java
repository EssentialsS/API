package org.essentialss.api.message.placeholder.common.world;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.essentialss.api.message.placeholder.SPlaceHolders;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.world.server.ServerWorld;

@SuppressWarnings("i-am-message-adapter")
public class WorldNamePlaceholder implements SPlaceHolder<ServerWorld> {

    public static final String TAG_NAME = "name";

    @NotNull
    private final String tag;
    @NotNull
    private final String name;

    public WorldNamePlaceholder() {
        this(TAG_NAME, SPlaceHolders.WORLD);
    }

    private WorldNamePlaceholder(@NotNull String tag, @NotNull String name) {
        this.tag = tag;
        this.name = name;
    }

    @NotNull
    @Override
    public Component apply(@NotNull Component message, @NotNull ServerWorld value) {
        Component name = value.properties().displayName().orElseGet(() -> Component.text(value.properties().name()));
        return message.replaceText(TextReplacementConfig.builder().match(this.formattedPlaceholderTag()).replacement(name).build());
    }

    @NotNull
    @Override
    public SPlaceHolder<ServerWorld> copy(@NotNull String placeholderTag, @NotNull String placeholderName) {
        return new WorldNamePlaceholder(placeholderTag, placeholderName);
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
        return ServerWorld.class;
    }
}
