package ru.oopstudy.kuznetcova.shapes;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getX3() {
        return x3;
    }

    public double getY3() {
        return y3;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public void setY3(double y3) {
        this.y3 = y3;
    }

    private double getMax(double... coordinates) {
        double max = coordinates[0];
        for (int i = 0; i < coordinates.length - 1; i++) {
            if (max < coordinates[i + 1]) {
                max = coordinates[i + 1];
            }
        }

        return max;
    }

    private double getMin(double... coordinates) {
        double min = coordinates[0];
        for (int i = 0; i < coordinates.length - 1; i++) {
            if (min > coordinates[i + 1]) {
                min = coordinates[i + 1];
            }
        }

        return min;
    }

    public double[] getSidesLengths() {
        double[] sidesLengths = new double[3];
        sidesLengths[0] = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        sidesLengths[1] = Math.sqrt(Math.pow((x3 - x1), 2) + Math.pow((y3 - y1), 2));
        sidesLengths[2] = Math.sqrt(Math.pow((x3 - x2), 2) + Math.pow((y3 - y2), 2));
        return sidesLengths;
    }

    @Override
    public double getWidth() {
        return getMax(x1, x2, x3) - getMin(x1, x2, x3);
    }

    @Override
    public double getHeight() {
        return getMax(y1, y2, y3) - getMin(y1, y2, y3);
    }

    @Override
    public double getArea() {
        double triangleSemiPerimeter = getPerimeter() / 2;
        return Math.sqrt(triangleSemiPerimeter * (triangleSemiPerimeter - getSidesLengths()[0]) *
                (triangleSemiPerimeter - getSidesLengths()[1]) *
                (triangleSemiPerimeter - getSidesLengths()[2]));
    }

    @Override
    public double getPerimeter() {
        double[] sideLengths = getSidesLengths();
        return sideLengths[0] + sideLengths[1] + sideLengths[2];
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;

        Triangle triangle = (Triangle) obj;
        double[] sides = getSidesLengths();
        double[] anotherSides = triangle.getSidesLengths();

        for (int i = 0; i < sides.length; i++) {
            if (sides[i] != anotherSides[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);
        return hash;
    }

    @Override
    public String toString() {
        return "Треугольник с координатами (" + x1 + ", " + y1 + "), " +
                "(" + x2 + ", " + y2 + "), " +
                "(" + x3 + ", " + y3 + ") ";
    }
}