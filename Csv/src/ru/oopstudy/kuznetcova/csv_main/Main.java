package ru.oopstudy.kuznetcova.csv_main;

import java.io.FileNotFoundException;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("В программу необходимо передать два аргумента: путь к файлу-источнику и путь к файлу для записи");
            return;
        }

        try (Scanner scanner = new Scanner(args[0]);
             PrintWriter writer = new PrintWriter(args[1])) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<meta charset=\"utf-8\">");
            writer.println("<title>Перевод CSV в HTML</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<table border=\"1\">");
            writer.print("<tr><td>");

            boolean isTextInTableDetail = false;
            boolean isNewRow = false;

            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();

                if (s.isEmpty()) {
                    continue;
                }

                if (isNewRow && !isTextInTableDetail) {
                    writer.print("<tr><td>");
                }

                char quotes = '"';
                char comma = ',';
                int i = 0;

                if (s.charAt(0) == quotes) {
                    isTextInTableDetail = true;
                    i = 1;
                }

                for (; i < s.length(); i++) {
                    char c = s.charAt(i);

                    if (i == 0 && s.charAt(0) == comma) {
                        writer.print("</td> <td>");
                        isTextInTableDetail = false;
                        continue;
                    } else if (c == quotes && s.charAt(i - 1) == comma) {
                        isTextInTableDetail = true;
                        continue;
                    } else if (c == comma && s.charAt(i - 1) == quotes) {
                        isTextInTableDetail = false;
                    } else if (c == quotes && isTextInTableDetail) {
                        isTextInTableDetail = false;
                        continue;
                    } else if (c == quotes && s.charAt(i - 1) == quotes) {
                        isTextInTableDetail = true;
                    }

                    if (c == '<') {
                        writer.print("&lt;");
                    } else if (c == '>') {
                        writer.print("&gt;");
                    } else if (c == '&') {
                        writer.print("&amp;");
                    } else if (c == comma && s.charAt(i - 1) == quotes
                            && s.charAt(i - 2) == quotes
                            && s.charAt(i - 3) != quotes) {
                        writer.print(c);
                    } else if (c == comma) {
                        writer.print("</td> <td>");
                    } else {
                        writer.print(c);
                    }
                }

                if (isTextInTableDetail) {
                    writer.print("<br/>");
                } else {
                    writer.print("</td></tr>");
                    isNewRow = true;
                }
            }

            writer.println("");
            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
    }
}