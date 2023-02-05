package org.essentialss.api.utils.validation;

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


}
