package ru.javawebinar.topjava.web;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class SecurityUtil {
    private static int authUserid;

    public static void setAuthUserid(int authUserid) {
        SecurityUtil.authUserid = authUserid;
    }

    public static int authUserId() {
        return authUserid;
    }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}