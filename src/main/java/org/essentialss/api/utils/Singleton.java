package org.essentialss.api.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class Singleton<T> {

    @NotNull
    private final Supplier<T> getter;
    @Nullable
    private T cached;

    public Singleton(@NotNull Supplier<T> getter) {
        this.getter = getter;
    }

    public T get() {
        if (null == this.cached) {
            return this.runGetter();
        }
        return this.cached;
    }

    private synchronized T runGetter() {
        if (null != this.cached) {
            return this.cached;
        }
        this.cached = this.getter.get();
        return this.cached;
    }
}
