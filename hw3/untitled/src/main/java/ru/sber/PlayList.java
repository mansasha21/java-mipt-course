package ru.sber;

import java.util.Set;

public class PlayList {
    private final Set<String> data;
    private final Long userId;


    public Set<String> getData() {
        return data;
    }

    public Long getUserId() {
        return userId;
    }

    public PlayList(Set<String> data, Long userId) {
        this.data = data;
        this.userId = userId;
    }
}
