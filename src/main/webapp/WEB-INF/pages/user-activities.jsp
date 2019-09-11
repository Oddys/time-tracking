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
            <th>ID</th>
            <th><fmt:message key="title.activity"/></th>
            <th>Is assigned</th>
            <th></th>
        </tr>
        <c:forEach var="activity" items="${userActivities}">
            <tr>
                <td>${activity.id}</td>
                <td><c:out value="${activity.activityName}"/></td>
                <td>${activity.assigned}</td>
                <td></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
