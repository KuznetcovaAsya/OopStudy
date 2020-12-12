package ru.oopstudy.kuznetcova.singlylinkedlist_main;

import ru.oopstudy.kuznetcova.singlylinkedlist.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {

        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();

        list1.addToHead(7);
        list1.addToHead(5);
        list1.addByIndex(7, 2);
        list1.addByIndex(3, 3);
        list1.addByIndex(8, 4);
        list1.addByIndex(4, 5);
        list1.addByIndex(1, 6);

        System.out.println("Список 1: ");
        list1.print();

        SinglyLinkedList<Integer> list2 = SinglyLinkedList.getCopy(list1);
        System.out.println("Список 2 (Копия списка 1): ");
        list2.print();

        int indexToDelete = 0;
        int elementToDelete = 7;

        System.out.println("Список 2. Удален элемент " + list2.removeByIndex(indexToDelete) + " с индексом " + indexToDelete);
        list2.print();

        System.out.println("Список 2. Запрос на удаление элемента " + elementToDelete + ". Результат: " + list2.removeAllWithValue(elementToDelete));
        list2.print();

        System.out.println("Длина списка 1 равна: " + list1.getCount());

        int indexForSearch = 1;
        System.out.println("Список 2. Значение элемента по индексу " + indexForSearch + " равно: " + list2.getByIndex(indexForSearch));

        int indexForChange = 3;
        int elementForChange = 4;
        System.out.println("Список 2. Установить элемент " + elementForChange + " по индексу " + indexForChange +
                ". Старое значение: " + list2.setByIndex(indexForChange, elementForChange));
        list2.print();

        System.out.println("Развернутый список: ");
        list2.reverse();
        list2.print();
    }
}