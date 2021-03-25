package ru.javawebinar.topjava.util;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class UserMealsUtil implements MealRepo {
    private static final Logger log = getLogger(UserMealsUtil.class);
    public static ConcurrentHashMap<Long, UserMeal> repository = new ConcurrentHashMap<>();
    public static AtomicLong counter = new AtomicLong(0);
    public static final int CALORIES_PER_DAY = 2000;

    @Override
    public List<UserMealWithExcess> getAll() {
        Map<LocalDate, Integer> sumByDate = new HashMap<>();
        List<UserMeal> filteredList = repository.values().stream()
                .peek(userMeal -> sumByDate.merge(userMeal.getDateTime().toLocalDate(), userMeal.getCalories(), Integer::sum))
                .collect(Collectors.toList());
        System.out.println(sumByDate);
        return filteredList.stream().map(userMeal -> new UserMealWithExcess(userMeal.getId(), userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), sumByDate.get(userMeal.getDateTime().toLocalDate()) > CALORIES_PER_DAY)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long userId) {
        log.warn(String.valueOf(userId));
        if (userId == null) return;
        UserMeal meal = repository.values().stream().filter(userMeal1 -> userMeal1.getId().equals(userId)).findFirst().get();
        repository.values().remove(meal);
    }

    public static List<UserMealWithExcess> filteredByStreams(LocalTime startTime, LocalTime endTime) {
        Map<LocalDate, Integer> sumByDate = new HashMap<>();
        List<UserMeal> filteredList = repository.values().stream()
                .peek(userMeal -> sumByDate.merge(userMeal.getDateTime().toLocalDate(), userMeal.getCalories(), Integer::sum))
                .filter(userMeal -> TimeUtil.isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                .collect(Collectors.toList());
        System.out.println(sumByDate);
        return filteredList.stream().map(userMeal -> new UserMealWithExcess(userMeal.getId(), userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), sumByDate.get(userMeal.getDateTime().toLocalDate()) > CALORIES_PER_DAY)).collect(Collectors.toList());
    }

    @Override
    public void saveOrUpdate(UserMeal meal) {
        log.info("save method");
        if (meal.getId() == null) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
        } else {
            repository.put(meal.getId(), meal);
        }
    }
}
