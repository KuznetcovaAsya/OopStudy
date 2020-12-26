package ru.oopstudy.kuznetcova.singly_linked_list_main;

import ru.oopstudy.kuznetcova.singly_linked_list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();

        list1.addFirst(1);
        list1.addFirst(2);
        list1.addByIndex(2, 3);
        list1.addByIndex(3, null);
        list1.addByIndex(4, 4);
        list1.addByIndex(5, 5);
        list1.addByIndex(6, 6);
        list1.addByIndex(7, 7);
        list1.addByIndex(8, 8);
        list1.addByIndex(9, 9);
        list1.addByIndex(10, 10);

        System.out.println("Список №1: " + list1);

        SinglyLinkedList<Integer> list2 = list1.getCopy();

        System.out.println("Список №2 (Копия списка №1): " + list2);

        int indexToDelete = 0;
        int elementToDelete = 8;

        System.out.println("Список №2. Удален элемент " + list2.removeByIndex(indexToDelete) + " с индексом " + indexToDelete + ": " + list2);

        System.out.println("Список №2. Запрос на удаление элемента " + elementToDelete + ". Результат: " + list2.removeByData(elementToDelete) + ". " + list2);

        System.out.println("Длина списка №2 равна: " + list2.getCount());

        int indexForSearch = 0;
        System.out.println("Список №2. Значение элемента по индексу " + indexForSearch + " равно: " + list2.getByIndex(indexForSearch));

        int indexForChange = 3;
        int elementForChange = 2;
        System.out.println("Список №2. Установить элемент " + elementForChange + " по индексу " + indexForChange +
                ". Старое значение: " + list2.setByIndex(indexForChange, elementForChange) + ". " + list2);

        list2.reverse();
        System.out.println("Развернутый список: " + list2);
    }
}