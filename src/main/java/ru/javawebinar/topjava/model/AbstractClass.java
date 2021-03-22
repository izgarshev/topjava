package ru.javawebinar.topjava.model;

import java.io.Serializable;

public abstract class AbstractClass implements Serializable {
    protected final Long id;

    public Long getId() {
        return id;
    }

    public AbstractClass(Long id) {
        this.id = id;
    }
}
