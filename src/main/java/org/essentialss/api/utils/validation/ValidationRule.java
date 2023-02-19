package org.essentialss.api.utils.validation;

import java.util.function.Supplier;

public interface ValidationRule<V> {

    IllegalStateException defaultException();

    <T extends Throwable> void check(V value, Supplier<T> throwable) throws T;

    default void check(V value) throws IllegalStateException {
        this.check(value, this::defaultException);
    }


}
