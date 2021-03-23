<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ru">
<head>
    <title>Meals</title>
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Список еды</h2>
<hr>
<a href="mealForm.jsp">Добавить еду</a>
<table border="1px solid black">
    <thead>
    <tr>
        <th>#</th>
        <th>Дата</th>
        <th>Описание</th>
        <th>Калории</th>
        <th>Обновить</th>
        <th>Удалить</th>
    </tr>
    </thead>
    <c:forEach items="${mealList}" var="meal">
        <tr class="${meal.excess ? 'excess' : 'normal'}">
            <td>${meal.id}</td>
            <td>${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="/topjava/meals?action=update&id=${meal.id}">Обновить</a></td>
            <td><a href="/topjava/meals?action=delete&id=${meal.id}">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>