package ru.oopstudy.kuznetcova;

import java.util.Arrays;

public class Vector {
    private double[] values;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Вместимость должна быть больше 0");
        }

        this.values = new double[size];
    }

    public Vector(double[] values) {
        this.values = new double[values.length];
        System.arraycopy(values, 0, this.values, 0, values.length);
    }

    public Vector(Vector vector) {
        this(vector.values);
    }

    public Vector(int size, double[] values) {
        this.values = new double[size];
        Arrays.fill(this.values, values.length, size, 0);
        for (int i = 0; i < size && i < values.length; i++) {
            this.values[i] = values[i];
        }
    }

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    public int getSize() {
        return values.length;
    }

    public void add(Vector vector) {
        for (int i = 0; i < values.length && i < vector.values.length; i++) {
            values[i] = values[i] + vector.values[i];
        }
    }

    public void deduct(Vector vector) {
        for (int i = 0; i < values.length && i < vector.values.length; i++) {
            values[i] = values[i] - vector.values[i];
        }
    }

    public void multiplicandByScalar(int number) {
        for (int i = 0; i < values.length; i++) {
            values[i] = values[i] * number;
        }
    }

    public void reverse() {
        multiplicandByScalar(-1);
    }

    public double getLength() {
        double squaresSum = 0;

        for (double element : values) {
            squaresSum += element * element;
        }

        return Math.sqrt(squaresSum);
    }

    public double getByIndex(int index) {
        if (index < 0 || index >= values.length) {
            throw new IllegalArgumentException("Неверный индекс");
        }

        return values[index];
    }

    public void setByIndex(int index, double number) {
        if (index < 0 || index >= values.length) {
            throw new IllegalArgumentException("Неверный индекс");
        }

        values[index] = number;
    }

    public static Vector sum(Vector vector1, Vector vector2) {
        double[] sum = new double[Math.max(vector1.values.length, vector2.values.length)];
        for (int i = 0; i < sum.length; i++) {
            sum[i] = (i < vector1.values.length ? vector1.values[i] : 0) +
                    (i < vector2.values.length ? vector2.values[i] : 0);
        }

        return new Vector(sum);
    }

    public static Vector difference(Vector vector1, Vector vector2) {
        double[] difference = new double[Math.max(vector1.values.length, vector2.values.length)];
        for (int i = 0; i < difference.length; i++) {
            difference[i] = (i < vector1.values.length ? vector1.values[i] : 0) -
                    (i < vector2.values.length ? vector2.values[i] : 0);
        }

        return new Vector(difference);
    }

    public static double dotProduct(Vector vector1, Vector vector2) {
        double dotProduct = 0;

        for (int i = 0; i < vector1.values.length && i < vector2.values.length; i++) {
            dotProduct += vector1.values[i] * vector2.values[i];
        }

        return dotProduct;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Vector vector = (Vector) obj;
        if (values.length != vector.values.length) return false;
        for (int i = 0; i < values.length; i++) {
            if (values[i] != vector.values[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(values);
    }

    @Override
    public String toString() {
        return Arrays.toString(values);
    }
}