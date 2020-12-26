package ru.oopstudy.kuznetcova.singly_linked_list;

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
        if (head == null) {
            head = new ListItem<>(data);
        } else {
            head = new ListItem<>(data, head);
        }

        count++;
    }

    public void addByIndex(int index, T data) {
        checkPositionIndex(index);

        if (index == 0) {
            addFirst(data);
        } else {
            ListItem<T> current = iterateToItem(index - 1);

            ListItem<T> newListItem = new ListItem<>(data);
            newListItem.setNext(current.getNext());
            current.setNext(newListItem);
            count++;
        }
    }

    public T getFirst() {
        if (count == 0) {
            throw new IllegalArgumentException("Список пуст");
        }

        return head.getData();
    }

    public T getByIndex(int index) {
        checkElementIndex(index);

        ListItem<T> current = iterateToItem(index);

        return current.getData();
    }

    public T setByIndex(int index, T data) {
        checkPositionIndex(index);

        ListItem<T> current = iterateToItem(index);
        T prevValue = current.getData();
        current.setData(data);
        return prevValue;
    }

    public SinglyLinkedList<T> getCopy() {
        SinglyLinkedList<T> result = new SinglyLinkedList<>();

        if (head == null) {
            return result;
        }

        ListItem<T> current = head;
        result.head = new ListItem<>(head.getData());
        ListItem<T> tail = result.head;

        while (current.getNext() != null) {
            tail.setNext(new ListItem<>(current.getNext().getData()));
            tail = tail.getNext();
            current = current.getNext();
        }

        result.count = count;
        return result;
    }

    public T removeByIndex(int index) {
        checkPositionIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> current = iterateToItem(index - 1);

        T removed = current.getNext().getData();
        current.setNext(current.getNext().getNext());
        count--;
        return removed;
    }

    public T removeFirst() {
        if (count == 0) {
            throw new IllegalArgumentException("Список пуст");
        }

        T removed = head.getData();
        head = head.getNext();
        count--;
        return removed;
    }

    public boolean removeByData(T data) {
        if (count == 0) {
            throw new IllegalArgumentException("Список пуст");
        }

        if (Objects.equals(head.getData(), data)) {
            head = head.getNext();
            count--;
            return true;
        }

        ListItem<T> current = head;
        boolean isDeleted = false;

        while (current.getNext() != null) {
            if (Objects.equals(current.getNext().getData(), data)) {
                current.setNext(current.getNext().getNext());
                isDeleted = true;
                count--;
            }

            current = current.getNext();
        }

        return isDeleted;
    }

    public void reverse() {
        if (count < 2) {
            return;
        }

        ListItem<T> current = head;
        ListItem<T> previous = null;

        while (current != null) {
            ListItem<T> next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;

        }

        head = previous;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        ListItem<T> current = head;

        while (current != null) {
            stringBuilder.append(current.getData());

            if (current.getNext() != null) {
                stringBuilder.append(", ");
            }

            current = current.getNext();
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
            throw new IndexOutOfBoundsException("Индекс: " + index + ". Индекс должен быть >= 0 и < " + count);
        }
    }

    private ListItem<T> iterateToItem(int index) {
        ListItem<T> current = head;
        int i = 0;

        while (i < index) {
            current = current.getNext();
            i++;
        }

        return current;
    }
}