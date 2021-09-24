package com.client;

public class LegalEntity implements Client {
    private final Integer inn;
    private final String name;


    public LegalEntity(String name, Integer inn) {
        this.inn = inn;
        this.name = name;
    }

    @Override
    public ClientType getType() {
        return ClientType.LEGAL_ENTITY;
    }

    @Override
    public String getInfo() {
        return "name: " + name + "\ninn: " + inn;
    }
}
