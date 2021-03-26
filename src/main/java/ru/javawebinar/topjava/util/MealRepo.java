package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.util.List;

public interface MealRepo {
    List<UserMealWithExcess> getAll();
    void delete(Long userId);
    void saveOrUpdate(UserMeal meal);
    UserMeal getMealById(Long id);
}
