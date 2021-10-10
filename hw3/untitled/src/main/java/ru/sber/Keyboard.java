package ru.sber;

public class Keyboard {
    private final Integer[] keys;
    private final Integer id;

    public Integer[] getKeys() {
        return keys;
    }

    public Integer getId() {
        return id;
    }

    public Keyboard(Integer[] keys, Integer id) {
        this.keys = keys;
        this.id = id;
    }
}
