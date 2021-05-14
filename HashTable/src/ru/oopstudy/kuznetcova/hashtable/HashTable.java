package ru.oopstudy.kuznetcova.hashtable;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private static final int DEFAULT_ARRAY_LENGTH = 11;

    private final ArrayList<T>[] lists;
    private int size;
    private int modCount;

    public HashTable() {
        lists = new ArrayList[DEFAULT_ARRAY_LENGTH];
    }

    public HashTable(int arrayLength) {
        if (arrayLength <= 0) {
            throw new IllegalArgumentException("Длина массива = " + arrayLength + ". Длина массива должна быть > 0");
        }

        lists = new ArrayList[arrayLength];
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
    public boolean contains(Object o) {
        int index = getListIndex(o);

        return lists[index] != null && lists[index].contains(o);
    }

    private class HashTableIterator implements Iterator<T> {
        private int generalIndex = -1;
        private int elementIndex = -1;
        private int listIndex = 0;
        private final int initialModCount = modCount;

        public boolean hasNext() {
            return generalIndex + 1 < size;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Нет следующего элемента. Индекс = " + generalIndex + ". Размер = " + size);
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Список был изменен");
            }

            while (lists[listIndex] == null || lists[listIndex].isEmpty() || (elementIndex + 1) == lists[listIndex].size()) {
                ++listIndex;
                elementIndex = -1;
            }

            ++elementIndex;
            ++generalIndex;

            return lists[listIndex].get(elementIndex);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        int i = 0;

        for (T e : this) {
            array[i] = e;
            i++;
        }

        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            return (T1[]) Arrays.copyOf(toArray(), size, a.getClass());
        }

        System.arraycopy(toArray(), 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(T element) {
        int index = getListIndex(element);

        createList(index);

        boolean isChanged = lists[index].add(element);

        if (isChanged) {
            ++size;
            ++modCount;
        }

        return isChanged;
    }

    private void createList(int index) {
        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }
    }

    @Override
    public boolean remove(Object o) {
        int index = getListIndex(o);

        if (lists[index] == null) {
            return false;
        }

        if (lists[index].remove(o)) {
            --size;
            ++modCount;
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object e : collection) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        boolean isChanged = false;

        for (T e : collection) {
            if (add(e)) {
                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        boolean isRemoved = false;
        size = 0;

        for (ArrayList<T> list : lists) {
            if (list != null) {
                if (list.removeAll(collection)) {
                    isRemoved = true;
                }

                size += list.size();
            }
        }

        if (isRemoved) {
            ++modCount;
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean isRemoved = false;
        size = 0;

        for (ArrayList<T> list : lists) {
            if (list != null) {
                if (list.retainAll(collection)) {
                    isRemoved = true;
                }

                size += list.size();
            }
        }

        if (isRemoved) {
            ++modCount;
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (ArrayList<T> list : lists) {
            if (list != null) {
                list.clear();
            }
        }

        size = 0;
        ++modCount;
    }

    private int getListIndex(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.abs(o.hashCode() % lists.length);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (int i = 0; i < lists.length; i++) {
            stringBuilder.append(lists[i]);

            if (i < lists.length - 1) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}