package org.essentialss.api.message.placeholder;

import org.essentialss.api.message.placeholder.common.DurationPlaceholder;
import org.essentialss.api.message.placeholder.common.player.NamePlaceholder;
import org.essentialss.api.message.placeholder.common.player.NicknamePlaceholder;
import org.essentialss.api.utils.Singleton;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class SPlaceHolders {

    public static final NicknamePlaceholder PLAYER_NICKNAME = new NicknamePlaceholder();
    public static final NamePlaceholder PLAYER_NAME = new NamePlaceholder();
    public static final DurationPlaceholder DURATION = new DurationPlaceholder();

    private static final Singleton<Collection<SPlaceHolder<?>>> DEFAULT_PLACEHOLDERS = new Singleton<Collection<SPlaceHolder<?>>>(() -> Arrays
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

    public static Collection<SPlaceHolder<?>> defaultValues() {
        return DEFAULT_PLACEHOLDERS.get();
    }

}
