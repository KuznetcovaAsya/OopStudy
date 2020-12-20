package ru.oopstudy.kuznetcova.vector_main;

import ru.oopstudy.kuznetcova.vector.Vector;

public class Main {
    public static void main(String[] args) {
        int size = 10;
        double[] array1 = {2.5, 3, 6, 7, 8.3, 9.5, 10};
        double[] array2 = {1, 2, 3, 4, 5};

        Vector vector = new Vector(size);
        System.out.println("Вектор размерности " + vector.getSize() + ": " + vector);

        Vector vector1 = new Vector(array1);
        System.out.println("Вектор №1: " + vector1);

        Vector vector2 = new Vector(6, array2);
        System.out.println("Вектор №2: " + vector2);

        Vector vector3 = new Vector(vector1);
        System.out.println("Копия вектора №1: " + vector3);

        Vector vector4 = Vector.getSum(vector1, vector2);
        System.out.println("Сумма векторов №1 и №2 (Новый вектор): " + vector4);

        Vector vector5 = Vector.getDifference(vector1, vector2);
        System.out.println("Разность векторов №1 и №2 (Новый вектор): " + vector5);

        double dotProduct = Vector.getDotProduct(vector1, vector2);
        System.out.println("Скалярное произведение векторов №1 и №2: " + dotProduct);

        vector1.add(vector2);
        System.out.println("Измененный вектор №1. Сумма векторов №1 и №2: " + vector1);

        vector1.subtract(vector2);
        System.out.println("Измененный вектор №1. Разность векторов №1 и №2: " + vector1);

        vector1.multiplyByScalar(5);
        System.out.println("Измененный вектор №1. Умножение вектора №1 на число 5: " + vector1);

        vector1.reverse();
        System.out.println("Разворот вектора №1: " + vector1);

        System.out.println("Длина вектора №1: " + vector1.getLength());

        System.out.println("Элемент по индексу 3 вектора №1: " + vector1.getByIndex(3));

        vector1.setByIndex(3, 8);
        System.out.println("Установили элемент по индексу 3 в векторе №1: " + vector1);

        System.out.println("Размерность вектора №1: " + vector1.getSize());
    }
}