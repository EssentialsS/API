package org.essentialss.api.utils.friendly;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public enum FriendlyStrings implements FriendlyString {
    DURATION((obj) -> {
        if (!(obj instanceof Duration)) {
            throw new IllegalArgumentException("unsupported type of " + obj.getClass().getSimpleName() + ". Must be Duration");
        }
        Duration mathsDuration = (Duration) obj;
        long days = mathsDuration.toDays();
        mathsDuration = mathsDuration.minus(days, ChronoUnit.DAYS);
        long hours = mathsDuration.toHours();
        mathsDuration = mathsDuration.minus(hours, ChronoUnit.HOURS);
        long minutes = mathsDuration.toMinutes();
        mathsDuration = mathsDuration.minus(minutes, ChronoUnit.MINUTES);
        long seconds = mathsDuration.getSeconds();

        StringBuilder builder = new StringBuilder();
        if (0 < days) {
            builder.append(days).append(" days");
        }
        if (0 < hours) {
            if (0 != builder.length()) {
                builder.append(", ");
            }
            builder.append(hours).append(" hours");
        }
        if (0 < minutes) {
            if (0 != builder.length()) {
                builder.append(", ");
            }
            builder.append(minutes).append(" minutes");
        }
        if (0 < seconds) {
            if (0 != builder.length()) {
                builder.append(", ");
            }
            builder.append(seconds).append(" seconds");
        }
        if (0 != builder.length()) {
            return builder.toString();
        }
        return "less then a second";
    }, Duration.class);

    private final Class<?>[] supportedTypes;
    private final Function<Object, Component> toComponent;
    private final Function<Object, String> toString;

    FriendlyStrings(Function<Object, String> toString, Class<?>... types) {
        this((obj) -> Component.text(toString.apply(obj)), toString, types);
    }

    FriendlyStrings(Function<Object, Component> toComponent, Function<Object, String> toString, Class<?>... types) {
        this.toString = toString;
        this.toComponent = toComponent;
        this.supportedTypes = types;
    }

    @Override
    public @NotNull Class<?>[] supportedTypes() {
        return this.supportedTypes;
    }

    @Override
    public @NotNull Component toFriendlyComponent(@NotNull Object value) {
        return this.toComponent.apply(value);
    }

    @Override
    public @NotNull String toFriendlyString(@NotNull Object value) {
        return this.toString.apply(value);
    }

    public static Optional<FriendlyStrings> ofType(Object type) {
        return Arrays.stream(values()).filter(friendly -> Arrays.stream(friendly.supportedTypes()).anyMatch(clazz -> clazz.isInstance(type))).findAny();
    }
}
