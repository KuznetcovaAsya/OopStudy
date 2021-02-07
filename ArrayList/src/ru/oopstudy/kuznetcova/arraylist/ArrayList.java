package ru.oopstudy.kuznetcova.arraylist;

import java.util.*;
import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private T[] items;
    private int length;
    private int modCount;

    public ArrayList() {
        items = (T[]) new Object[10];
    }

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Вместимость = " + capacity + " Вместимость должна быть > 0");
        }

        items = (T[]) new Object[capacity];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Индекс = " + index + ". Индекс должен быть >= 0 и < " + length);
        }
    }

    private void checkIndexToAdd(int index) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Индекс = " + index + ". Индекс должен быть >= 0 и <= " + length);
        }
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public T get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public T set(int index, T item) {
        checkIndex(index);

        T oldItem = items[index];
        items[index] = item;

        return oldItem;
    }

    @Override
    public boolean add(T item) {
        add(length, item);

        return true;
    }

    @Override
    public void add(int index, T item) {
        checkIndexToAdd(index);

        if (length >= items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, length - index);

        items[index] = item;
        length++;
        modCount++;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        addAll(length, collection);

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        checkIndexToAdd(index);

        if (collection.size() == 0) {
            return false;
        }

        ensureCapacity(length + collection.size());

        System.arraycopy(items, index, items, index + collection.size(), length - index);

        int i = index;

        for (T e : collection) {
            items[i] = e;
            i++;
        }

        length += collection.size();
        modCount++;

        return true;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T oldItem = items[index];

        if (index < length - 1) {
            System.arraycopy(items, index + 1, items, index, length - index - 1);
        }

        length--;
        modCount++;

        return oldItem;
    }

    @Override
    public boolean remove(Object item) {
        int index = indexOf(item);

        if (index != -1) {
            remove(index);
            return true;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection collection) {
        if (collection.size() == 0) {
            return false;
        }

        boolean isRemoved = false;

        for (int i = 0; i < length; i++) {
            while (collection.contains(items[i])) {
                remove(i);

                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean contains(Object item) {
        int index = indexOf(item);

        return index != -1;
    }

    @Override
    public boolean containsAll(Collection collection) {
        for (Object e : collection) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int indexOf(Object item) {
        for (int i = 0; i < length; i++) {
            if (Objects.equals(items[i], item)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object item) {
        for (int i = length - 1; i >= 0; i--) {
            if (Objects.equals(items[i], item)) {
                return i;
            }
        }

        return -1;
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private final int currentModCount = modCount;

        public boolean hasNext() {
            return currentIndex + 1 < length;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Выход за length (" + (length - 1) + "): " + currentIndex);
            }

            if (modCount != currentModCount) {
                throw new ConcurrentModificationException("Размер списка изменился во время работы итератора");
            }

            ++currentIndex;

            return items[currentIndex];
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, length);
    }


    @Override
    public void clear() {
        for (int i = 0; i < length; i++) {
            items[i] = null;
        }

        length = 0;
        modCount++;
    }


    @Override
    public boolean retainAll(Collection collection) {
        if (length == 0) {
            return false;
        }

        if (collection.size() == 0) {
            clear();
            return true;
        }

        boolean isRemoved = false;

        for (int i = 0; i < length; ++i) {
            if (!collection.contains(items[i])) {
                remove(i);

                i--;
                isRemoved = true;
            }
        }

        return isRemoved;
    }


    @Override
    public <T1> T1[] toArray(T1[] array) {
        if (array.length < length) {
            return (T1[]) Arrays.copyOf(items, length, array.getClass());
        }

        System.arraycopy(items, 0, array, 0, length);

        if (array.length > length) {
            array[length] = null;
        }

        return array;
    }


    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public void ensureCapacity(int capacity) {
        if (capacity > items.length) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    public void trimToSize() {
        if (length < items.length) {
            items = Arrays.copyOf(items, length);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (int i = 0; i < length; i++) {
            stringBuilder.append(items[i]);

            if (i < length - 1) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override // Нет реализации
    public ListIterator listIterator() {
        return null;
    }

    @Override // Нет реализации
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override // Нет реализации
    public List subList(int fromIndex, int toIndex) {
        return null;
    }
}