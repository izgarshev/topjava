package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);
        System.out.println("===============================================");

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }


    /**
     * Реализовать метод \`UserMealsUtil.filteredByCycles\` через циклы (\`forEach\`):
     * -  должны возвращаться только записи между \`startTime\` и \`endTime\`
     * -  поле \`UserMealWithExcess.excess\` должно показывать,
     * превышает ли сумма калорий за весь день значение \`caloriesPerDay\`
     * <p>
     * Т.е \`UserMealWithExcess\` - это запись одной еды, но поле \`excess\` будет одинаково для всех записей за этот день.
     */
    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMeal> mealsBetweenTwoDates = new ArrayList<>();
        Map<LocalDate, Integer> sumByDate = new HashMap<>();
        for (UserMeal um : meals) {
            if (TimeUtil.isBetweenHalfOpen(um.getDateTime().toLocalTime(), startTime, endTime)) mealsBetweenTwoDates.add(um);
            sumByDate.merge(um.getDateTime().toLocalDate(), um.getCalories(), Integer::sum);
        }
        System.out.println(sumByDate);
        List<UserMealWithExcess> excessList = new ArrayList<>();
        for (UserMeal userMeal : mealsBetweenTwoDates) {
            excessList.add(new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), sumByDate.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay));
        }
        return excessList;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> sumByDate = new HashMap<>();
        List<UserMeal> filteredList = meals.stream()
                .peek(userMeal -> sumByDate.merge(userMeal.getDateTime().toLocalDate(), userMeal.getCalories(), Integer::sum))
                .filter(userMeal -> TimeUtil.isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                .collect(Collectors.toList());
        System.out.println(sumByDate);
        return filteredList.stream().map(userMeal -> new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), sumByDate.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay)).collect(Collectors.toList());
    }
}
