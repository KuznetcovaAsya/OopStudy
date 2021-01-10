package ru.oopstudy.kuznetcova.person_main;

import ru.oopstudy.kuznetcova.person.Person;

import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Сергей", 35),
                new Person("Андрей", 32),
                new Person("Ольга", 20),
                new Person("Александр", 50),
                new Person("Александр", 19),
                new Person("Елена", 15),
                new Person("Илья", 19),
                new Person("Ольга", 18),
                new Person("Илья", 44),
                new Person("Елена", 21),
                new Person("Ольга", 13),
                new Person("Андрей", 32),
                new Person("Юлия", 20),
                new Person("Кристина", 25)
        );

        printUniqueNames(persons);
        printAverageAgeOfMinors(persons);
        printAverageAgeByName(persons);
        filterAgeAndPrintOldToYoung(persons);
    }

    public static void printUniqueNames(List<Person> persons) {
        String uniqueNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println(uniqueNames);
    }

    public static void printAverageAgeOfMinors(List<Person> persons) {
        final int legalAge = 18;

        OptionalDouble average = persons.stream()
                .mapToInt(Person::getAge)
                .filter(x -> x < legalAge)
                .average();

        if (average.isPresent()) {
            System.out.println("Средний возраст людей младше 18: " + average.getAsDouble());
        } else {
            System.out.println("Нет людей младше 18");
        }
    }

    public static void printAverageAgeByName(List<Person> persons) {
        Map<String, Double> averageAgeByName = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        System.out.println("Имена и средний возраст: " + averageAgeByName);
    }

    public static void filterAgeAndPrintOldToYoung(List<Person> persons) {
        System.out.println("Люди в возрасте от 20 до 45 лет в порядке убывания возраста: ");
        persons.stream()
                .filter(x -> x.getAge() >= 20 && x.getAge() <= 45)
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .forEach(x -> System.out.println(x.getName()));
    }
}