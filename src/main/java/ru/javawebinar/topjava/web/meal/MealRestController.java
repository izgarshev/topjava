package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MealRestController {
    private static final Logger log = LoggerFactory.getLogger(MealRestController.class);
    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal create(Meal meal) {
        return service.create(meal, SecurityUtil.authUserId());
    }

    public boolean delete(int id) {
        return service.delete(id, SecurityUtil.authUserId());
    }

    public Meal get(int id) {
        return service.get(id, SecurityUtil.authUserId());
    }

    public List<Meal> getAll() {
        log.info("get all meals for user {}", SecurityUtil.authUserId());
        return new ArrayList<>(service.getAll(SecurityUtil.authUserId()));
    }

    public List<Meal> getAll(@Nullable LocalDate dateFrom, @Nullable LocalDate dateTo, @Nullable LocalTime timeFrom, @Nullable LocalTime timeTo) {
        return new ArrayList<>(service.getAll(dateFrom, dateTo, timeFrom, timeTo, SecurityUtil.authUserId()));
    }
}