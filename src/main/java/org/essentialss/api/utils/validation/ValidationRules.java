package org.essentialss.api.utils.validation;

import java.util.Collection;

public final class ValidationRules {

    private ValidationRules() {
        throw new RuntimeException("Should not create new");
    }

    public static <T> ValidationRule<T> notNull() {
        return value -> {
            if (null == value) {
                throw new IllegalStateException("Can not be null");
            }
        };
    }

    public static <T> ValidationRule<T> isNull() {
        return value -> {
            if (null != value) {
                throw new IllegalStateException("Must be null");
            }
        };
    }

    public static <T> ValidationRule<T[]> isLengthGreaterThan(int underMinimumLength) {
        return value -> {
            if (value.length <= underMinimumLength) {
                throw new IllegalStateException("Must be " + (underMinimumLength + 1) + " or more in size");
            }
        };
    }

    public static <T> ValidationRule<? extends Collection<T>> isSizeGreaterThan(int underMinimumLength) {
        return value -> {
            if (value.size() <= underMinimumLength) {
                throw new IllegalStateException("Must be " + (underMinimumLength + 1) + " or more in size");
            }
        };
    }


}
