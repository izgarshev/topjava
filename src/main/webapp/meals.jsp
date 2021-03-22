<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border="1px solid black">
    <tr>
        <th>#</th>
        <th>Дата</th>
        <th>Описание</th>
        <th>Калории</th>
        <th>Обновить</th>
        <th>Удалить</th>
    </tr>
    <jsp:useBean id="mealList" scope="request" type="java.util.List"/>
    <c:forEach items="${mealList}" var="meal">
        <jsp:useBean id="meal" class="ru.javawebinar.topjava.model.UserMeal"/>
        <tr>
            <td><c:out value="${meal.id}" /></td>
            <td><fmt:formatDate pattern="yyyy-MMM-dd" value="${meal.dateTime}" /></td>
            <td><c:out value="${meal.description}" /></td>
            <td><c:out value="${meal.calories}" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>