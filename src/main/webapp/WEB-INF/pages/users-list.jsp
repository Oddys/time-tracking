<%--@elvariable id="userNotFound" type="java.lang.Boolean"--%>
<%--@elvariable id="users" type="java.util.List"--%>
<html>
<head>
    <title><fmt:message key="title.users"/></title>
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
        <th><fmt:message key="table.column.action"/></th>
    </tr>
    <c:forEach var="currentUser" items="${users}">
        <tr>
            <td>${currentUser.firstName}&nbsp;${currentUser.lastName}</td>
            <td>
                <form action="controller">
                    <input type="hidden" name="command" value="show_user_activities"/>
                    <input type="hidden" name="userId" value="${currentUser.userId}"/>
                    <input type="hidden" name="userFirstName" value="${currentUser.firstName}"/>
                    <input type="hidden" name="userLastName" value="${currentUser.lastName}"/>
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
