package org.essentialss.api.utils.arrays;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class UnmodifiableCollector<T, C extends Collection<T>, U extends UnmodifiableCollection<T>> implements Collector<T, C, U> {

    private final Supplier<C> collectionSupplier;
    private final Function<C, U> finisher;
    private final Set<Characteristics> characteristics;

    UnmodifiableCollector(Supplier<C> collectionSupplier, Function<C, U> finisher, Characteristics... characteristics) {
        this.collectionSupplier = collectionSupplier;
        this.finisher = finisher;
        this.characteristics = new HashSet<>(Arrays.asList(characteristics));
    }

    @Override
    public Supplier<C> supplier() {
        return this.collectionSupplier;
    }

    @Override
    public BiConsumer<C, T> accumulator() {
        return Collection::add;
    }

    @Override
    public BinaryOperator<C> combiner() {
        return (l, v) -> {
            l.addAll(v);
            return l;
        };
    }

    @Override
    public Function<C, U> finisher() {
        return this.finisher;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return this.characteristics;
    }
}
