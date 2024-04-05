package org.example;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

public class MyArrayList<E> extends AbstractList<E> implements RandomAccess, java.io.Serializable {
    private static final int DEFAULT_CAPACITY = 10;
    transient Object[] elements;
    private int size;
    private static final Object[] DEFAULT_ARRAY = {};
    private static final int MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;

    public MyArrayList() {
        this.elements = DEFAULT_ARRAY;
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elements = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elements = DEFAULT_ARRAY;
        } else {
            throw new IllegalArgumentException("Неверное значение: " + initialCapacity);
        }
    }

    public MyArrayList(Collection<? extends E> collection) {
        Object[] object = collection.toArray();
        if ((size = object.length) != 0) {
            elements = Arrays.copyOf(object, size, Object[].class);
        } else {
            elements = DEFAULT_ARRAY;
        }
    }

    @Override
    public int size() {
        return size;
    }




}
