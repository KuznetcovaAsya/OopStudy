package ru.oopstudy.kuznetcova.singly_linked_list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private int count;
    private ListItem<T> head;

    public SinglyLinkedList() {
    }

    public int getCount() {
        return count;
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public void addByIndex(int index, T data) {
        checkPositionIndex(index);

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListItem<T> previousItem = getItem(index - 1);

        ListItem<T> newItem = new ListItem<>(data, previousItem.getNext());
        previousItem.setNext(newItem);
        count++;
    }

    public T getFirst() {
        if (count == 0) {
            throw new NoSuchElementException("Список пуст");
        }

        return head.getData();
    }

    public T getByIndex(int index) {
        checkElementIndex(index);

        ListItem<T> item = getItem(index);
        return item.getData();
    }

    public T setByIndex(int index, T data) {
        checkPositionIndex(index);

        ListItem<T> item = getItem(index);
        T previousData = item.getData();
        item.setData(data);
        return previousData;
    }

    public SinglyLinkedList<T> getCopy() {
        SinglyLinkedList<T> result = new SinglyLinkedList<>();

        if (head == null) {
            return result;
        }

        ListItem<T> item = head;
        result.head = new ListItem<>(head.getData());
        ListItem<T> itemCopy = result.head;
        int i = 0;

        while (i < count - 1) {
            ListItem<T> nextItem = item.getNext();
            itemCopy.setNext(new ListItem<>(nextItem.getData()));
            itemCopy = itemCopy.getNext();
            item = nextItem;
            i++;
        }

        result.count = count;
        return result;
    }

    public T removeByIndex(int index) {
        checkPositionIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> previousItem = getItem(index - 1);

        T removedData = previousItem.getNext().getData();
        previousItem.setNext(previousItem.getNext().getNext());
        count--;
        return removedData;
    }

    public T removeFirst() {
        if (count == 0) {
            throw new NoSuchElementException("Список пуст");
        }

        T removedData = head.getData();
        head = head.getNext();
        count--;
        return removedData;
    }

    public boolean removeByData(T data) {
        if (count == 0) {
            return false;
        }

        if (Objects.equals(head.getData(), data)) {
            removeFirst();
            return true;
        }

        ListItem<T> item = head;
        int i = 0;

        while (i < count - 1) {
            ListItem<T> nextItem = item.getNext();

            if (Objects.equals(nextItem.getData(), data)) {
                item.setNext(nextItem.getNext());
                count--;
                return true;
            }

            i++;
            item = nextItem;
        }

        return false;
    }

    public void reverse() {
        if (count < 2) {
            return;
        }

        ListItem<T> item = head;
        ListItem<T> previousItem = null;

        while (item != null) {
            ListItem<T> nextItem = item.getNext();
            item.setNext(previousItem);
            previousItem = item;
            item = nextItem;

        }

        head = previousItem;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        ListItem<T> item = head;

        while (item != null) {
            stringBuilder.append(item.getData());

            if (item.getNext() != null) {
                stringBuilder.append(", ");
            }

            item = item.getNext();
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ". Индекс должен быть >= 0 и < " + count);
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ". Индекс должен быть >= 0 и <= " + count);
        }
    }

    private ListItem<T> getItem(int index) {
        ListItem<T> item = head;
        int i = 0;

        while (i < index) {
            item = item.getNext();
            i++;
        }

        return item;
    }
}