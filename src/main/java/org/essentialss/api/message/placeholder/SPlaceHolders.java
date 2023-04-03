package org.essentialss.api.message.placeholder;

import org.essentialss.api.message.MuteType;
import org.essentialss.api.message.adapters.player.command.PingMessageAdapter;
import org.essentialss.api.message.placeholder.common.DurationPlaceholder;
import org.essentialss.api.message.placeholder.common.player.NamePlaceholder;
import org.essentialss.api.message.placeholder.common.player.NicknamePlaceholder;
import org.essentialss.api.message.placeholder.common.player.PingPlaceholder;
import org.essentialss.api.message.placeholder.common.point.PointNamePlaceholder;
import org.essentialss.api.message.placeholder.common.point.PointXPlaceholder;
import org.essentialss.api.message.placeholder.common.point.PointYPlaceholder;
import org.essentialss.api.message.placeholder.common.point.PointZPlaceholder;
import org.essentialss.api.message.placeholder.wrapper.EnumPlaceholder;
import org.essentialss.api.utils.Singleton;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public final class SPlaceHolders {

    public static final NicknamePlaceholder PLAYER_NICKNAME = new NicknamePlaceholder();
    public static final NamePlaceholder PLAYER_NAME = new NamePlaceholder();
    public static final DurationPlaceholder DURATION = new DurationPlaceholder();
    public static final PointXPlaceholder POINT_X = new PointXPlaceholder();
    public static final PointYPlaceholder POINT_Y = new PointYPlaceholder();
    public static final PointZPlaceholder POINT_Z = new PointZPlaceholder();
    public static final PointNamePlaceholder POINT_NAME = new PointNamePlaceholder();
    public static final EnumPlaceholder<MuteType> MUTE_TYPE = new EnumPlaceholder<>("mute", "type", MuteType.class);
    public static final PingPlaceholder PLAYER_PING = new PingPlaceholder();

    private static final Singleton<Collection<SPlaceHolder<?>>> DEFAULT_PLACEHOLDERS = new Singleton<>(() -> Arrays
            .stream(SPlaceHolders.class.getDeclaredFields())
            .filter(field -> Modifier.isPublic(field.getModifiers()))
            .filter(field -> Modifier.isStatic(field.getModifiers()))
            .filter(field -> Modifier.isFinal(field.getModifiers()))
            .filter(field -> SPlaceHolder.class.isAssignableFrom(field.getType()))
            .map(field -> {
                try {
                    return (SPlaceHolder<?>) field.get(null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toSet()));

    private SPlaceHolders() {
        throw new RuntimeException("Cannot run");
    }

    public static Collection<SPlaceHolder<?>> defaultValues() {
        return DEFAULT_PLACEHOLDERS.get();
    }

}
