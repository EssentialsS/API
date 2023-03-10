package org.essentialss.api.utils.arrays;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings({"DataFlowIssue", "NullableProblems"})
public final class SingleUnmodifiableCollection<T> implements UnmodifiableCollection<T> {

    private final Collection<T> collection;

    public SingleUnmodifiableCollection(Collection<T> collection) {
        this.collection = Collections.unmodifiableCollection(collection);
    }

    @Override
    @Deprecated
    public boolean add(T t) {
        return this.collection.add(t);
    }

    @Deprecated
    @Override
    public boolean remove(Object o) {
        return this.collection.remove(o);
    }

    @Deprecated
    @Override
    public boolean addAll(@NotNull Collection<? extends T> collection) {
        return this.collection.addAll(collection);
    }

    @Deprecated
    @Override
    public boolean removeAll(@NotNull Collection<?> collection) {
        return this.collection.removeAll(collection);
    }

    @Deprecated
    @Override
    public boolean retainAll(@NotNull Collection<?> collection) {
        return this.collection.retainAll(collection);
    }

    @Deprecated
    @Override
    public void clear() {
        this.collection.clear();
    }

    @Override
    public int hashCode() {
        return this.collection.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return this.collection.equals(o);
    }

    @Override
    public int size() {
        return this.collection.size();
    }

    @Override
    public boolean isEmpty() {
        return this.collection.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.collection.contains(o);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return this.collection.iterator();
    }

    @Override
    public @NotNull Object[] toArray() {
        return this.collection.toArray();
    }

    @Override
    public <T1> @NotNull T1[] toArray(@NotNull T1[] t1s) {
        return this.collection.toArray(t1s);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> collection) {
        return this.collection.containsAll(collection);
    }

    @SafeVarargs
    public static <E> Collection<E> of(Collection<E>... arrays) {
        List<E> lists = Arrays.stream(arrays).flatMap(Collection::stream).collect(Collectors.toList());
        return new SingleUnmodifiableCollection<>(lists);
    }
}
