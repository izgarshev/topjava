package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
    @Query("delete from Meal m where m.id=:id and m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    Meal findMealByIdAndUser_Id(int id, int userId);

    List<Meal> findMealsByUser_IdOrderByDateTimeDesc(Integer userId);

    @Query("select m from Meal m where m.user.id=:userId and m.dateTime>=:start and m.dateTime<:end order by m.dateTime desc")
    List<Meal> getBetweenInclusive(@Param("start") LocalDateTime startDateTime, @Param("end") LocalDateTime endDateTime, @Param("userId") int userId);
}
