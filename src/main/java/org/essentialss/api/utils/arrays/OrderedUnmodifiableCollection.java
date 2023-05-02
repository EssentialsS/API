package org.essentialss.api.utils.arrays;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;

public interface OrderedUnmodifiableCollection<T> extends UnmodifiableCollection<T>, List<T> {

    @Deprecated
    @Override
    boolean addAll(int i, @NotNull Collection<? extends T> collection);

    @Deprecated
    @Override
    default void replaceAll(UnaryOperator<T> operator) {
        List.super.replaceAll(operator);
    }

    @Deprecated
    @Override
    default void sort(Comparator<? super T> c) {
        List.super.sort(c);
    }

    @Deprecated
    @Override
    T set(int i, T t);

    @Deprecated
    @Override
    void add(int i, T t);

    @Deprecated
    @Override
    T remove(int i);
}
