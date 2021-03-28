package ru.oopstudy.kuznetcova.matrix;

import ru.oopstudy.kuznetcova.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] vectors;

    public Matrix(int rowsCount, int columnsCount) {
        if (columnsCount <= 0 || rowsCount <= 0) {
            throw new IllegalArgumentException("Длина = " + columnsCount + " , высота = " + rowsCount + ". Размеры матрицы должны быть > 0");
        }

        vectors = new Vector[rowsCount];

        for (int i = 0; i < vectors.length; i++) {
            vectors[i] = new Vector(columnsCount);
        }
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new NullPointerException("Массив не может быть null");
        }

        int matrixLength = getMaxVectorLengthFromArray(array);

        vectors = new Vector[array.length];

        for (int i = 0; i < vectors.length; i++) {
            vectors[i] = new Vector(matrixLength);

            for (int j = 0; j < matrixLength; j++) {
                if (j < array[i].length) {
                    vectors[i].setByIndex(j, array[i][j]);
                } else {
                    vectors[i].setByIndex(j, 0);
                }
            }
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.vectors);
    }

    public Matrix(Vector[] vectors) {
        int columnsCount = getMaxVectorLengthFromVectors(vectors);

        this.vectors = new Vector[vectors.length];

        for (int i = 0; i < this.vectors.length; i++) {
            this.vectors[i] = new Vector(columnsCount);

            for (int j = 0; j < columnsCount; j++) {
                if (j < vectors[i].getSize()) {
                    this.vectors[i].setByIndex(j, vectors[i].getByIndex(j));
                } else {
                    this.vectors[i].setByIndex(j, 0);
                }
            }
        }
    }

    public int getLength() {
        return vectors[0].getSize();
    }

    public int getHeight() {
        return vectors.length;
    }

    public Vector getVectorByIndexFromRow(int index) {
        if (index < 0 || index >= getHeight()) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ". Индекс должен быть >= 0 и < " + getHeight());
        }

        return new Vector(vectors[index]);
    }

    public void setVectorByIndex(int index, Vector vector) {
        if (vector.getSize() > getLength()) {
            throw new IndexOutOfBoundsException("Длина вектора: " + vector.getSize() + ": Длина вектора должна быть <= " + getLength());
        }

        if (index >= getHeight()) {
            throw new ArrayIndexOutOfBoundsException("Индекс = " + index + ": Индекс должен быть < " + getHeight());
        }

        int vectorLength = vector.getSize();

        for (Vector value : vectors) {
            for (int j = 0; j < value.getSize(); j++) {
                if (j < vectorLength) {
                    vectors[index].setByIndex(j, vector.getByIndex(j));
                } else {
                    vectors[index].setByIndex(j, 0);
                }
            }
        }
    }

    public Vector getVectorByIndexFromColumn(int index) {
        if (index < 0 || index >= getLength()) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ". Индекс должен быть >= 0 и < " + getLength());
        }

        double[] vector = new double[getHeight()];

        for (int i = 0; i < vectors.length; i++) {
            for (int j = 0; j < vectors[i].getSize(); j++) {
                if (j == index) {
                    vector[i] = vectors[i].getByIndex(j);
                }
            }
        }

        return new Vector(vector);
    }

    public void multiplyByScalar(double number) {
        for (Vector vector : vectors) {
            vector.multiplyByScalar(number);
        }
    }

    public void multiplyByVector(Vector vector) {
        if (vector.getSize() > getLength()) {
            throw new IndexOutOfBoundsException("Длина вектора: " + vector.getSize() + ": Длина вектора должна быть <= " + getLength());
        }

        for (Vector value : vectors) {
            for (int j = 0; j < value.getSize(); j++) {
                if (j < vector.getSize()) {
                    value.setByIndex(j, value.getByIndex(j) * vector.getByIndex(j));
                } else {
                    value.setByIndex(j, 0);
                }
            }
        }
    }

    public void transposed() {
        Vector[] transposedElements = new Vector[getLength()];

        for (int i = 0; i < transposedElements.length; i++) {
            transposedElements[i] = getVectorByIndexFromColumn(i);
        }

        vectors = Arrays.copyOf(transposedElements, transposedElements.length);
    }

    public void add(Matrix matrix) {
        if (getLength() < matrix.getLength()) {
            throw new IndexOutOfBoundsException("Длина матрицы = " + matrix.getLength() + ". Длина матрицы должна быть <= " + getLength());
        }

        if (getHeight() < matrix.getHeight()) {
            throw new IndexOutOfBoundsException("Высота матрицы = " + matrix.getHeight() + ". Высота матрицы должна быть <= " + getHeight());
        }

        for (int i = 0; i < vectors.length; i++) {
            for (int j = 0; j < vectors[i].getSize(); j++) {
                if (i < matrix.vectors.length) {
                    if (j < matrix.vectors[i].getSize()) {
                        vectors[i].setByIndex(j, vectors[i].getByIndex(j) + matrix.vectors[i].getByIndex(j));
                    } else {
                        vectors[i].setByIndex(j, vectors[i].getByIndex(j));
                    }
                } else {
                    vectors[i].setByIndex(j, vectors[i].getByIndex(j));
                }
            }
        }
    }

    public void subtract(Matrix matrix) {
        if (getLength() < matrix.getLength()) {
            throw new IndexOutOfBoundsException("Длина матрицы = " + matrix.getLength() + ". Длина матрицы должна быть <= " + getLength());
        }

        if (getHeight() < matrix.getHeight()) {
            throw new IndexOutOfBoundsException("Высота матрицы = " + matrix.getHeight() + ". Высота матрицы должна быть <= " + getHeight());
        }

        for (int i = 0; i < vectors.length; i++) {
            for (int j = 0; j < vectors[i].getSize(); j++) {
                if (i < matrix.vectors.length) {
                    if (j < matrix.vectors[i].getSize()) {
                        vectors[i].setByIndex(j, vectors[i].getByIndex(j) - matrix.vectors[i].getByIndex(j));
                    } else {
                        vectors[i].setByIndex(j, vectors[i].getByIndex(j));
                    }
                } else {
                    vectors[i].setByIndex(j, vectors[i].getByIndex(j));
                }
            }
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        Matrix sum = new Matrix(matrix1);
        sum.add(matrix2);

        return sum;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        Matrix difference = new Matrix(matrix1);
        difference.subtract(matrix2);

        return difference;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getLength() < matrix2.getLength()) {
            throw new IndexOutOfBoundsException("Длина матрицы = " + matrix2.getLength() + ". Длина матрицы должна быть <= " + matrix1.getLength());
        }

        if (matrix1.getHeight() < matrix2.getHeight()) {
            throw new IndexOutOfBoundsException("Высота матрицы = " + matrix2.getHeight() + ". Высота матрицы должна быть <= " + matrix1.getHeight());
        }

        Matrix product = new Matrix(matrix1);

        for (int i = 0; i < product.vectors.length; i++) {
            for (int j = 0; j < product.vectors[i].getSize(); j++) {
                if (i < matrix2.vectors.length) {
                    if (j < matrix2.vectors[i].getSize()) {
                        product.vectors[i].setByIndex(j, product.vectors[i].getByIndex(j) * matrix2.vectors[i].getByIndex(j));
                    } else {
                        product.vectors[i].setByIndex(j, 0);
                    }
                } else {
                    product.vectors[i].setByIndex(j, 0);
                }
            }
        }

        return product;
    }

    private static int getMaxVectorLengthFromArray(double[][] array) {
        int[] vectorsLengths = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                vectorsLengths[i]++;
            }
        }

        int maxVectorLength = 0;

        for (int vectorsLength : vectorsLengths) {
            if (vectorsLength > maxVectorLength) {
                maxVectorLength = vectorsLength;
            }
        }

        return maxVectorLength;
    }

    private static int getMaxVectorLengthFromVectors(Vector[] vectors) {
        int[] vectorsLengths = new int[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            vectorsLengths[i] = vectors[i].getSize();
        }

        int maxVectorLength = 0;

        for (int vectorsLength : vectorsLengths) {
            if (vectorsLength > maxVectorLength) {
                maxVectorLength = vectorsLength;
            }
        }

        return maxVectorLength;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (Vector vector : vectors) {
            stringBuilder.append(vector.toString());
            stringBuilder.append(",");
            stringBuilder.append(System.lineSeparator());
        }

        stringBuilder.setLength(stringBuilder.length() - 3);

        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}