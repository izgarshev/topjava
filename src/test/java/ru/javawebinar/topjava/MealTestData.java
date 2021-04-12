package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ + 100;
    public static final Meal MEAL = new Meal(MEAL_ID, LocalDateTime.now(), "zavtrak", 300);

}
