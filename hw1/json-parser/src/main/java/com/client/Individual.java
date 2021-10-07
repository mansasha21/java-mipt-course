package com.client;

public class Individual implements Client {
    private final String name;
    private final String surname;
    private final Integer number;

    public Individual(String name, String surname, Integer number) {
        this.name = name;
        this.surname = surname;
        this.number = number;
    }

    public String getInfo() {
        return "name: " + name + "\nsurname: " + surname + "\nnumber: " + number;
    }

    @Override
    public ClientType getType() {
        return ClientType.INDIVIDUAL;
    }
}
