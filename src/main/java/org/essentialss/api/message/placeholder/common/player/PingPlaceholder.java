package org.essentialss.api.message.placeholder.common.player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.format.TextColor;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.essentialss.api.message.placeholder.SPlaceHolders;
import org.essentialss.api.utils.Constants;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;

public class PingPlaceholder implements SPlaceHolder<ServerPlayer> {

    public static final String TAG_NAME = "ping";

    private final @NotNull String tag;
    private final @NotNull String name;

    public PingPlaceholder() {
        this(SPlaceHolders.PLAYER, TAG_NAME);
    }

    public PingPlaceholder(@NotNull String tag, @NotNull String name) {
        this.tag = tag;
        this.name = name;
    }

    @Override
    public @NotNull Component apply(@NotNull Component message, @NotNull ServerPlayer value) {
        int pingOriginal = value.connection().latency();
        int ping = Math.max(pingOriginal, 1); //0 ping breaks percentage
        double pingPercent = Math.min(((double) ping) / Constants.BAD_PING, 1);
        double red = Constants.UNSIGNED_BYTE_MAX - (pingPercent * Constants.UNSIGNED_BYTE_MAX);
        double green = pingPercent * Constants.UNSIGNED_BYTE_MAX;
        TextColor colour = TextColor.color(Math.round(red), Math.round(green), 0);
        return message.replaceText(
                TextReplacementConfig.builder().match(this.formattedPlaceholderTag()).replacement(Component.text(pingOriginal + "").color(colour)).build());
    }

    @Override
    public @NotNull SPlaceHolder<ServerPlayer> copy(@NotNull String placeholderTag, @NotNull String placeholderName) {
        return new PingPlaceholder(placeholderTag, placeholderName);
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
    public @NotNull Class<?> type() {
        return ServerPlayer.class;
    }
}
