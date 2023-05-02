package org.essentialss.api.utils.arrays.impl;

import org.essentialss.api.utils.arrays.OrderedUnmodifiableCollection;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("NullableProblems")
public class SingleOrderedUnmodifiableCollection<E> extends LinkedList<E> implements OrderedUnmodifiableCollection<E> {

    public SingleOrderedUnmodifiableCollection(List<E> collection) {
        super(Collections.unmodifiableList(collection));
    }

    public SingleOrderedUnmodifiableCollection(E... array) {
        super(Arrays.asList(array));
    }

}
