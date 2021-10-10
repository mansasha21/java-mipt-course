package ru.sber;

public class Person {
    private Integer age;
    private Boolean alive;
    private String name;

    public Boolean isAlive() {
        return alive;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Person(Integer age, Boolean alive, String name) {
        this.age = age;
        this.alive = alive;
        this.name = name;
    }
}