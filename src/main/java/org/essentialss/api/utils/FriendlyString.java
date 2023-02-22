package org.essentialss.api.utils;

import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public final class FriendlyString {

    private FriendlyString() {
        throw new RuntimeException("Should not create");
    }

    public static @NotNull String toString(@NotNull Duration time) {
        Duration mathsDuration = time;
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
    }

}
