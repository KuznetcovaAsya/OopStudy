package ru.oopstudy.kuznetcova.vector;

import java.util.Arrays;

public class Vector {
    private double[] vectorElements;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Вместимость = " + size + " Вместимость должна быть > 0");
        }

        vectorElements = new double[size];
    }

    public Vector(double[] vectorElements) {
        if (vectorElements == null) {
            throw new NullPointerException("Массив не может быть null");
        }

        if (vectorElements.length == 0) {
            throw new IllegalArgumentException(vectorElements.length +
                    " Длина массива должна быть > 0");
        }

        this.vectorElements = new double[vectorElements.length];
        System.arraycopy(vectorElements, 0, this.vectorElements, 0, vectorElements.length);
    }

    public Vector(Vector vector) {
        this(vector.vectorElements);
    }

    public Vector(int size, double[] vectorElements) {
        if (vectorElements == null) {
            throw new NullPointerException("Массив не может быть null");
        }

        if (vectorElements.length == 0) {
            throw new IllegalArgumentException(vectorElements.length +
                    " Длина массива должна быть > 0");
        }

        if (size <= 0) {
            throw new IllegalArgumentException("Вместимость = " + size + " Вместимость должна быть > 0");
        }

        this.vectorElements = new double[size];
        int elementsMinCount = Math.min(size, vectorElements.length);
        System.arraycopy(vectorElements, 0, this.vectorElements, 0, elementsMinCount);
    }

    public int getSize() {
        return vectorElements.length;
    }

    public void add(Vector vector) {
        if (vectorElements.length >= vector.vectorElements.length) {
            for (int i = 0; i < vector.vectorElements.length; i++) {
                vectorElements[i] += vector.vectorElements[i];
            }
        } else {
            double[] maxVector = new double[vector.vectorElements.length];

            for (int i = 0; i < vectorElements.length; i++) {
                maxVector[i] = vector.vectorElements[i] + vectorElements[i];
            }

            System.arraycopy(vector.vectorElements, vectorElements.length, maxVector, vectorElements.length,
                    vector.vectorElements.length - vectorElements.length);

            vectorElements = maxVector;
        }
    }

    public void subtract(Vector vector) {
        if (vectorElements.length >= vector.vectorElements.length) {
            for (int i = 0; i < vector.vectorElements.length; i++) {
                vectorElements[i] -= vector.vectorElements[i];
            }
        } else {
            double[] maxVector = new double[vector.vectorElements.length];

            for (int i = 0; i < vectorElements.length; i++) {
                maxVector[i] = vector.vectorElements[i] - vectorElements[i];
            }

            for (int i = vectorElements.length; i < vector.vectorElements.length; i++) {
                maxVector[i] = -vector.vectorElements[i];
            }

            vectorElements = maxVector;
        }
    }

    public void getMultiplicationByScalar(double number) {
        for (int i = 0; i < vectorElements.length; i++) {
            vectorElements[i] *= number;
        }
    }

    public void reverse() {
        getMultiplicationByScalar(-1);
    }

    public double getLength() {
        double squaresSum = 0;

        for (double element : vectorElements) {
            squaresSum += element * element;
        }

        return Math.sqrt(squaresSum);
    }

    public double getByIndex(int index) {
        if (index < 0 || index >= vectorElements.length) {
            throw new ArrayIndexOutOfBoundsException("Индекс = " + index +
                    "Индекс должен быть >= 0, индекс должен быть < " + vectorElements.length);
        }

        return vectorElements[index];
    }

    public void setByIndex(int index, double number) {
        if (index < 0 || index >= vectorElements.length) {
            throw new ArrayIndexOutOfBoundsException("Индекс = " + index +
                    "Индекс должен быть >= 0, индекс должен быть < " + vectorElements.length);
        }

        vectorElements[index] = number;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        int finalSize = Math.max(vector1.vectorElements.length, vector2.vectorElements.length);

        Vector sum = new Vector(finalSize);

        sum.add(vector1);
        sum.add(vector2);

        return sum;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        int finalSize = Math.max(vector1.vectorElements.length, vector2.vectorElements.length);

        Vector difference = new Vector(finalSize);

        difference.add(vector1);
        difference.subtract(vector2);

        return difference;
    }

    public static double getDotProduct(Vector vector1, Vector vector2) {
        double dotProduct = 0;
        int minSize = Math.min(vector1.vectorElements.length, vector2.vectorElements.length);

        for (int i = 0; i < minSize; i++) {
            dotProduct += vector1.vectorElements[i] * vector2.vectorElements[i];
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

        if (vectorElements.length != vector.vectorElements.length) {
            return false;
        }

        for (int i = 0; i < vectorElements.length; i++) {
            if (vectorElements[i] != vector.vectorElements[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(vectorElements);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (int i = 0; i < vectorElements.length; i++) {
            stringBuilder.append(vectorElements[i]);

            if (i < vectorElements.length - 1) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}