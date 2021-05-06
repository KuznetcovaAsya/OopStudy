package ru.oopstudy.kuznetcova.tree_main;

import ru.oopstudy.kuznetcova.tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree1 = new Tree<>(50);

        tree1.add(5);
        tree1.add(15);
        tree1.add(25);
        tree1.add(17);
        tree1.add(63);
        tree1.add(125);
        tree1.add(29);
        tree1.add(19);
        System.out.println("Дерево 1:");
        System.out.println(tree1);

        System.out.println(tree1.getRootData() + " Корень Дерева 1");

        tree1.add(32);
        System.out.println("Добавили элемент 32:");
        System.out.println(tree1);

        tree1.remove(125);
        System.out.println("Удалить элемент 125:");
        System.out.println(tree1);

        System.out.println(tree1.size() + " Количество элементов Дерева 1");

        System.out.println(tree1.contains(27) + " Содержит ли Дерево 1 элемнет 27");

        System.out.println("Добавить null в Дерево 1:");
        tree1.add(null);
        System.out.println(tree1);

        System.out.println(tree1.contains(null) + " Содержит ли Дерево 1 null");

        System.out.println(tree1.remove(null) + " Удалить null из Дерева 1:");
        System.out.println(tree1);
    }
}