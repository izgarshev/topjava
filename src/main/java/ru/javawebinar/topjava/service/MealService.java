package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Service
public class MealService {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public List<MealTo> getAll(int userId, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        log.info("get all for user id {}", userId);
        if (userId != SecurityUtil.authUserId()) {
            throw new NotFoundException("userId not equals auth user id");
        }
        return MealsUtil.getFilteredTos(repository.getAll(userId), SecurityUtil.authUserCaloriesPerDay(), startDate, endDate, startTime, endTime);
    }



    public Meal getById(int id, int userId) {
        return repository.get(id, userId);
    }

    public Meal save(Meal meal, int userId) {
        log.info("save {}", meal);
        return repository.save(meal, userId);
    }

    public void delete(int id, int userId) {
        log.info("delete {}", id);
        repository.delete(id, userId);
    }
}