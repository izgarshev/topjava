package ru.javawebinar.topjava.util;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;
import ru.javawebinar.topjava.web.MealServlet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class UserMealsUtil {
    private static final Logger log = getLogger(UserMealsUtil.class);

    public static List<UserMeal> MEAL_LIST = new ArrayList<>(Arrays.asList(
            new UserMeal(1L, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new UserMeal(2L, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new UserMeal(3L, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new UserMeal(4L, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new UserMeal(5L, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new UserMeal(6L, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new UserMeal(7L, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    ));
    public static final int CALORIES_PER_DAY = 2000;

    public static List<UserMealWithExcess> getAll() {
        Map<LocalDate, Integer> sumByDate = new HashMap<>();
        List<UserMeal> filteredList = MEAL_LIST.stream()
                .peek(userMeal -> sumByDate.merge(userMeal.getDateTime().toLocalDate(), userMeal.getCalories(), Integer::sum))
                .collect(Collectors.toList());
        System.out.println(sumByDate);
        return filteredList.stream().map(userMeal -> new UserMealWithExcess(userMeal.getId(), userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), sumByDate.get(userMeal.getDateTime().toLocalDate()) > CALORIES_PER_DAY)).collect(Collectors.toList());
    }

    public static void delete(Long userId) {
        log.warn(String.valueOf(userId));
        if (userId == null) return;
        UserMeal meal = MEAL_LIST.stream().filter(userMeal1 -> userMeal1.getId().equals(userId)).findFirst().get();
        MEAL_LIST.remove(meal);
    }

    public static List<UserMealWithExcess> filteredByStreams(LocalTime startTime, LocalTime endTime) {
        Map<LocalDate, Integer> sumByDate = new HashMap<>();
        List<UserMeal> filteredList = MEAL_LIST.stream()
                .peek(userMeal -> sumByDate.merge(userMeal.getDateTime().toLocalDate(), userMeal.getCalories(), Integer::sum))
                .filter(userMeal -> TimeUtil.isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                .collect(Collectors.toList());
        System.out.println(sumByDate);
        return filteredList.stream().map(userMeal -> new UserMealWithExcess(userMeal.getId(), userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), sumByDate.get(userMeal.getDateTime().toLocalDate()) > CALORIES_PER_DAY)).collect(Collectors.toList());
    }
}
