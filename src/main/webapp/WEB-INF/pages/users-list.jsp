<%--@elvariable id="userNotFound" type="java.lang.Boolean"--%>
<%--@elvariable id="users" type="java.util.List"--%>
<html>
<head>
    <title>Users List</title>
</head>
<body>
<h2><fmt:message key="user.search.heading"/></h2>
<c:if test="${userNotFound}">
    <fmt:message key="user.notfound">
        <fmt:param value="${param.lastName}"/>
    </fmt:message>
</c:if>
<table>
    <tr>
        <th><fmt:message key="user.user"/></th>
        <th><fmt:message key="action"/></th>
    </tr>
    <c:forEach var="currentUser" items="${users}">
        <tr>
            <td>${currentUser.firstName}&nbsp;${currentUser.lastName}</td>
            <td>
                <form action="controller">
                    <input type="hidden" name="command" value="show_user_activities"/>
                    <input type="hidden" name="userId" value="${currentUser.userId}"/>
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
