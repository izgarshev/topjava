package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealTo> getAll(@Nullable LocalDate startDate, @Nullable LocalDate endDate, @Nullable LocalTime startTime, @Nullable LocalTime endTime) {
        log.info("get all");
        return service.getAll(SecurityUtil.authUserId(), startDate, endDate, startTime, endTime);
    }

    public Meal getById(int id) {
        log.info("get by id {}", id);
        return service.getById(id, SecurityUtil.authUserId());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, SecurityUtil.authUserId());
    }

    public Meal save(Meal meal) {
        log.info("save {}", meal);
        return service.save(meal, SecurityUtil.authUserId());
    }
}