package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final Meal userMeal3 = new Meal(100003, LocalDateTime.of(2024, 2, 17, 8, 41,28), "юзер завтрак", 700);
    public static final Meal adminMeal6 = new Meal(100006, LocalDateTime.of(2024, 2, 17, 7, 41,28), "админ завтрак", 600);

    public static List<Meal> meals = new ArrayList<>();

    static {
        meals.add(userMeal3);
        meals.add(adminMeal6);
    }

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2024, 2, 17, 17, 0,0), "новая еда", 555);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("user_id").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparatorIgnoringFields("user_id").isEqualTo(expected);
    }
}
