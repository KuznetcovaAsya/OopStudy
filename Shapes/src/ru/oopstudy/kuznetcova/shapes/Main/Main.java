package ru.oopstudy.kuznetcova.shapes.Main;

import ru.oopstudy.kuznetcova.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {new Square(5),
                new Triangle(0, 0, 3, 0, 3, 4),
                new Rectangle(5, 3),
                new Circle(3),
                new Square(3),
                new Circle(10),
                new Rectangle(30, 80),
                new Triangle(10, 11, 13, 10, 13, 14)};

        Shape maxAreaShape = getShapeWithMaxArea(shapes);
        System.out.println("Фигура с максимальной площадью: " + maxAreaShape);

        Shape secondMaxPerimeterShape = getShapeWithSecondMaxPerimeter(shapes);
        System.out.println("Фигура со вторым по величине периметром: " + secondMaxPerimeterShape);
    }

    public static Shape getShapeWithMaxArea(Shape[] shapes) {
        Arrays.sort(shapes, new AreaComparator());

        if (shapes.length > 0) {
            return shapes[shapes.length - 1];
        } else {
            return null;
        }
    }

    public static Shape getShapeWithSecondMaxPerimeter(Shape[] shapes) {
        Arrays.sort(shapes, new PerimeterComparator());
        int secondMaxIndex = shapes.length - 2;

        while (secondMaxIndex >= 0) {
            if (shapes[secondMaxIndex].getPerimeter() < shapes[secondMaxIndex + 1].getPerimeter()) {
                return shapes[secondMaxIndex];
            }

            secondMaxIndex--;
        }

        return null;
    }
}