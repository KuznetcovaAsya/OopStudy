package ru.oopstudy.kuznetcova.singlylinkedlist;

public class SinglyLinkedList<T> {
    private int count;
    private Node<T> head;

    public SinglyLinkedList() {
        count = 0;
        head = null;
    }

    public int getCount() {
        return count;
    }

    public T getFirstElement() {
        return getByIndex(0);
    }

    public T getByIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException(index + " is illegal for count " + count);
        }

        Node<T> current = head;
        int i = 0;
        while (i < index) {
            current = current.getNext();
            i++;
        }

        return current.getData();
    }

    public T setByIndex(int index, T value) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException(index + " is illegal for count " + count);
        }

        Node<T> current = head;
        int i = 0;

        while (i < index) {
            current = current.getNext();
            i++;
        }

        T firstValue = current.getData();

        current.setData(value);

        return firstValue;
    }

    public void addToHead(T value) {
        if (head == null) {
            head = new Node<>(value);
        } else {
            Node<T> newHead = new Node<>(value);
            newHead.setNext(head);
            head = newHead;
        }

        count++;
    }

    public void addByIndex(T element, int index) {
        if (index < 0 || index > count) {
            throw new IllegalArgumentException(index + " is illegal for count " + count);
        }

        if (index == 0) {
            addToHead(element);
        } else {
            Node<T> current = head;
            int i = 0;

            while (i < index - 1) {
                current = current.getNext();
                i++;
            }

            Node<T> newNode = new Node<>(element);
            newNode.setNext(current.getNext());
            current.setNext(newNode);
            count++;
        }
    }

    public static <T> SinglyLinkedList<T> getCopy(SinglyLinkedList<T> aList) {
        SinglyLinkedList<T> result = new SinglyLinkedList<>();
        Node<T> current = aList.head;

        while (current != null) {
            result.addByIndex(current.getData(), result.count);
            current = current.getNext();
        }

        return result;
    }

    public T removeByIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException(index + " is illegal for count " + count);
        }

        if (index == 0) {
            T firstValue = head.getData();
            head = head.getNext();
            count--;
            return firstValue;
        }

        Node<T> current = head;
        int i = 0;

        while (i < index - 1) {
            current = current.getNext();
            i++;
        }

        T firstValue = current.getNext().getData();
        current.setNext(current.getNext().getNext());
        count--;
        return firstValue;
    }

    public T removeFirstElement() {
        return removeByIndex(0);
    }

    public boolean removeAllWithValue(T value) {
        if (count == 0) {
            return false;
        }

        int deletionsCount = 0;

        while (head.getData().equals(value)) {
            head = head.getNext();
            count--;
            deletionsCount++;

            if (head == null) {
                return true;
            }
        }

        Node<T> current = head;

        while (current.getNext() != null) {
            if (current.getNext().getData().equals(value)) {
                current.setNext(current.getNext().getNext());
                deletionsCount++;
                count--;
            } else {
                current = current.getNext();
            }
        }

        return deletionsCount > 0;
    }

    public void reverse() {
        if (count < 2) {
            return;
        }

        Node<T> current = head;
        Node<T> previous = null;

        while (current != null) {
            Node<T> next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }

        head = previous;
    }

    public void print() {
        System.out.print("[");
        Node<T> current = head;

        while (current != null) {
            System.out.print(current.getData());

            if (current.getNext() != null) {
                System.out.print(", ");
            }

            current = current.getNext();
        }

        System.out.println("]");
    }
}