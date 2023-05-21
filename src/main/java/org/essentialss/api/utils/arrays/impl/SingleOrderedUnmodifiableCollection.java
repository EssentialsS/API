package org.essentialss.api.utils.arrays.impl;

import org.essentialss.api.utils.arrays.OrderedUnmodifiableCollection;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Predicate;

@SuppressWarnings("NullableProblems")
public class SingleOrderedUnmodifiableCollection<E> extends LinkedList<E> implements OrderedUnmodifiableCollection<E> {

    public SingleOrderedUnmodifiableCollection(Collection<E> collection) {
        super(Collections.unmodifiableList((collection instanceof List) ? (List<E>) collection : new LinkedList<>(collection)));
    }

    @SafeVarargs
    public SingleOrderedUnmodifiableCollection(E... array) {
        super(Arrays.asList(array));
    }

    @Override
    @Deprecated
    public boolean removeAll(@NotNull Collection<?> c) {
        return super.removeAll(c);
    }

    @Override
    @Deprecated
    public boolean retainAll(@NotNull Collection<?> c) {
        return super.retainAll(c);
    }

    @Override
    @Deprecated
    public E removeFirst() {
        return super.removeFirst();
    }

    @Override
    @Deprecated
    public E removeLast() {
        return super.removeLast();
    }

    @Override
    @Deprecated
    public void addFirst(E e) {
        super.addFirst(e);
    }

    @Override
    @Deprecated
    public void addLast(E e) {
        super.addLast(e);
    }

    @Override
    @Deprecated
    public boolean add(E e) {
        return super.add(e);
    }

    @Override
    @Deprecated
    public boolean addAll(Collection<? extends E> c) {
        return super.addAll(c);
    }

    @Override
    @Deprecated
    public boolean addAll(int index, Collection<? extends E> c) {
        return super.addAll(index, c);
    }

    @Override
    @Deprecated
    public E set(int index, E element) {
        return super.set(index, element);
    }

    @Override
    @Deprecated
    public void add(int index, E element) {
        super.add(index, element);
    }

    @Override
    @Deprecated
    public E remove(int index) {
        return super.remove(index);
    }

    @Override
    @Deprecated
    public E poll() {
        return super.poll();
    }

    @Override
    @Deprecated
    public E remove() {
        return super.remove();
    }

    @Override
    @Deprecated
    public boolean offer(E e) {
        return super.offer(e);
    }

    @Override
    @Deprecated
    public boolean offerFirst(E e) {
        return super.offerFirst(e);
    }

    @Override
    @Deprecated
    public boolean offerLast(E e) {
        return super.offerLast(e);
    }

    @Override
    @Deprecated
    public E pollFirst() {
        return super.pollFirst();
    }

    @Override
    @Deprecated
    public E pollLast() {
        return super.pollLast();
    }

    @Override
    @Deprecated
    public void push(E e) {
        super.push(e);
    }

    @Override
    @Deprecated
    public E pop() {
        return super.pop();
    }

    @Override
    @Deprecated
    public boolean removeIf(Predicate<? super E> filter) {
        return super.removeIf(filter);
    }
}
