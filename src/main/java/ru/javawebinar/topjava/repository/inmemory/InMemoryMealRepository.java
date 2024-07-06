package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = getLogger(InMemoryMealRepository.class);
    Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    AtomicInteger counter = new AtomicInteger(0);

    public InMemoryMealRepository() {
        MealsUtil.meals.forEach(meal -> {
            Integer id = counter.incrementAndGet();
            meal.setId(id);
            repository.put(meal.getId(), meal);
        });
    }

    @Override
    public boolean delete(int id) {
        log.debug("delete meal {}", id);
        return repository.remove(id) != null;
    }

    @Override
    public void save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public Meal getById(int id) {
        return repository.get(id);
    }
}
