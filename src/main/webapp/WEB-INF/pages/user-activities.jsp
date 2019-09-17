<%--@elvariable id="user" type="org.oddys.timetracking.dto.UserDto"--%>
<%--@elvariable id="userActivities" type="java.util.List"--%>
<%--@elvariable id="errorMessageKey" type="java.lang.String"--%>
<html>
<head>
    <title>
        <fmt:message key="title.activities.user">
            <fmt:param value="${userFirstName}"/>
            <fmt:param value="${userLastName}"/>
        </fmt:message>
    </title>
</head>
<body>
    <h2>
        <fmt:message key="title.activities.user">
            <fmt:param value="${userFirstName}"/>
            <fmt:param value="${userLastName}"/>
        </fmt:message>
    </h2>
    <c:if test="${not empty messageKey}">
        <fmt:message key="${messageKey}"/>
    </c:if>
    <table>
        <tr>
            <th><fmt:message key="title.activity"/></th>
<%--            <th><fmt:message key="activity.user.assigned"/></th>--%>
<%--            <th><fmt:message key="table.column.status"/></th>--%>
            <th><fmt:message key="table.header.status"/> </th>
<%--            <th><fmt:message key="table.column.action"/></th>--%>
            <th></th>
            <c:if test="${user.roleName eq 'USER'}">
                <th></th>
            </c:if>
        </tr>
        <c:forEach var="currentUserActivity" items="${userActivities}">
            <tr>
                <td><c:out value="${currentUserActivity.activityName}"/></td>
<%--                <td>${currentUserActivity.assigned}</td>--%>
<%--                <td>${currentUserActivity.statusChangeRequested}</td>--%>
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
                    <form action="controller">
                        <input type="hidden" name="command" value="show_activity_records">
                        <input type="hidden" name="userActivityId" value="${currentUserActivity.id}"/>
                        <input type="hidden" name="userActivityAssigned" value="${currentUserActivity.assigned}"/>
                        <input type="hidden" name="currentPage" value="1"/>
                        <input type="hidden" name="rowsPerPage" value="5"/>
                        <input type="submit" value="<fmt:message key="button.show"/>"/>
                    </form>
                </td>
                <c:if test="${user.roleName eq 'USER'}">
                    <td>
                        <c:if test="${currentUserActivity.assigned and not currentUserActivity.statusChangeRequested}">
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="stop_activity"/>
                                <input type="hidden" name="userActivityId" value="${currentUserActivity.id}"/>
                                <input type="submit" value="Stop activity"/>
                            </form>
                        </c:if>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <form action="${pageContext.request.contextPath}">
        <input type="submit" value="<fmt:message key="button.main"/>"/>
    </form>
</body>
</html>
