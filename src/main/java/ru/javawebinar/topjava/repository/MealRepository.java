package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepository {
    boolean delete(int id);
    void save(Meal meal);
    List<Meal> getAll();
    Meal getById(int id);
}
