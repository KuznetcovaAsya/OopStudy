package ru.oopstudy.kuznetcova.main;

import ru.oopstudy.kuznetcova.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите значения интервала. От: ");
        double from = scanner.nextDouble();

        System.out.println("До: ");
        double to = scanner.nextDouble();

        Range baseRange = new Range(from, to);

        baseRange.print();

        baseRange.setFrom(1);
        baseRange.setTo(50);

        double changedFrom = baseRange.getFrom();
        System.out.println("\nНовое значение \"от\": " + changedFrom);

        double changedTo = baseRange.getTo();
        System.out.println("Новое значение \"до\": " + changedTo);

        System.out.printf("Длина диапазона: %5.2f%n", baseRange.getLength());

        System.out.println("Введите число: ");
        double number = scanner.nextDouble();

        if (baseRange.isInside(number)) {
            System.out.println("Число " + number + " входит в диапазон");
        } else {
            System.out.println("Число " + number + " не входит в диапазон");
        }

        System.out.println("Введите значения нового интервала. От: ");
        double fromAnother = scanner.nextDouble();

        System.out.println("До: ");
        double toAnother = scanner.nextDouble();

        Range anotherRange = new Range(fromAnother, toAnother);

        if (baseRange.getIntersection(anotherRange) == null) {
            System.out.println("Пересечения интервалов нет");
        } else {
            System.out.print("Интервал пересечения: ");
            baseRange.getIntersection(anotherRange).print();
        }

        System.out.print("Интервал объединения: ");
        baseRange.printArray(baseRange.getMerging(anotherRange));

        if (baseRange.getRemainder(anotherRange) == null) {
            System.out.println("Разности интервалов нет");
        } else {
            System.out.print("\nИнтервал разности: ");
            baseRange.printArray(baseRange.getRemainder(anotherRange));
        }
    }
}