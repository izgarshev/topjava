package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private final MealRepository repository = new InMemoryMealRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
         if (action == null || action.equals("all")) {
            log.debug("meals all");
            req.setAttribute("meals", MealsUtil.filteredByStreams(repository.getAll(), LocalTime.MIN, LocalTime.MAX, MealsUtil.caloriesPerDay));
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        } else if (action.equals("create")) {
             log.debug("create");
             req.setAttribute("meal", new Meal(null, LocalDateTime.now(), "desc", 3));
             req.getRequestDispatcher("/mealForm.jsp").forward(req, resp);
         } else if (action.equals("delete")) {
             log.debug("delete");
             Integer id;
             String idStr = req.getParameter("id");
             id = idStr == null ? null : Integer.parseInt(idStr);
             repository.delete(id);
             resp.sendRedirect("meals");
         } else if (action.equals("update")) {
             log.debug("update");
             Integer id;
             String idStr = req.getParameter("id");
             id = idStr == null ? null : Integer.parseInt(idStr);
             Meal byId = repository.getById(id);
             req.setAttribute("meal", byId);
             req.getRequestDispatcher("/mealForm.jsp").forward(req, resp);
         }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        Integer calories = Integer.parseInt(req.getParameter("calories"));
        String description = req.getParameter("description");
        LocalDateTime datetime = LocalDateTime.parse(req.getParameter("datetime"));
        Integer id;
        String strId = req.getParameter("id");
        id = strId.isEmpty() ? null : Integer.parseInt(strId);
        Meal meal = new Meal();
        if (id != null) {
            meal = repository.getById(id);
        }
        meal.setDateTime(datetime);
        meal.setCalories(calories);
        meal.setDescription(description);
        repository.save(meal);
        resp.sendRedirect("meals");
    }
}
