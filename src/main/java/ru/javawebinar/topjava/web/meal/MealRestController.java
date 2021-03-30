package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

@Controller
public class MealRestController {
    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal save(Meal meal) {
        return service.save(meal);
    }

    // false if meal do not belong to userId
    public boolean delete(int id) {
        return service.delete(id);
    }

    // null if meal do not belong to userId
    public Meal get(int id) {
        Meal meal = service.get(id);
        if (meal == null) {
            throw new NotFoundException("");
        }
        return meal;
    }

    // ORDERED dateTime desc
    public Collection<Meal> getAll() {
        return service.getAll();
    }

}