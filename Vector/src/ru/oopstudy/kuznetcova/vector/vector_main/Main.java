package ru.oopstudy.kuznetcova.vector.vector_main;

import ru.oopstudy.kuznetcova.vector.Vector;

public class Main {
    public static void main(String[] args) {
        int size = 10;
        double[] array1 = {1, 2, 3, 4, 5};
        double[] array2 = {2.5, 3, 6, 7, 8.3, 9.5, 10};

        Vector vector = new Vector(10);
        System.out.println(vector + " Вектор размерности " + size);

        Vector vector1 = new Vector(array1);
        System.out.println(vector1 + " Вектор 1");

        Vector vector2 = new Vector(8, array2);
        System.out.println(vector2 + " Вектор 2");

        Vector vector3 = new Vector(vector1);
        System.out.println(vector3 + " Копия вектора 1");

        Vector vector4 = Vector.getSum(vector1, vector2);
        System.out.println(vector4 + " Сумма векторов 1 и 2 (Новый вектор)");

        Vector vector5 = Vector.getDifference(vector1, vector2);
        System.out.println(vector5 + " Разность векторов 1 и 2 (Новый вектор)");

        double dotProduct = Vector.getDotProduct(vector1, vector2);
        System.out.println(dotProduct + " Скалярное произведение векторов 1 и 2");

        vector1.add(vector2);
        System.out.println(vector1 + " Измененный вектор 1. Сумма векторов 1 и 2");

        vector1.subtract(vector2);
        System.out.println(vector1 + " Измененный вектор 1. Разность векторов 1 и 2");

        vector1.getMultiplicationByScalar(5);
        System.out.println(vector1 + " Измененный вектор 1. Умножение вектора 1 на число 5");

        vector1.reverse();
        System.out.println(vector1 + " Разворот вектора 1");

        System.out.println(vector1.getLength() + " Длина вектора");

        System.out.println(vector1.getByIndex(3) + " Элемент по индексу 3 вектора 1");

        vector1.setByIndex(3, 8);
        System.out.println(vector1 + " Установили элемент по индексу 3 в векторе 1");

        System.out.println(vector1.getSize() + " Размерность вектора 1");
    }
}