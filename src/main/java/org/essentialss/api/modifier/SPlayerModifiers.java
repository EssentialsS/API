package org.essentialss.api.modifier;

import org.spongepowered.api.data.Keys;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public final class SPlayerModifiers {

    public static final SPlayerModifier<Boolean> VISIBILITY = new SPlayerModifierImpl<>("visibility", Keys.IS_INVISIBLE);

    private SPlayerModifiers() {
        throw new RuntimeException("Cannot run");
    }

    public static Collection<SPlayerModifier<?>> all() {
        return Arrays
                .stream(SPlayerModifiers.class.getDeclaredFields())
                .filter(f -> Modifier.isFinal(f.getModifiers()))
                .filter(f -> Modifier.isPublic(f.getModifiers()))
                .filter(f -> Modifier.isStatic(f.getModifiers()))
                .filter(f -> SPlayerModifier.class.isAssignableFrom(f.getType()))
                .map(f -> {
                    try {
                        return (SPlayerModifier<?>) f.get(null);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

}
