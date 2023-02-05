package org.essentialss.api.utils.validation;

public interface ValidationRule<T> {

    void check(T value) throws IllegalStateException;

}
