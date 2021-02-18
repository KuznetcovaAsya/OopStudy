package ru.oopstudy.kuznetcova.arraylist;

import java.util.*;
import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private int modCount;

    public ArrayList() {
        items = (T[]) new Object[10];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость = " + capacity + " Вместимость должна быть >= 0");
        }

        items = (T[]) new Object[capacity];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс = " + index + ". Индекс должен быть >= 0 и < " + size);
        }
    }

    private void checkIndexToAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс = " + index + ". Индекс должен быть >= 0 и <= " + size);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
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
        add(size, item);

        return true;
    }

    @Override
    public void add(int index, T item) {
        if (items.length > 0) {
            checkIndexToAdd(index);
        }

        if (items.length == 0 || size >= items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, size - index);

        items[index] = item;
        size++;
        modCount++;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        addAll(size, collection);

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        if (items.length > 0) {
            checkIndexToAdd(index);
        }

        if (collection.size() == 0) {
            return false;
        }

        ensureCapacity(size + collection.size());

        System.arraycopy(items, index, items, index + collection.size(), size - index);

        int i = index;

        for (T e : collection) {
            items[i] = e;
            i++;
        }

        size += collection.size();
        modCount++;

        return true;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T oldItem = items[index];

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        size--;
        items[size] = null;
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

        for (int i = 0; i < size; i++) {
            if (collection.contains(items[i])) {
                remove(i);

                i--;
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean contains(Object item) {
        return indexOf(item) != -1;
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
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], item)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object item) {
        for (int i = size - 1; i >= 0; i--) {
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
            return currentIndex + 1 < size;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Выход за length (" + (size - 1) + "): " + currentIndex);
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
        return Arrays.copyOf(items, size);
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        size = 0;
        modCount++;
    }

    @Override
    public boolean retainAll(Collection collection) {
        if (size == 0) {
            return false;
        }

        if (collection.size() == 0) {
            clear();
            return true;
        }

        boolean isRemoved = false;

        for (int i = 0; i < size; ++i) {
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
        if (array.length < size) {
            return (T1[]) Arrays.copyOf(items, size, array.getClass());
        }

        System.arraycopy(items, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    private void increaseCapacity() {
        if (items.length == 0) {
            items = Arrays.copyOf(items, 10);
        }

        items = Arrays.copyOf(items, items.length * 2);
    }

    public void ensureCapacity(int capacity) {
        if (capacity > items.length) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
            modCount++;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (int i = 0; i < size; i++) {
            stringBuilder.append(items[i]);

            if (i < size - 1) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override // Нет реализации
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override // Нет реализации
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override // Нет реализации
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}