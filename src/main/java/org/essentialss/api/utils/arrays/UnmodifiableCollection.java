package org.essentialss.api.utils.arrays;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface UnmodifiableCollection<T> extends Collection<T> {

    @Override
    @Deprecated
    boolean add(T t);

    @Deprecated
    @Override
    boolean remove(Object o);

    @Deprecated
    @Override
    boolean addAll(@NotNull Collection<? extends T> collection);

    @Deprecated
    @Override
    boolean removeAll(@NotNull Collection<?> collection);

    @Deprecated
    @Override
    boolean retainAll(@NotNull Collection<?> collection);

    @Deprecated
    @Override
    void clear();
}
