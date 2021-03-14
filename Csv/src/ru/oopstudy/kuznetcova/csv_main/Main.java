package ru.oopstudy.kuznetcova.csv_main;

import java.io.FileNotFoundException;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileInputStream("file.csv"));
             PrintWriter writer = new PrintWriter("result.html")) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("<!DOCTYPE html>");
            stringBuilder.append("<html>");
            stringBuilder.append("<head>");
            stringBuilder.append("<meta charset=\"utf-8\">");
            stringBuilder.append("</head>");
            stringBuilder.append("<table border=\"1\">");

            while (scanner.hasNext()) {
                stringBuilder.append("<tr>");
                String list = scanner.nextLine();
                String[] splitList = list.split(",");

                for (String s : splitList) {
                    stringBuilder.append("<td>");
                    stringBuilder.append(s);
                    stringBuilder.append("</td>");
                }

                stringBuilder.append("</tr>");
            }

            stringBuilder.append("</table>");
            stringBuilder.append("</html>");
            writer.println(stringBuilder);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
    }
}