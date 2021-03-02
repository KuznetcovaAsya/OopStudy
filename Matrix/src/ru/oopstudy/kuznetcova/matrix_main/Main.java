package ru.oopstudy.kuznetcova.matrix_main;

import ru.oopstudy.kuznetcova.matrix.Matrix;
import ru.oopstudy.kuznetcova.matrix.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{1, 2, 3, 4, 5, 7, 8});
        System.out.println(vector1 + " Вектор 1");

        Vector vector2 = new Vector(new double[]{0, 2, 8, 4, 7, 6, 8});
        System.out.println(vector2 + " Вектор 2");

        Vector vector3 = new Vector(new double[]{0, 3, 4, 8, 9});
        System.out.println(vector3 + " Вектор 3");
        System.out.println();

        Matrix matrix1 = new Matrix(new Vector[]{vector1, vector2});
        System.out.println(matrix1 + " Матрица 1 (Из векторов 1 и 2)");
        System.out.println();

        matrix1.setVectorByIndex(1, vector3);
        System.out.println(matrix1 + " Установить в Матрицу 1 Вектор 3 по индексу 1");
        System.out.println();

        System.out.println(matrix1.getVectorByIndexFromColumn(0) + " Получить вектор-столбец по индексу 0 из Матрицы 1");
        System.out.println();

        matrix1.multiplyByVector(vector1);
        System.out.println(matrix1 + " Матрица 1. Умножить Матрицу 1 на Вектор 1");
        System.out.println();

        Matrix matrix2 = new Matrix(new Vector[]{vector2, vector3});
        System.out.println(matrix2 + " Матрица 2 (из Векторов 2 и 3)");
        System.out.println();

        matrix1.add(matrix2);
        System.out.println(matrix1 + " Матрица 1. Сложить Матрицы 1 и 2");
        System.out.println();

        matrix1.subtract(matrix2);
        System.out.println(matrix1 + " Матрица 1. Вычесть Матрицу 2 из Матрицы 1");
        System.out.println();

        Matrix matrix3 = new Matrix(new double[][]{{6, 2, 3, 4}, {1, 7, 3, 4}, {1, 2, 8, 4}, {1, 2, 3, 9}});
        System.out.println(matrix3 + " Матрица 3");
        System.out.println();

        System.out.println(matrix3.getDeterminant() + " Детерминант Матрицы 3");
        System.out.println();

        Matrix matrix4 = Matrix.getSum(matrix1, matrix2);
        System.out.println(matrix4 + " Матрица 4 (Сумма Матриц 1 и 2)");
        System.out.println();

        Matrix matrix5 = Matrix.getDifference(matrix1, matrix2);
        System.out.println(matrix5 + " Матрица 5 (Разница Матриц 1 и 2)");
        System.out.println();

        Matrix matrix6 = Matrix.getProduct(matrix1, matrix2);
        System.out.println(matrix6 + " Произведение Векторов 1 и 2");
        System.out.println();

        matrix6.transposed();
        System.out.println(matrix6 + " Транспонированная Матрица 6");
    }
}