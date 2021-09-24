package com.client;

import com.client.Client;
import com.client.ClientType;

import java.util.Date;

public class Holding implements Client {
    private final String name;
    private final Date date;
    private final Integer zip;

    public Holding(String name, Date date, Integer zip) {
        this.name = name;
        this.date = date;
        this.zip = zip;
    }

    @Override
    public ClientType getType() {
        return ClientType.HOLDING;
    }

    @Override
    public String getInfo() {
        return "name: " + name + "\ndate: " + date.toString() + "\nzip: " + zip;
    }
}
