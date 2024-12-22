<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<script src="resources/js/topjava.common.js" defer></script>
<script src="resources/js/topjava.meals.js" defer></script>
<div class="container py-4">
    <section>
        <h3 class="text-center"><spring:message code="meal.title"/></h3>
        <form method="get" action="meals/filter" id="mealFilter">
        <div class="card border-dark">
                <input type="text" value="">
                <input type="date" value="">
                <input type="datetime-local" value="">
                <div class="card-body row">
                    <div class="col-2">
                        <spring:message code="meal.startDate"/>:
                        <input class="form-control" type="date" name="startDate" value="${param.startDate}">
                    </div>
                    <div class="col-2">
                        <spring:message code="meal.endDate"/>:
                        <input class="form-control" type="date" name="endDate" value="${param.endDate}">
                    </div>
                    <div class="col-3 offset-2">
                        <spring:message code="meal.startTime"/>:
                        <input class="form-control" type="time" name="startTime" value="${param.startTime}">
                    </div>
                    <div class="col-3">
                        <spring:message code="meal.endTime"/>:
                        <input class="form-control" type="time" name="endTime" value="${param.endTime}">
                    </div>
                </div>
                <div class="card-footer text-right">
                    <button class="btn btn-danger" onclick="cleanFilter()">
                        <spring:message code="common.cancel"/>
                    </button>
                    <button type="submit" class="btn btn-primary">
                        <spring:message code="meal.filter"/>
                    </button>
                </div>
        </div>
        </form>
        <hr>
        <%--        <a href="meals/create"><spring:message code="meal.add"/></a>--%>
        <button class="btn btn-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            <spring:message code="meal.add"/>
        </button>
        <hr>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="meal.dateTime"/></th>
                <th><spring:message code="meal.description"/></th>
                <th><spring:message code="meal.calories"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${requestScope.meals}" var="meal">
                <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
                <tr data-meal-excess="${meal.excess}" id="${meal.id}">
                    <td>
                            <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                            <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                            <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                            ${fn:formatDateTime(meal.dateTime)}
                    </td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                        <%--                    <td><a href="meals/update?id=${meal.id}"><spring:message code="common.update"/></a></td>--%>
                    <td><a><span class="fa fa-pencil"></span></a></td>
                    <td><a class="delete"><span class="fa fa-remove"></span></a></td>
                        <%--                    <td><a href="meals/delete?id=${meal.id}"><spring:message code="common.delete"/></a></td>--%>
                </tr>
            </c:forEach>
        </table>
    </section>
</div>

<div class="modal fade" tabindex="-1" id="mealFormModal">
    <%--    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>--%>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><spring:message code="meal.add"/></h4>
            </div>
            <div class="modal-body">
                <form id="mealDetailsForm">
                    <input type="hidden" id="id" name="id">
                    <div class="form-group">
                        <label class="col-form-label" for="date"><spring:message code="meal.dateTime"/>:</label>
                        <input id="date" class="form-control" type="datetime-local"
                               name="dateTime" required>
                    </div>
                    <div class="form-group">
                        <label for="desc"><spring:message code="meal.description"/>:</label>
                        <input class="form-control" id="desc" type="text" size=40
                               name="description" required>
                    </div>
                    <div class="form-group">
                        <label for="cals"><spring:message code="meal.calories"/>:</label>
                        <input class="form-control" id="cals" type="number" name="calories"
                               required>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>