package ru.oopstudy.kuznetcova.matrix;

import java.util.Arrays;

public class Vector {
    private double[] elements;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер = " + size + " Размер должен быть > 0");
        }

        elements = new double[size];
    }

    public Vector(double[] elements) {
        if (elements == null) {
            throw new NullPointerException("Массив не может быть null");
        }

        if (elements.length == 0) {
            throw new IllegalArgumentException("Длина массива: " + elements.length + " Длина массива должна быть > 0");
        }

        this.elements = Arrays.copyOf(elements, elements.length);
    }

    public Vector(Vector vector) {
        this(vector.elements);
    }

    public Vector(int size, double[] elements) {
        if (elements == null) {
            throw new NullPointerException("Массив не может быть null");
        }

        if (size <= 0) {
            throw new IllegalArgumentException("Размер = " + size + " Размер должен быть > 0");
        }

        this.elements = Arrays.copyOf(elements, size);
    }

    public int getSize() {
        return elements.length;
    }

    public void add(Vector vector) {
        if (elements.length < vector.elements.length) {
            elements = Arrays.copyOf(elements, vector.elements.length);
        }

        for (int i = 0; i < vector.elements.length; i++) {
            elements[i] += vector.elements[i];
        }
    }

    public void subtract(Vector vector) {
        if (elements.length < vector.elements.length) {
            elements = Arrays.copyOf(elements, vector.elements.length);
        }

        for (int i = 0; i < vector.elements.length; i++) {
            elements[i] -= vector.elements[i];
        }
    }

    public void multiplyByScalar(double number) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] *= number;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double squaresSum = 0;

        for (double element : elements) {
            squaresSum += element * element;
        }

        return Math.sqrt(squaresSum);
    }

    public double getByIndex(int index) {
        if (index < 0 || index >= elements.length) {
            throw new IndexOutOfBoundsException("Индекс = " + index + " Индекс должен быть >= 0, индекс должен быть < " + elements.length);
        }

        return elements[index];
    }

    public void setByIndex(int index, double number) {
        if (index < 0 || index >= elements.length) {
            throw new IndexOutOfBoundsException("Индекс = " + index + " Индекс должен быть >= 0, индекс должен быть < " + elements.length);
        }

        elements[index] = number;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector sum = new Vector(vector1);
        sum.add(vector2);

        return sum;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector difference = new Vector(vector1);
        difference.subtract(vector2);

        return difference;
    }

    public static double getDotProduct(Vector vector1, Vector vector2) {
        double dotProduct = 0;
        int minSize = Math.min(vector1.elements.length, vector2.elements.length);

        for (int i = 0; i < minSize; i++) {
            dotProduct += vector1.elements[i] * vector2.elements[i];
        }

        return dotProduct;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Vector vector = (Vector) obj;

        if (elements.length != vector.elements.length) {
            return false;
        }

        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != vector.elements[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (double element : elements) {
            stringBuilder.append(element);
            stringBuilder.append(", ");
        }

        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}