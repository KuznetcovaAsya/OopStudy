package ru.oopstudy.kuznetcova.arraylist_main;

import ru.oopstudy.kuznetcova.arraylist.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(5);
        list1.add(4);
        list1.add(3);
        list1.add(2);
        list1.add(1);
        list1.add(0);
        list1.add(5);
        list1.add(5);
        System.out.println("Список 1: " + list1);

        ArrayList<Integer> collection1 = new ArrayList<>();
        collection1.add(1);
        collection1.add(2);
        collection1.add(3);
        collection1.add(4);
        collection1.add(5);
        collection1.add(3);
        collection1.add(7);
        System.out.println("Коллекция 1: " + collection1);

        ArrayList<Integer> collection2 = new ArrayList<>(6);
        collection2.add(1);
        collection2.add(70);
        collection2.add(7);
        collection2.add(13);
        collection2.add(17);
        collection2.add(5);
        System.out.println("Коллекция 2: " + collection2);

        int indexToAdd = 3;
        list1.addAll(3, collection1);
        System.out.println("Список 1 + коллекция 1 (с индекса " + indexToAdd + "): " + list1);

        list1.retainAll(collection2);
        System.out.println("Оставить только те элементы, которые есть в коллекции 2: " + list1);

        int number = 17;
        int indexToAdd2 = 2;
        System.out.println("Установить значение " + number + " по индексу " + indexToAdd2 + ": ");
        list1.set(indexToAdd2, number);
        System.out.println("Список 1: " + list1);

        System.out.println("Список 1 + коллекция 2: " + list1.addAll(collection2));
        System.out.println(list1);

        System.out.println("Удалить из списка 1 коллекцию 2: " + list1.removeAll(collection2));
        System.out.println(list1);
    }
}