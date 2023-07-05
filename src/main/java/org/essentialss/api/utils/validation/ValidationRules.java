package org.essentialss.api.utils.validation;

import java.util.Collection;
import java.util.function.Supplier;

@SuppressWarnings("AnonymousInnerClass")
public final class ValidationRules {

    private ValidationRules() {
        throw new RuntimeException("Should not create new");
    }

    public static <V extends Number> ValidationRule<V> isGreaterThan(double size) {
        return new ValidationRule<V>() {
            @Override
            public IllegalStateException defaultException() {
                return new IllegalStateException("Must be " + (size + 1) + " or more");
            }

            @Override
            public <E extends Throwable> void check(V value, Supplier<E> throwable) throws E {
                if (size >= value.doubleValue()) {
                    throw throwable.get();
                }
            }

        };
    }

    public static <T> ValidationRule<T> notNull() {
        return new ValidationRule<T>() {
            @Override
            public IllegalStateException defaultException() {
                return new IllegalStateException("Can not be null");

            }

            @Override
            public <T1 extends Throwable> void check(T value, Supplier<T1> throwable) throws T1 {
                if (null == value) {
                    throw throwable.get();
                }
            }
        };
    }

    public static <T> ValidationRule<T> isNull() {
        return new ValidationRule<T>() {
            @Override
            public IllegalStateException defaultException() {
                return new IllegalStateException("Must be null");
            }

            @Override
            public <T1 extends Throwable> void check(T value, Supplier<T1> throwable) throws T1 {
                if (null != value) {
                    throw throwable.get();
                }
            }
        };
    }

    public static <E> ValidationRule<E[]> isLengthGreaterThan(int underMinimumLength) {
        return new ValidationRule<E[]>() {
            @Override
            public IllegalStateException defaultException() {
                return new IllegalStateException("Must be " + (underMinimumLength + 1) + " or more in size");
            }

            @Override
            public <T extends Throwable> void check(E[] value, Supplier<T> throwable) throws T {
                if (value.length <= underMinimumLength) {
                    throw throwable.get();
                }
            }
        };
    }

    public static <E> ValidationRule<Collection<E>> isSizeGreaterThan(int underMinimumLength) {
        return new ValidationRule<Collection<E>>() {
            @Override
            public IllegalStateException defaultException() {
                return new IllegalStateException("Must be " + (underMinimumLength + 1) + " or more in size");
            }

            @Override
            public <T extends Throwable> void check(Collection<E> value, Supplier<T> throwable) throws T {
                if (value.size() <= underMinimumLength) {
                    throw throwable.get();
                }
            }
        };
    }


}
