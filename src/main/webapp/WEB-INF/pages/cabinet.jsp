<%--@elvariable id="user" type="org.oddys.timetracking.dto.UserDto"--%>
<html>
<head>
    <title><fmt:message key="title.main"/> - <fmt:message key="title.cabinet"/></title>
</head>
<body>
    <h2><fmt:message key="title.cabinet"/></h2>
    <p>
        <fmt:message key="greet.user">
            <fmt:param value="${sessionScope.user.firstName}"/>
            <fmt:param value="${sessionScope.user.lastName}"/>
        </fmt:message>
    </p>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="signout"/>
        <input type="submit" value="<fmt:message key="button.signout"/>">
    </form>

    <c:choose>
        <c:when test="${user.roleName == 'ADMIN'}">
            <%@ include file="/WEB-INF/jspf/cabinet-admin.jspf"%>
        </c:when>
        <c:otherwise>
            <%@ include file="/WEB-INF/jspf/cabinet-user.jspf"%>
        </c:otherwise>
    </c:choose>
</body>
</html>
