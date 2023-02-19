package org.essentialss.api.utils;

import java.util.function.Supplier;

public class Singleton<T> {

    private T cached;
    private final Supplier<T> getter;

    public Singleton(Supplier<T> getter) {
        this.getter = getter;
    }

    public T get() {
        if (null == cached) {
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
