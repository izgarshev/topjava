<%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 04.07.2024
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<h2>Meals</h2>
<body>
<a href="meals?action=create">Новая еда</a>
<p/>
<table>
    <tr>
        <th>date</th>
        <th>description</th>
        <th>calories</th>
        <th>update</th>
        <th>delete</th>
    </tr>
    <c:forEach items="${requestScope.meals}" var="meal">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
        <tr class="${meal.excess ? 'exceed' : 'normal'}">
            <td>${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
