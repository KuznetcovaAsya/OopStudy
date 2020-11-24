package ru.oopstudy.kuznetcova.shapes;

public class Square implements Shape {
    private double length;

    public Square(double length) {
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public double getWidth() {
        return length;
    }

    @Override
    public double getHeight() {
        return length;
    }

    @Override
    public double getArea() {
        return length * length;
    }

    @Override
    public double getPerimeter() {
        return length * 4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Square square = (Square) obj;

        return length == square.length;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(length);

        return hash;
    }

    @Override
    public String toString() {
        return "Квадрат со стороной " + length + ". Периметр " + getPerimeter() +
                ". Площадь " + getArea();
    }
}