<%--@elvariable id="user" type="org.oddys.timetracking.dto.UserDto"--%>
<%--@elvariable id="userActivities" type="java.util.List"--%>
<html>
<head>
    <title><fmt:message key="title.activities.user"/></title>
</head>
<body>
    <h2>
        <fmt:message key="title.activities.user">
            <fmt:param value="${user.firstName}"/>
            <fmt:param value="${user.lastName}"/>
        </fmt:message> </h2>
    <table>
        <tr>
            <th><fmt:message key="title.activity"/></th>
            <th><fmt:message key="activity.user.assigned"/></th>
            <th><fmt:message key="action"/></th>
        </tr>
        <c:forEach var="currentUserActivity" items="${userActivities}">
            <tr>
                <td><c:out value="${currentUserActivity.activityName}"/></td>
                <td>${currentUserActivity.assigned}</td>
                <td>
                    <form action="controller">
                        <input type="hidden" name="command" value="show_activity_records">
                        <input type="hidden" name="userActivityId" value="${currentUserActivity.id}"/>
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
