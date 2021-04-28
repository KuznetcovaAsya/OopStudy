package ru.oopstudy.kuznetcova.hashtable_main;

import ru.oopstudy.kuznetcova.hashtable.HashTable;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable = new HashTable<>();

        hashTable.add(1);
        hashTable.add(2);
        hashTable.add(3);
        hashTable.add(4);
        hashTable.add(1);
        hashTable.add(4);

        ArrayList<Integer> arrayList1 = new ArrayList<>();
        arrayList1.add(11);
        arrayList1.add(12);
        arrayList1.add(0);

        System.out.println(arrayList1 + " Коллекция 1");

        ArrayList<Integer> arrayList2 = new ArrayList<>();
        arrayList2.add(25);
        arrayList2.add(12);
        arrayList2.add(6);

        System.out.println(arrayList2 + " Коллекция 2");

        hashTable.addAll(arrayList1);
        hashTable.addAll(arrayList2);

        System.out.println(hashTable + " Таблица 1");

        System.out.println(hashTable.containsAll(arrayList2) + " - Содержит ли таблица 1 Коллекцию 2");

        ArrayList<Integer> arrayList3 = new ArrayList<>();
        arrayList3.add(12);
        arrayList3.add(3);
        arrayList3.add(0);
        System.out.println(arrayList3 + " Коллекция 3");

        System.out.println(hashTable.removeAll(arrayList3) + " - Удалить из Таблицы 1 Коллекцию 3");
        System.out.println(hashTable + " Таблица 1");

        ArrayList<Integer> arrayList4 = new ArrayList<>();
        arrayList4.add(2);
        arrayList4.add(4);
        arrayList4.add(6);
        System.out.println(arrayList4 + " Коллекция 4");

        System.out.println(hashTable.retainAll(arrayList4) + " - Оставить в Таблица 1 только элементы Коллекции 4");
        System.out.println(hashTable + " Таблица 1");
    }
}