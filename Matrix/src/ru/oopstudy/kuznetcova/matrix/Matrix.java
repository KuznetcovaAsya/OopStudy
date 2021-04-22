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
            throw new NullPointerException("Массив не может быть null");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Количество строк = " + array.length + ". Размеры матрицы должны быть > 0");
        }

        int columnsCount = getMaxRowLength(array);

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(columnsCount, array[i]);
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.rows);
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Количество строк = " + vectors.length + ". Размеры матрицы должны быть > 0");
        }

        int columnsCount = getMaxVectorSize(vectors);

        rows = new Vector[vectors.length];

        for (int i = 0; i < this.rows.length; i++) {
            rows[i] = new Vector(columnsCount);
            rows[i].add(vectors[i]);
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
        if (vector.getSize() != rows[0].getSize()) {
            throw new IllegalArgumentException("Размер вектора: " + vector.getSize() + ": Размер вектора должен быть = " + rows[0].getSize());
        }

        if (index >= rows.length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс = " + index + ": Индекс должен быть >= 0 и < " + rows.length);
        }

        int vectorSize = vector.getSize();

        for (Vector value : rows) {
            for (int j = 0; j < value.getSize(); j++) {
                if (j < vectorSize) {
                    rows[index].setByIndex(j, vector.getByIndex(j));
                } else {
                    rows[index].setByIndex(j, 0);
                }
            }
        }
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= rows[0].getSize()) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ". Индекс должен быть >= 0 и < " + rows[0].getSize());
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
        if (vector.getSize() != rows[0].getSize()) {
            throw new IndexOutOfBoundsException("Длина вектора: " + vector.getSize() + ": Длина вектора должна быть = " + rows[0].getSize());
        }

        Vector multiplication = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            double sum = 0;

            for (int j = 0; j < vector.getSize(); j++) {
                sum += rows[i].getByIndex(j) * vector.getByIndex(j);
            }

            multiplication.setByIndex(i, sum);
        }

        return multiplication;
    }

    public void transpose() {
        Vector[] transposedElements = new Vector[rows[0].getSize()];

        for (int i = 0; i < transposedElements.length; i++) {
            transposedElements[i] = getColumn(i);
        }

        rows = transposedElements;
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
        if (matrix1.rows[0].getSize() != matrix2.rows.length) {
            throw new IndexOutOfBoundsException("Количесво столбцов первой матрицы = " + matrix1.rows[0].getSize() +
                    ". Количесво строк второй матрицы = " + matrix2.rows.length + ". Количество столбцов первой матрицы и количество строк ворой матрицы должны быть равны");
        }

        Matrix product = new Matrix(matrix1.rows.length, matrix2.rows[0].getSize());

        for (int i = 0; i < matrix1.rows.length; i++) {
            Vector vector = new Vector(matrix2.rows[0].getSize());

            for (int j = 0; j < matrix2.rows[0].getSize(); j++) {
                int sum = 0;

                for (int k = 0; k < matrix2.rows.length; k++) {
                    sum += matrix1.rows[i].getByIndex(k) * matrix2.rows[k].getByIndex(j);
                }

                vector.setByIndex(j, sum);
            }

            product.setRow(i, vector);
        }

        return product;
    }

    public double getDeterminant() {
        if (rows.length != rows[0].getSize()) {
            throw new UnsupportedOperationException("Строк: " + rows.length + ". Столбцов: " + rows[0].getSize() + ". Детерминант можно вычислить только для квадратной матрицы");
        }

        double[][] matrixArray = new double[rows.length][rows[0].getSize()];

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[i].getSize(); j++) {
                matrixArray[i][j] = rows[i].getByIndex(j);
            }
        }

        return calculateDeterminant(matrixArray);
    }

    private static double calculateDeterminant(double[][] matrixArray) {
        double determinant = 0;

        if (matrixArray.length == 1) {
            determinant = matrixArray[0][0];
            return determinant;
        }

        if (matrixArray.length == 2) {
            determinant = matrixArray[0][0] * matrixArray[1][1] - matrixArray[1][0] * matrixArray[0][1];
            return determinant;
        }

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

        for (double[] doubles : array) {
            if (doubles.length > maxRowLength) {
                maxRowLength = doubles.length;
            }
        }

        return maxRowLength;
    }

    private static int getMaxVectorSize(Vector[] vectors) {
        int maxVectorSize = 0;

        for (Vector vector : vectors) {
            if (vector.getSize() > maxVectorSize) {
                maxVectorSize = vector.getSize();
            }
        }

        return maxVectorSize;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (Vector vector : rows) {
            stringBuilder.append(vector);
            stringBuilder.append(",");
            stringBuilder.append(System.lineSeparator());
        }

        stringBuilder.setLength(stringBuilder.length() - 3);

        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    private void checkSizes(Matrix matrix) {
        if (rows[0].getSize() != matrix.rows[0].getSize()) {
            throw new IllegalArgumentException("Количесво столбцов матрицы = " + matrix.rows[0].getSize() + ". Количесво столбцов матрицы должно быть = " + rows[0].getSize());
        }

        if (rows.length != matrix.rows.length) {
            throw new IllegalArgumentException("Количество строк матрицы = " + matrix.rows.length + ". Количество строк матрицы должно быть = " + rows.length);
        }
    }
}