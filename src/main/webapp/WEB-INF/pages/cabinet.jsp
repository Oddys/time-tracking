<html>
<head>
    <title><fmt:message key="title.main"/> - <fmt:message key="title.cabinet"/></title>
</head>
<body>
    <h2><fmt:message key="title.cabinet"/></h2>
    <p><fmt:message key="greet.user"/><c:out value="${sessionScope.user.firstName} ${sessionScope.user.lastName}"/></p>

<%--    <form action="${pageContext.request.contextPath}/controller" method="post">--%>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="logout"/>
        <input type="submit" value="<fmt:message key="button.logout"/>">
    </form>

    <c:choose>
        <c:when test="${user.role == 'ADMIN'}">
            <%@ include file="/WEB-INF/jspf/cabinet-admin.jspf"%>
        </c:when>
        <c:otherwise>
            <%@ include file="/WEB-INF/jspf/cabinet-user.jspf"%>
        </c:otherwise>
    </c:choose>

    ${pageContext.request.requestURI}<br/>
    ${pageContext.request.requestURL}<br/>
    ${pageContext.request.contextPath}<br/>
    ${pageContext.request.servletPath}<br/>
</body>
</html>
