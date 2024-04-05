package org.example;

import java.util.AbstractList;
import java.util.RandomAccess;

public class MyArrayList<E> extends AbstractList<E> implements RandomAccess, java.io.Serializable {
    private static final int DEFAULT_CAPACITY = 10;
    transient Object[] elements;
    private int size;
    private static final Object[] DEFAULT_ARRAY = {};
    private static final int MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;


    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
