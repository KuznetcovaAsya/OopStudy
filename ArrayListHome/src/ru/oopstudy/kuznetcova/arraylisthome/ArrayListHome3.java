package ru.oopstudy.kuznetcova.arraylisthome;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome3 {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 4, 5, 5, 5, 6, 7));
        ArrayList<Integer> numbersWithoutDuplicates = new ArrayList<>();

        for (Integer number : numbers) {
            if (!numbersWithoutDuplicates.contains(number)) {
                numbersWithoutDuplicates.add(number);
            }
        }

        System.out.println(numbersWithoutDuplicates);
    }
}