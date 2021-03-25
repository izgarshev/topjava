package ru.javawebinar.topjava.model;

import java.io.Serializable;

public abstract class AbstractClass implements Serializable {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AbstractClass(Long id) {
        this.id = id;
    }
}
