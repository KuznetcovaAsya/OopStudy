package ru.oopstudy.kuznetcova.range;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(1,10);

        System.out.println(Arrays.toString(range.getIntersectionRange(1,7)));



        range.print();

        range.setFrom(-100);
        range.setTo(-25);

        double newFrom = range.getFrom();
        System.out.println("Новое значение \"от\": " + newFrom);

        double newTo = range.getTo();
        System.out.println("Новое значение \"до\": " + newTo);

        System.out.printf("Длина диапазона: %5.2f%n", range.getLength());

        double number = 500;

        if (range.isInside(number)) {
            System.out.println("Число " + number + " входит в диапазон");
        } else {
            System.out.println("Число " + number + " не входит в диапазон");
        }


    }
}
