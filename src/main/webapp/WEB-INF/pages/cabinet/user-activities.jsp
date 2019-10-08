<%--@elvariable id="user" type="org.oddys.timetracking.dto.UserDto"--%>
<%--@elvariable id="userActivities" type="org.oddys.timetracking.dto.UserActivitiesDto"--%>
<%--@elvariable id="messageKey" type="java.lang.String"--%>
<html>
<head>
    <title>
        <fmt:message key="title.activities.user">
            <fmt:param value="${userActivities.firstName}"/>
            <fmt:param value="${userActivities.lastName}"/>
        </fmt:message>
    </title>
</head>
<body>
    <h2>
        <fmt:message key="title.activities.user">
            <fmt:param value="${userActivities.firstName}"/>
            <fmt:param value="${userActivities.lastName}"/>
        </fmt:message>
    </h2>
    <c:if test="${not empty messageKey}">
        <div class="text-info"><fmt:message key="${messageKey}"/></div>
        <c:remove var="messageKey" scope="session"/>
    </c:if>
    <table class="table table-hover table-striped table-bordered">
        <tr>
            <th><fmt:message key="title.activity"/></th>
            <th><fmt:message key="table.header.status"/> </th>
            <th></th>
            <c:if test="${user.roleName eq 'USER'}">
                <th></th>
            </c:if>
        </tr>
        <c:forEach var="currentUserActivity" items="${userActivities.userActivities}">
            <tr>
                <td><c:out value="${currentUserActivity.activityName}"/></td>
                <td>
                    <c:choose>
                        <c:when test="${currentUserActivity.assigned and not currentUserActivity.statusChangeRequested}">
                            <fmt:message key="table.column.assigned"/>
                        </c:when>
                        <c:when test="${not currentUserActivity.assigned and currentUserActivity.statusChangeRequested}">
                            <fmt:message key="table.column.assign.wait"/>
                        </c:when>
                        <c:when test="${currentUserActivity.assigned and currentUserActivity.statusChangeRequested}">
                            <fmt:message key="table.column.cancel.wait"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="table.column.notassigned"/>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/cabinet/activity-records">
                        <input type="hidden" name="command" value="show_activity_records">
                        <input type="hidden" name="userActivityId" value="${currentUserActivity.id}"/>
                        <input type="hidden" name="currentPage" value="1"/>
                        <input type="hidden" name="rowsPerPage" value="5"/>
                        <input class="btn btn-secondary" type="submit" value="<fmt:message key="button.show"/>"/>
                    </form>
                </td>
                <c:if test="${user.roleName eq 'USER'}">
                    <td>
                        <c:if test="${currentUserActivity.assigned and not currentUserActivity.statusChangeRequested}">
                            <form action="${pageContext.request.contextPath}/cabinet/stop-activity" method="post">
                                <input type="hidden" name="command" value="stop_activity"/>
                                <input type="hidden" name="userActivityId" value="${currentUserActivity.id}"/>
                                <input type="hidden" name="userId" value="${userActivities.userId}"/>
                                <input class="btn btn-secondary" type="submit" value="<fmt:message key="button.activity.stop"/>"/>
                            </form>
                        </c:if>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <form action="${pageContext.request.contextPath}">
        <input class="btn btn-secondary" type="submit" value="<fmt:message key="button.main"/>"/>
    </form>
</body>
</html>
