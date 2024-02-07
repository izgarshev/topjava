<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://localhost:8080/functions" %>
<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<a href="meals?action=create">Добавить</a>
<table border="1" cellpadding="10px">
    <jsp:useBean id="meals" scope="request" type="java.util.ArrayList"/>
    <thead>
    <th>id</th>
    <th>Описание</th>
    <th>Калории</th>
    <th>Дата</th>
    <th></th>
    <th></th>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.meals}" var="meal">
        <tr class="${meal.excess ? 'excess' : 'normal'}">
            <td>${meal.id}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>${f:formatLocalDateTime(meal.dateTime)}</td>
            <td><a href="meals?action=update&id=${meal.id}">Изменить</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">Удалить</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
