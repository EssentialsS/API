package org.essentialss.api.utils.validation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.LinkedList;

public class Validator<T> {

    private final LinkedList<ValidationRule<T>> rules = new LinkedList<>();
    private final @Nullable T value;

    public Validator(@Nullable T value) {
        this.value = value;
    }

    public Validator<T> notNull() {
        return this.rule(ValidationRules.notNull());
    }

    public Validator<T> isNull() {
        return this.rule(ValidationRules.isNull());
    }

    public T validate() throws IllegalStateException {
        for (ValidationRule<T> rule : this.rules) {
            rule.check(this.value);
        }
        return this.value;
    }

    public Validator<T> rule(@NotNull ValidationRule<T> rule) {
        this.rules.add(rule);
        return this;
    }

    public Validator<T> rules(@NotNull ValidationRule<T>... rules) {
        return this.rules(rules);
    }

    public Validator<T> rules(@NotNull Collection<? extends ValidationRule<T>> collection) {
        this.rules.addAll(collection);
        return this;
    }
}