package ru.oopstudy.kuznetcova.person;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        if (name == null) {
            throw new IllegalArgumentException("Имя не может быть null");
        }

        if (age <= 0) {
            throw new IllegalArgumentException(age + ". Возраст должен быть > 0");
        }

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Имя не может быть null");
        }

        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException(age + " Возраст должен быть > 0");
        }

        this.age = age;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + name.hashCode();
        hash = prime * hash + age;

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Person person = (Person) obj;

        return name.equals(person.name) && age == person.age;
    }

    @Override
    public String toString() {
        return "Имя: " + name + ". Возраст: " + age;
    }
}