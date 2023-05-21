package org.essentialss.api.utils.arrays;

import org.essentialss.api.utils.arrays.impl.SingleOrderedUnmodifiableCollection;
import org.essentialss.api.utils.arrays.impl.SingleUnmodifiableCollection;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.stream.Collector;

public final class UnmodifiableCollectors {

    private UnmodifiableCollectors() {
        throw new RuntimeException("Do not create");
    }

    public static <T> UnmodifiableCollector<T, LinkedBlockingQueue<T>, OrderedUnmodifiableCollection<T>> asOrdered() {
        return new UnmodifiableCollector<>(LinkedBlockingQueue::new, queue -> new SingleOrderedUnmodifiableCollection<>(new LinkedList<>(queue)),
                                           Collector.Characteristics.CONCURRENT);
    }

    public static <T> UnmodifiableCollector<T, LinkedTransferQueue<T>, UnmodifiableCollection<T>> asUnordered() {
        return new UnmodifiableCollector<>(LinkedTransferQueue::new, queue -> {
            while (queue.hasWaitingConsumer()) {
            }
            return new SingleUnmodifiableCollection<>(queue);
        }, Collector.Characteristics.UNORDERED, Collector.Characteristics.CONCURRENT);
    }
}
