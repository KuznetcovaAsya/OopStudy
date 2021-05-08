package ru.oopstudy.kuznetcova.matrix;

import ru.oopstudy.kuznetcova.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (columnsCount <= 0 || rowsCount <= 0) {
            throw new IllegalArgumentException("Количество столбцов = " + columnsCount + " , количество строк = " + rowsCount + ". Размеры матрицы должны быть > 0");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Количество строк = " + array.length + ". Размеры матрицы должны быть > 0");
        }

        int columnsCount = getMaxRowLength(array);

        if (columnsCount == 0) {
            throw new IllegalArgumentException("Количество столбцов = " + columnsCount + ". Размеры матрицы должны быть > 0");
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                rows[i] = new Vector(columnsCount, array[i]);
            } else {
                rows[i] = new Vector(columnsCount);
            }
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.rows);
    }

    public Matrix(Vector[] vectors) {
        if (vectors == null) {
            throw new IllegalArgumentException("Массив векторов не должен быть null");
        }

        if (vectors.length == 0) {
            throw new IllegalArgumentException("Количество строк = " + vectors.length + ". Размеры матрицы должны быть > 0");
        }

        int columnsCount = getMaxVectorSize(vectors);

        if (columnsCount == 0) {
            throw new IllegalArgumentException("Количество столбцов = " + columnsCount + ". Размеры матрицы должны быть > 0");
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(columnsCount);
            if (vectors[i] != null) {
                rows[i].add(vectors[i]);
            }
        }
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public int getRowsCount() {
        return rows.length;
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ". Индекс должен быть >= 0 и < " + rows.length);
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Размер вектора: " + vector.getSize() + ": Размер вектора должен быть = " + getColumnsCount());
        }

        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Индекс = " + index + ": Индекс должен быть >= 0 и < " + rows.length);
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ". Индекс должен быть >= 0 и < " + getColumnsCount());
        }

        double[] array = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            array[i] = rows[i].getByIndex(index);
        }

        return new Vector(array);
    }

    public void multiplyByScalar(double number) {
        for (Vector vector : rows) {
            vector.multiplyByScalar(number);
        }
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Длина вектора: " + vector.getSize() + ": Длина вектора должна быть = " + getColumnsCount());
        }

        Vector product = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            product.setByIndex(i, Vector.getDotProduct(rows[i], vector));
        }

        return product;
    }

    public void transpose() {
        Vector[] transposedRows = new Vector[getColumnsCount()];

        for (int i = 0; i < transposedRows.length; i++) {
            transposedRows[i] = getColumn(i);
        }

        rows = transposedRows;
    }

    public void add(Matrix matrix) {
        checkSizes(matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkSizes(matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        matrix1.checkSizes(matrix2);

        Matrix sum = new Matrix(matrix1);
        sum.add(matrix2);

        return sum;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        matrix1.checkSizes(matrix2);

        Matrix difference = new Matrix(matrix1);
        difference.subtract(matrix2);

        return difference;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.rows.length) {
            throw new IllegalArgumentException("Количесво столбцов первой матрицы = " + matrix1.getColumnsCount() +
                    ". Количесво строк второй матрицы = " + matrix2.rows.length + ". Количество столбцов первой матрицы и количество строк ворой матрицы должны быть равны");
        }

        Matrix product = new Matrix(matrix1.rows.length, matrix2.getColumnsCount());

        for (int i = 0; i < matrix1.rows.length; i++) {
            Vector vector = new Vector(matrix2.getColumnsCount());

            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                double sum = 0;

                for (int k = 0; k < matrix2.rows.length; k++) {
                    sum += matrix1.rows[i].getByIndex(k) * matrix2.rows[k].getByIndex(j);
                }

                vector.setByIndex(j, sum);
            }

            product.rows[i] = vector;
        }

        return product;
    }

    public double getDeterminant() {
        if (rows.length != getColumnsCount()) {
            throw new UnsupportedOperationException("Строк: " + rows.length + ". Столбцов: " + getColumnsCount() + ". Детерминант можно вычислить только для квадратной матрицы");
        }

        double[][] matrixArray = new double[rows.length][getColumnsCount()];

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[i].getSize(); j++) {
                matrixArray[i][j] = rows[i].getByIndex(j);
            }
        }

        return calculateDeterminant(matrixArray);
    }

    private static double calculateDeterminant(double[][] matrixArray) {
        if (matrixArray.length == 1) {
            return matrixArray[0][0];
        }

        if (matrixArray.length == 2) {
            return matrixArray[0][0] * matrixArray[1][1] - matrixArray[1][0] * matrixArray[0][1];
        }

        double determinant = 0;

        for (int i = 0; i < matrixArray.length; i++) {
            int coefficient;

            if (i % 2 == 1) {  // Раскладываем всегда по нулевой строке, проверяем на четность значение i+0.
                coefficient = -1;
            } else {
                coefficient = 1;
            }

            determinant += coefficient * matrixArray[0][i] * calculateDeterminant(getMinor(matrixArray, 0, i));
        }

        return determinant;
    }

    // На входе матрица, из которой надо достать минор и номера строк-столбцов, к-е надо вычеркнуть.
    private static double[][] getMinor(double[][] elements, int rowIndex, int columnIndex) {
        int minorSize = elements.length - 1;
        double[][] minor = new double[minorSize][minorSize];

        int skipRow = 0; // Чтобы "пропускать" ненужные нам строку и столбец

        for (int i = 0; i <= minorSize; i++) {
            int skipColumn = 0;

            for (int j = 0; j <= minorSize; j++) {
                if (i == rowIndex) {
                    skipRow = 1;
                } else {
                    if (j == columnIndex) {
                        skipColumn = 1;
                    } else {
                        minor[i - skipRow][j - skipColumn] = elements[i][j];
                    }
                }
            }
        }

        return minor;
    }

    private static int getMaxRowLength(double[][] array) {
        int maxRowLength = 0;
        boolean isAllRowsNull = true;

        for (double[] doubles : array) {
            if (doubles != null && doubles.length > maxRowLength) {
                maxRowLength = doubles.length;
                isAllRowsNull = false;
            }
        }

        if (isAllRowsNull) {
            throw new IllegalArgumentException("Все строки null");
        }

        return maxRowLength;
    }

    private static int getMaxVectorSize(Vector[] vectors) {
        int maxVectorSize = 0;
        boolean isAllVectorsNull = true;

        for (Vector vector : vectors) {
            if (vector != null && vector.getSize() > maxVectorSize) {
                maxVectorSize = vector.getSize();
                isAllVectorsNull = false;
            }
        }

        if (isAllVectorsNull) {
            throw new IllegalArgumentException("Все строки null");
        }

        return maxVectorSize;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (int i = 0; i < rows.length; i++) {
            stringBuilder.append(rows[i]);

            if (i < rows.length - 1) {
                stringBuilder.append(",");
                stringBuilder.append(System.lineSeparator());
            }
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    private void checkSizes(Matrix matrix) {
        if (getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Количество столбцов матрицы = " + matrix.getColumnsCount() + ". Количесво столбцов матрицы должно быть = " + getColumnsCount());
        }

        if (rows.length != matrix.rows.length) {
            throw new IllegalArgumentException("Количество строк матрицы = " + matrix.rows.length + ". Количество строк матрицы должно быть = " + rows.length);
        }
    }
}