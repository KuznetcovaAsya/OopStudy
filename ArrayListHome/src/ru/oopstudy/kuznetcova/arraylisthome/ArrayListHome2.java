package ru.oopstudy.kuznetcova.arraylisthome;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome2 {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 7, 9, 2, 4, 20, 26));

        for (int i = 0; i < numbers.size(); i++) {
            numbers.removeIf(n -> n % 2 == 0);
        }

        System.out.println(numbers);
    }
}