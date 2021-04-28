package ru.oopstudy.kuznetcova.hashtable;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private static final int DEFAULT_CAPACITY = 11;

    private ArrayList<T>[] lists;
    private int size;
    private int modCount;

    public HashTable() {
        lists = new ArrayList[DEFAULT_CAPACITY];
    }

    public HashTable(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Вместимость = " + initialCapacity + ". Вместимость должна быть > 0");
        }

        lists = new ArrayList[initialCapacity];
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

        return lists[index].contains(o) && lists[index] != null;
    }

    private class HashTableIterator implements Iterator<T> {
        private int generalIndex = -1;
        private int elementIndex = -1;
        private int listIndex = 0;
        private final int initialModCount = modCount;

        public boolean hasNext() {
            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Список был изменен");
            }

            return generalIndex + 1 < size;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Выход за пределы таблицы = " + generalIndex + ", размер = " + size + ")");
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
        return Math.abs(o.hashCode() % lists.length);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + DEFAULT_CAPACITY;
        hash = prime * hash + Arrays.hashCode(lists);
        hash = prime * hash + size;
        hash = prime * hash + modCount;

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        HashTable<T> hashTable = (HashTable<T>) obj;

        return Arrays.equals(lists, hashTable.lists);
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