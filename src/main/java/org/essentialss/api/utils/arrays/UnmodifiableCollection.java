package org.essentialss.api.utils.arrays;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

@SuppressWarnings({"DataFlowIssue", "NullableProblems"})
public final class UnmodifiableCollection<T> implements Collection<T> {

    private final Collection<T> collection;

    public UnmodifiableCollection(Collection<T> collection) {
        this.collection = Collections.unmodifiableCollection(collection);
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
    @Deprecated
    public boolean add(T t) {
        return this.collection.add(t);
    }

    @Deprecated
    @Override
    public boolean remove(Object o) {
        return this.collection.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> collection) {
        return this.collection.containsAll(collection);
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
}
