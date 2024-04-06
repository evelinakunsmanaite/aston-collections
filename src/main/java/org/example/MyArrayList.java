package org.example;

import java.util.*;

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

    public E get(int index) {
        Objects.checkIndex(index, size);
        return (E) elements[index];
    }

    public E[] setByIndex(int index, E element){
        Objects.checkIndex(index, size);
        elements[index] = element;
        return (E[]) elements;
    }

    private void add(E element, Object[] object, int sizeObject) {
        if (sizeObject == object.length) elements = grow(size + 1);
        elements[sizeObject] = element;
        size++;
    }

    public boolean add(E element) {
        add(element, elements, size);
        return true;
    }

    public void add(int index, E element) {
        checkerRange(index);
        final int sizeObject = size;
        Object[] objects;
        if (sizeObject == (objects = elements).length)
            grow(sizeObject + 1);
        System.arraycopy(objects, index, objects, index + 1, sizeObject - index);

        elements[index] = element;
        size++;
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elements.length;
        if (oldCapacity > 0 || elements != DEFAULT_ARRAY) {
            int newCapacity = newLength(oldCapacity, minCapacity - oldCapacity, oldCapacity >> 1);// наш старый массив, минимально на сколько он должен вырости, желательный рост
            return elements = Arrays.copyOf(elements, newCapacity);
        } else return elements = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];

    }

    private int newLength(int oldCapacity, int minGrowCapacity, int bestGrowCapacity) {
        int newLength = oldCapacity + Math.max(minGrowCapacity, bestGrowCapacity);
        if (newLength > 0 && newLength <= MAX_ARRAY_LENGTH)
            return newLength;
        else {
            int minNewLength = oldCapacity + minGrowCapacity;
            if (minNewLength < 0)
                throw new OutOfMemoryError("Длина массива слишком большая");
            else return Math.max(minNewLength, MAX_ARRAY_LENGTH);
        }
    }

    private void checkerRange(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
    }

    public boolean addAll(Collection<? extends E> collection) {
        Object[] objects = collection.toArray();
        int objectsLength = objects.length;

        if (objectsLength == 0) return false;

        Object[] objectsElements = elements;
        final int elementSize = size;

        if (objectsLength > (objectsElements.length - elementSize))
            objectsElements = grow(elementSize + objectsLength);

        System.arraycopy(objects, 0, objectsElements, elementSize, objectsLength);
        size += objectsLength;
        return true;
    }

    public boolean addAll(int index, Collection<? extends E> collection) {
        checkerRange(index);

        Object[] objects = collection.toArray();
        int objectsLength = objects.length;

        if (objectsLength == 0) return false;

        Object[] objectsElements = elements;
        final int elementSize = size;

        if (objectsLength > (objectsElements.length - elementSize))
            objectsElements = grow(elementSize + objectsLength);

        int move = elementSize - index;
        if (move > 0)
            System.arraycopy(objectsElements, index, objectsElements, objectsLength + index, move);
        System.arraycopy(objects, 0, objectsElements, index, objectsLength);
        size += objectsLength;
        return true;
    }

    public E[] delete(int index) {
        checkerRange(index);
        final Object[] objects = elements;
        delete(objects, index);
        return (E[]) elements;
    }

    private void delete(Object[] objects, int indexObject) {
        final int newSize = size - 1;
        if (newSize > indexObject)
            System.arraycopy(objects, indexObject + 1, objects, indexObject, newSize - indexObject);
        objects[newSize] = null;

    }

    public boolean delete(Object object) {
        final Object[] objects = elements;
        int index = Arrays.asList(objects).indexOf(object);

        if (index < 0) return false;
        else delete(objects, index);

        return true;
    }

    public static <E extends Comparable<? super E>> E[] bubbleSort(Collection<? extends E> collection) {
        E[] objects = (E[]) new Comparable[collection.size()];
        objects = collection.toArray(objects);
        if (!isSort(objects)) {
            for (int i = objects.length - 1; i > 0; i--) {
                int j = 0;
                while (j < i) {
                    if (objects[j].compareTo(objects[j + 1]) > 0) {
                        E c = objects[j];
                        objects[j] = objects[j + 1];
                        objects[j + 1] = c;
                    }
                    j++;
                }
            }
        }
        return objects;
    }

    private static <E extends Comparable<? super E>> boolean isSort(E[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0)
                return false;
        }
        return true;
    }


}
