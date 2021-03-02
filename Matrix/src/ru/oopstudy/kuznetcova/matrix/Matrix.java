package ru.oopstudy.kuznetcova.matrix;

import java.util.Arrays;

public class Matrix {
    private double[][] elements;

    public Matrix(int length, int height) {
        if (length <= 0 || height <= 0) {
            throw new IllegalArgumentException("Длина = " + length + " , высота = " + height + ". Размеры матрицы должны быть > 0");
        }

        elements = new double[height][length];
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new NullPointerException("Массив не может быть null");
        }

        int matrixLength = getMaxVectorLengthFromArray(array);

        elements = new double[array.length][matrixLength];

        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, elements[i], 0, array[i].length);
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.elements);
    }

    public Matrix(Vector[] vectors) {
        int matrixLength = getMaxVectorLengthFromVectors(vectors);

        elements = new double[vectors.length][matrixLength];

        for (int i = 0; i < vectors.length; i++) {
            for (int j = 0; j < vectors[i].getSize(); j++) {
                elements[i][j] = vectors[i].getByIndex(j);
            }
        }
    }

    public int getLength() {
        return elements[0].length;
    }

    public int getHeight() {
        return elements.length;
    }

    public Vector getVectorByIndexFromLine(int index) {
        if (index < 0 || index >= getHeight()) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ". Индекс должен быть >= 0 и < " + getHeight());
        }

        return new Vector(elements[index]);
    }

    public void setVectorByIndex(int index, Vector vector) {
        if (vector.getSize() > getLength()) {
            throw new IndexOutOfBoundsException("Длина вектора: " + vector.getSize() + ": Длина вектора должна быть <= " + getLength());
        }

        if (index >= getHeight()) {
            throw new ArrayIndexOutOfBoundsException("Индекс = " + index + ": Индекс должен быть < " + getHeight());
        }

        int vectorLength = vector.getSize();

        for (int i = 0; i < elements[index].length; i++) {
            if (i < vectorLength) {
                elements[index][i] = vector.getByIndex(i);
            } else {
                elements[index][i] = 0;
            }
        }
    }

    public Vector getVectorByIndexFromColumn(int index) {
        if (index < 0 || index >= getLength()) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ". Индекс должен быть >= 0 и < " + getLength());
        }

        double[] vector = new double[getHeight()];

        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements[i].length; j++) {
                if (j == index) {
                    vector[i] = elements[i][j];
                }
            }
        }

        return new Vector(vector);
    }

    public void multiplyByScalar(double number) {
        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements[i].length; j++) {
                elements[i][j] *= number;
            }
        }
    }

    public void multiplyByVector(Vector vector) {
        if (vector.getSize() > getLength()) {
            throw new IndexOutOfBoundsException("Длина вектора: " + vector.getSize() + ": Длина вектора должна быть <= " + getLength());
        }

        int vectorLength = vector.getSize();

        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements[i].length; j++) {
                if (i < vectorLength) {
                    elements[i][j] *= vector.getByIndex(j);
                } else {
                    elements[i][j] = 0;
                }
            }
        }
    }

    public void transposed() {
        double[][] transposedElements = new double[getLength()][getHeight()];

        for (int i = 0; i < transposedElements.length; i++) {
            for (int j = 0; j < transposedElements[i].length; j++) {
                transposedElements[i][j] = elements[j][i];
            }
        }

        elements = Arrays.copyOf(transposedElements, transposedElements.length);
    }

    public void add(Matrix matrix) {
        if (getLength() < matrix.getLength()) {
            throw new IndexOutOfBoundsException("Длина матрицы = " + matrix.getLength() + ". Длина матрицы должна быть <= " + getLength());
        }

        if (getHeight() < matrix.getHeight()) {
            throw new IndexOutOfBoundsException("Высота матрицы = " + matrix.getHeight() + ". Высота матрицы должна быть <= " + getHeight());
        }

        for (int i = 0; i < matrix.elements.length; i++) {
            for (int j = 0; j < matrix.elements[i].length; j++) {
                elements[i][j] += matrix.elements[i][j];
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

        for (int i = 0; i < matrix.elements.length; i++) {
            for (int j = 0; j < matrix.elements[i].length; j++) {
                elements[i][j] -= matrix.elements[i][j];
            }
        }
    }

    public double getDeterminant() {
        double[][] elementsForCalculate = Arrays.copyOf(elements, elements.length);
        return calculateDeterminant(elementsForCalculate);
    }

    public double calculateDeterminant(double[][] elementsForCalculate) {
        double determinant = 0;

        if (elementsForCalculate.length == 2) {
            determinant = elementsForCalculate[0][0] * elementsForCalculate[1][1] - elementsForCalculate[1][0] * elementsForCalculate[0][1];
        } else {
            int coefficient;

            for (int i = 0; i < elementsForCalculate.length; i++) {
                if (i % 2 == 1) {  // Раскладываем всегда по нулевой строке, проверяем на четность значение i+0.
                    coefficient = -1;
                } else {
                    coefficient = 1;
                }

                determinant += coefficient * elementsForCalculate[0][i] * calculateDeterminant(getMinor(elementsForCalculate, 0, i));
            }
        }

        return determinant;
    }

    // На входе матрица, из которой надо достать минор и номера строк-столбцов, к-е надо вычеркнуть.
    private double[][] getMinor(double[][] elements, int row, int column) {
        int minorLength = elements.length - 1;
        double[][] minor = new double[minorLength][minorLength];

        int skipRow = 0; // Чтобы "пропускать" ненужные нам строку и столбец
        int skipColumn;

        for (int i = 0; i <= minorLength; i++) {
            skipColumn = 0;

            for (int j = 0; j <= minorLength; j++) {
                if (i == row) {
                    skipRow = 1;
                } else {
                    if (j == column) {
                        skipColumn = 1;
                    } else {
                        minor[i - skipRow][j - skipColumn] = elements[i][j];
                    }
                }
            }
        }

        return minor;
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
        int matrixLength = getMaxVectorLengthFromArray(matrix1.elements);
        double[][] matrix2Array = new double[matrix1.elements.length][matrixLength];

        for (int i = 0; i < matrix2.elements.length; i++) {
            System.arraycopy(matrix2.elements[i], 0, matrix2Array[i], 0, matrix2.elements[i].length);
        }

        for (int i = 0; i < matrix1.elements.length; i++) {
            for (int j = 0; j < matrix1.elements[i].length; j++) {
                product.elements[i][j] *= matrix2Array[i][j];
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

        for (int i = 0; i < elements.length; i++) {
            stringBuilder.append("{");

            for (int j = 0; j < elements[i].length; j++) {
                stringBuilder.append(elements[i][j]);

                if (j < elements[i].length - 1) {
                    stringBuilder.append(", ");
                }
            }

            stringBuilder.append("}");

            if (i < elements.length - 1) {
                stringBuilder.append(", ");
                stringBuilder.append(System.getProperty("line.separator"));
            }
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}