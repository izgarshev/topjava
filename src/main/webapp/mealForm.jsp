<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal form</title>
</head>
<body>
<form method="post" action="meals">
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <input type="text" name="id" value="${meal.id}" readonly>
    <input type="text" name="description" value="${meal.description}" required>
    <input type="number" name="calories" value="${meal.calories}" required>
    <input type="datetime-local" name="datetime" value="${meal.dateTime}" required>
    <input type="submit">
    <input type="button" onclick="window.history.back()" value="Отмена">
</form>
</body>
</html>
