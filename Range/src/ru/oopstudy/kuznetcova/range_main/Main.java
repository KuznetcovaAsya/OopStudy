package ru.oopstudy.kuznetcova.range_main;

import ru.oopstudy.kuznetcova.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите значения интервала. От: ");
        double from1 = scanner.nextDouble();

        System.out.println("До: ");
        double to1 = scanner.nextDouble();

        Range range1 = new Range(from1, to1);

        System.out.println(range1);

        range1.setFrom(1);
        range1.setTo(50);

        double changedFrom1 = range1.getFrom();
        System.out.println("Новое значение \"от\": " + changedFrom1);

        double changedTo1 = range1.getTo();
        System.out.println("Новое значение \"до\": " + changedTo1);

        System.out.printf("Длина диапазона: %5.2f%n", range1.getLength());

        System.out.println("Введите число: ");
        double number = scanner.nextDouble();

        if (range1.isInside(number)) {
            System.out.println("Число " + number + " входит в диапазон");
        } else {
            System.out.println("Число " + number + " не входит в диапазон");
        }

        System.out.println("Введите значения нового интервала. От: ");
        double from2 = scanner.nextDouble();

        System.out.println("До: ");
        double to2 = scanner.nextDouble();

        Range range2 = new Range(from2, to2);
        Range[] intersectionRange = range1.getIntersection(range2);

        if (intersectionRange == null) {
            System.out.print("Пересечения интервалов нет");
        } else {
            System.out.print("Интервал пересечения: ");
            Range.print(intersectionRange);
        }

        System.out.println();

        System.out.print("Интервал объединения: ");
        Range.print(range1.getUnion(range2));

        System.out.println();

        System.out.print("Интервал разности: ");
        Range.print(range1.getDifference(range2));
    }
}