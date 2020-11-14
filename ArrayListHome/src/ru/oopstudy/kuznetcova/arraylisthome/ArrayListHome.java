package ru.oopstudy.kuznetcova.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 3, 3, 5, 4, 8, 10));
        System.out.println(getListWithoutEvenNumbers(numbers));
        System.out.println(getListWithoutDuplicates(numbers));

        try (Scanner scanner = new Scanner(new FileInputStream("Input.txt"))) {
            ArrayList<String> list = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();
                list.add(string);
            }

            System.out.println(list);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
    }

    public static ArrayList<Integer> getListWithoutEvenNumbers(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(numbers.get(i));
                i--;
            }
        }

        return numbers;
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