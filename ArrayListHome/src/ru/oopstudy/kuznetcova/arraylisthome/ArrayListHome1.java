package ru.oopstudy.kuznetcova.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListHome1 {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream("Input.txt"))) {
            ArrayList<String> list = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();
                list.add(string);
            }

            System.out.println(list);
        }
    }
}