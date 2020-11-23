package ru.oopstudy.kuznetcova.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(2, 3, 7, 7, 4, 6, 8, 8, 11, 16, 10));
        removeEvenNumbers(numbers);
        System.out.println("Список без четных элементов: " + numbers);
        System.out.println("Список без повторяющихся элементов: " + getListWithoutDuplicates(numbers));

        try (Scanner scanner = new Scanner(new FileInputStream("Input.txt"))) {
            ArrayList<String> list = new ArrayList<>();

            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }

            System.out.println("Список строк: " + list);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
    }

    public static void removeEvenNumbers(ArrayList<Integer> numbers) {
        int i = 0;

        while (i < numbers.size()) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
            } else {
                i++;
            }
        }
    }

    public static ArrayList<Integer> getListWithoutDuplicates(ArrayList<Integer> numbers) {
        ArrayList<Integer> numbersWithoutDuplicates = new ArrayList<>(numbers.size());

        for (Integer number : numbers) {
            if (!numbersWithoutDuplicates.contains(number)) {
                numbersWithoutDuplicates.add(number);
            }
        }

        return numbersWithoutDuplicates;
    }
}