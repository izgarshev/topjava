<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal form</title>
</head>
<body>
<jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
<h2>${meal.id == null ? 'Add meal' : 'Edit meal'}</h2>
<form method="post" action="meals">
    <label>id</label>
    <input type="text" name="id" value="${meal.id}" readonly><br>
    <label>description</label>
    <input type="text" name="description" value="${meal.description}" required><br>
    <label>calories</label>
    <input type="number" name="calories" value="${meal.calories}" required><br>
    <label>dateTime</label>
    <input type="datetime-local" name="datetime" value="${meal.dateTime}" required><br>
    <input type="submit">
    <input type="button" value="Отмена" onclick="window.history.back()">
</form>
</body>
</html>
