package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    // null if updated meal do not belong to userId
    public Meal save(Meal meal) {
        return repository.save(meal);
    }

    // false if meal do not belong to userId
    public boolean delete(int id) {
        return repository.delete(id);
    }

    // null if meal do not belong to userId
    public Meal get(int id) {
        Meal meal = repository.get(id);
        if (meal == null) {
            throw new NotFoundException("");
        }
        return meal;
    }

    // ORDERED dateTime desc
    public Collection<Meal> getAll() {
        return repository.getAll();
    }
}