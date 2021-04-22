package ru.oopstudy.kuznetcova.matrix_main;

import ru.oopstudy.kuznetcova.matrix.Matrix;
import ru.oopstudy.kuznetcova.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{1, 2, 3, 4, 5, 7, 8});
        System.out.println(vector1 + " Вектор 1");

        Vector vector2 = new Vector(new double[]{0, 2, 8, 4, 7, 6, 8});
        System.out.println(vector2 + " Вектор 2");

        Vector vector3 = new Vector(new double[]{0, 3, 4, 8, 9, 0, 2});
        System.out.println(vector3 + " Вектор 3");
        System.out.println();

        Matrix matrix1 = new Matrix(new Vector[]{vector1, vector2});
        System.out.println(matrix1 + " Матрица 1 (Из векторов 1 и 2)");
        System.out.println();

        matrix1.setRow(1, vector3);
        System.out.println(matrix1 + " Матрица 1. Установить в Матрицу 1 Вектор 3 по индексу 1");
        System.out.println();

        System.out.println(matrix1.getColumn(0) + " Получить вектор-столбец по индексу 0 из Матрицы 1");
        System.out.println();

        matrix1.multiplyByVector(new Vector(vector1));
        System.out.println(matrix1 + " Матрица 1. Умножить Матрицу 1 на Вектор 1");
        System.out.println();

        Matrix matrix2 = new Matrix(new Vector[]{vector2, vector3});
        System.out.println(matrix2 + " Матрица 2 (из Векторов 2 и 3)");
        System.out.println();

        matrix2.add(matrix1);
        System.out.println(matrix2 + " Матрица 2. Сложить Матрицы 2 и 1");
        System.out.println();

        matrix2.subtract(matrix1);
        System.out.println(matrix2 + " Матрица 2. Вычесть Матрицу 1 из Матрицы 2");
        System.out.println();

        Matrix matrix3 = new Matrix(new double[][]{{6, 2, 3, 4}, {1, 7, 3, 4}, {2, 8, 9, 0}});
        System.out.println(matrix3 + " Матрица 3");
        System.out.println();

        Matrix matrix4 = Matrix.getSum(matrix2, matrix1);
        System.out.println(matrix4 + " Матрица 4 (Сумма Матриц 2 и 1)");
        System.out.println();

        Matrix matrix5 = Matrix.getDifference(matrix2, matrix1);
        System.out.println(matrix5 + " Матрица 5 (Разница Матриц 2 и 1)");
        System.out.println();

        Matrix matrix6 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {0, 5, 0}});
        System.out.println(matrix6 + " Матрица 6");
        System.out.println();

        Vector vector4 = new Vector(new double[]{1, 2, 3});
        System.out.println(vector4 + " Вектор 4");
        System.out.println();

        System.out.println(matrix6.multiplyByVector(vector4) + " Умножение Матрицы 6 на Вектор 4");
        System.out.println();

        Matrix matrix7 = new Matrix(new double[][]{{6, 2, 3}, {1, 7, 3}, {0, 7, 9}});
        System.out.println(matrix7 + " Матрица 7");
        System.out.println();

        Vector vector5 = new Vector(new double[]{1, 2, 3});
        System.out.println(vector5 + " Вектор 5");
        System.out.println();

        System.out.println(matrix7.multiplyByVector(vector5) + " Умножение Матрицы 7 на Вектор 4");
        System.out.println();

        Matrix matrix8 = new Matrix(new double[][]{{6, 2, 3}, {1, 7, 3}, {0, 7, 9}});
        System.out.println(matrix8 + " Матрица 8");
        System.out.println();

        Matrix matrix9 = Matrix.getProduct(matrix7, matrix8);
        System.out.println(matrix9 + " Умножение Матрицы 7 на Матрицу 8");
        System.out.println();

        matrix9.transpose();
        System.out.println(matrix9 + " Транспонированная Матрица 9");
        System.out.println();

        System.out.println(matrix9.getRow(1) + " Строка по индексу 1 Матрицы 9");
        System.out.println();

        matrix9.multiplyByScalar(2);
        System.out.println(matrix9 + " Матрица 9 умноженная на число 2");
        System.out.println();

        System.out.println(matrix9.getDeterminant() + " Детерминант Матрицы 9");
        System.out.println();
    }
}