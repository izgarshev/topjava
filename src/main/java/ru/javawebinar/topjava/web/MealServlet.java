package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.MealRepo;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private final MealRepo mealRepo;
    private final String mealList = "mealList";

    public MealServlet() {
        this.mealRepo = new UserMealsUtil();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String action = request.getParameter("action");
        log.info(action);
        if (action == null || action.equals("all")) {
            request.setAttribute(mealList, mealRepo.getAll());
            request.getRequestDispatcher("meals.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            String mealId = request.getParameter("id");
            log.info("meal id: " + mealId);
            mealRepo.delete(Long.parseLong(mealId));
            request.setAttribute(mealList, mealRepo.getAll());
            request.getRequestDispatcher("meals.jsp").forward(request, response);
        } else if (action.equals("update")) {
            UserMeal meal = new UserMeal(999L, LocalDateTime.now(), "супчик", 400);
            request.setAttribute("meal", meal);
            log.info(request.getAttribute("meal").toString());
            request.getRequestDispatcher("meals.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("doPost");
        UserMeal meal = new UserMeal(null, LocalDateTime.parse(req.getParameter("dateTime")), req.getParameter("description"), Integer.parseInt(req.getParameter("calories")));
        mealRepo.saveOrUpdate(meal);
        req.setAttribute(mealList, mealRepo.getAll());
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }
}
