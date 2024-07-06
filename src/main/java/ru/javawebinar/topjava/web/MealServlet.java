package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    MealRepository repository;

    public MealServlet() {
        repository = new InMemoryMealRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        log.debug("action is " + action);
        if (action == null) {
            req.setAttribute("meals", MealsUtil.filteredByStreams(repository.getAll(), LocalTime.MIN, LocalTime.MAX, MealsUtil.caloriesPerDay));
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        } else if (action.equals("delete")) {
            String requestMealId = req.getParameter("id");
            int id = Integer.parseInt(requestMealId);
            log.debug("delete meal with id {}", id);
            repository.delete(id);
            resp.sendRedirect("meals");
        } else if (action.equals("update") || action.equals("create")) {
            String requestMealId = req.getParameter("id");
            Integer id = requestMealId == null ? null : Integer.parseInt(requestMealId);
            if (id == null) {
                req.setAttribute("meal", new Meal(null, LocalDateTime.now(), "default", 333));
            } else {
                req.setAttribute("meal", repository.getById(id));
            }
            log.debug("set attribute meal as " + req.getAttribute("meal"));
            req.getRequestDispatcher("/mealForm.jsp").forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqId = req.getParameter("id");
        Integer id = reqId.isEmpty() ? null : Integer.parseInt(reqId);
        String description = req.getParameter("description");
        Integer calories = Integer.valueOf(req.getParameter("calories"));
        LocalDateTime ldt = LocalDateTime.parse(req.getParameter("datetime"));
        log.debug("do post id: " + id);
        repository.save(new Meal(id, ldt, description, calories));
        resp.sendRedirect("meals");
    }
}
