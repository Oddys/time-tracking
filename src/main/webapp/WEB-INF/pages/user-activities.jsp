<%--@elvariable id="user" type="org.oddys.timetracking.dto.UserDto"--%>
<%--@elvariable id="userActivities" type="java.util.List"--%>
<html>
<head>
    <title><fmt:message key="title.activities.user"/></title>
</head>
<body>
    <h2>
        <%-- FIXME What if no user found? --%>
        <fmt:message key="title.activities.user">
<%--            <fmt:param value="${user.firstName}"/>--%>
<%--            <fmt:param value="${user.lastName}"/>--%>
            <fmt:param value="${userActivities[0].userFirstName}"/>
            <fmt:param value="${userActivities[0].userLastName}"/>
        </fmt:message>
    </h2>
    <table>
        <tr>
            <th><fmt:message key="title.activity"/></th>
            <th><fmt:message key="activity.user.assigned"/></th>
            <th><fmt:message key="status"/></th>
            <th><fmt:message key="action"/></th>
        </tr>
        <c:forEach var="currentUserActivity" items="${userActivities}">
            <tr>
                <td><c:out value="${currentUserActivity.activityName}"/></td>
                <td>${currentUserActivity.assigned}</td>
                <td>${currentUserActivity.statusChangeRequested}</td>
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
            </tr>
        </c:forEach>
    </table>
    <form action="${pageContext.request.contextPath}">
        <input type="submit" value="<fmt:message key="button.main"/>"/>
    </form>
</body>
</html>
