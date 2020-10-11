package ru.oopstudy.kuznetcova.range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите значения интервала. От: ");
        double startNumberBaseRange = scanner.nextDouble();

        System.out.println("До: ");
        double endNumberBaseRange = scanner.nextDouble();

        Range baseRange = new Range(startNumberBaseRange, endNumberBaseRange);

        baseRange.print();

        baseRange.setFrom(1);
        baseRange.setTo(25);

        double newStartNumberBaseRange = baseRange.getFrom();
        System.out.println("Новое значение \"от\": " + newStartNumberBaseRange);

        double newEndNumberBaseRange = baseRange.getTo();
        System.out.println("Новое значение \"до\": " + newEndNumberBaseRange);

        System.out.printf("Длина диапазона: %5.2f%n", baseRange.getLength());

        System.out.println("Введите число: ");
        double number = scanner.nextDouble();

        if (baseRange.isInside(number)) {
            System.out.println("Число " + number + " входит в диапазон");
        } else {
            System.out.println("Число " + number + " не входит в диапазон");
        }

        System.out.println("Введите значения нового интервала. От: ");
        double startNumberAnotherRange = scanner.nextDouble();

        System.out.println("До: ");
        double endNumberAnotherRange = scanner.nextDouble();

        if (baseRange.intersection(startNumberAnotherRange, endNumberAnotherRange) == null) {
            System.out.println("Пересечения интервалов нет");
        } else {
            System.out.println("Интервал пересечения: " +
                    baseRange.intersection(startNumberAnotherRange, endNumberAnotherRange).getFrom() +
                    " " + baseRange.intersection(startNumberAnotherRange, endNumberAnotherRange).getTo());
        }
    }
}