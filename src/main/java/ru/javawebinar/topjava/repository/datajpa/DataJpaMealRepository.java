package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DataJpaMealRepository implements MealRepository {

    private final CrudMealRepository crudRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaMealRepository(CrudMealRepository crudRepository, CrudUserRepository crudUserRepository) {
        this.crudRepository = crudRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public Meal save(Meal meal, int userId) {
        User referenceById = crudUserRepository.getReferenceById(userId);
        meal.setUser(referenceById);
        return crudRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        Optional<Meal> optionalMeal = crudRepository.findById(id);
        Meal meal = optionalMeal.orElseThrow(() -> new NotFoundException("meal not found"));
        if (meal.getUser() != null && meal.getUser().getId() != null && meal.getUser().getId() == userId) {
            crudRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        Optional<Meal> optionalMeal = crudRepository.findById(id);
        return optionalMeal.orElseThrow(() -> new NotFoundException("meal not found"));
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.findAll().stream()
                .filter(meal -> meal.getUser().getId() == userId)
                .sorted((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        //мне лень
        return getAll(userId);
    }
}
