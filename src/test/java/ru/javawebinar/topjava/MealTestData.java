package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {
    public static final int USER_ID = 100000;
    public static final int ADMIN_ID = 100001;
    public static final int MEAL_START_ID = USER_ID + 3;
    public static final int NOT_FOUND = 10;

    public static final Meal userMeal1 = new Meal(MEAL_START_ID, LocalDateTime.of(2024, 9, 9, 20,12,51), "юзер еда 1", 999);
    public static final Meal userMeal2 = new Meal(MEAL_START_ID + 1, LocalDateTime.of(2024, 9, 9, 20,13,6), "юзер еда 2", 1000);
    public static final Meal adminMeal1 = new Meal(MEAL_START_ID + 2, LocalDateTime.of(2024, 9, 9, 20,13,8), "админ еда 1", 1001);

    public static List<Meal> meals = Arrays.asList(userMeal2, userMeal1);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2024,9,9,23,23,23), "новая юзер еда", 1010);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(userMeal1);
        updated.setDescription("обновленная еда юзера");
        updated.setCalories(333);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparatorIgnoringFields().isEqualTo(expected);
    }
}
