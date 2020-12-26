package ru.oopstudy.kuznetcova.person_main;

import ru.oopstudy.kuznetcova.person.Person;

import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("Сергей", 25),
                new Person("Андрей", 32),
                new Person("Ольга", 20),
                new Person("Александр", 50),
                new Person("Александр", 15),
                new Person("Елена", 18),
                new Person("Илья", 19),
                new Person("Ольга", 17),
                new Person("Илья", 44),
                new Person("Елена", 21),
                new Person("Ольга", 35),
                new Person("Андрей", 32),
                new Person("Юлия", 20),
                new Person("Кристина", 25)));

        printUniqueNames(persons);
        printAverage(persons);
        mapNameToAverageAge(persons);
        filterAgeAndPrintOldToYoung(persons);
    }

    public static void printUniqueNames(List<Person> persons) {
        Stream<Person> stream = persons.stream();

        String uniqueNames = stream
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", "));

        System.out.println("Имена: " + uniqueNames);
    }

    public static void printAverage(List<Person> persons) {
        Stream<Person> stream = persons.stream();

        OptionalDouble average = stream
                .mapToInt(Person::getAge)
                .filter(x -> x < 18)
                .average();

        if (average.isPresent()) {
            System.out.println("Средний возраст людей младше 18: " + average.getAsDouble());
        } else {
            System.out.println("Нет людей младше 18");
        }
    }

    public static void mapNameToAverageAge(List<Person> persons) {
        Map<String, Double> map = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        System.out.println("Имена и средний возраст: " + map);
    }

    public static void filterAgeAndPrintOldToYoung(List<Person> persons) {
        System.out.println("Люди в возрасте от 20 до 45 лет в порядке убывания возраста: ");
        persons.stream()
                .filter(x -> x.getAge() < 45 && x.getAge() > 20)
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .forEach(x -> System.out.println(x.getName()));
    }
}