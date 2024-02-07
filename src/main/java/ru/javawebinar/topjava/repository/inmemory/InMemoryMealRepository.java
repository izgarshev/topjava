package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.MealServlet;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = getLogger(InMemoryMealRepository.class);
    private static final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private static final AtomicInteger counter = new AtomicInteger(0);

    //init repo
    static {
        MealsUtil.meals.forEach(meal -> {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
        });
        log.warn("meal repo after static: " + repository);
    }

    @Override
    public List<Meal> getAll() {
        log.debug("getAll");
        return new ArrayList<>(repository.values());
    }

    @Override
    public void save(Meal meal) {
        log.debug("save");
        if (meal.getId() == null) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
    }

    @Override
    public boolean delete(Integer id) {
        log.debug("delete {}", id);
        return repository.remove(id) != null;
    }

    @Override
    public Meal getById(Integer id) {
        log.debug("getById {}", id);
        return repository.getOrDefault(id, null);
    }
}
