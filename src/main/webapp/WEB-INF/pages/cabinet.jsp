<html>
<head>
    <title><fmt:message key="title.main"/> - <fmt:message key="title.cabinet"/></title>
</head>
<body>
    <h2><fmt:message key="title.cabinet"/></h2>
    <p><fmt:message key="greet.user"/><c:out value="${sessionScope.user.login}"/></p>

    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="logout"/>
        <input type="submit" value="<fmt:message key="auth.logoutButton"/>">
    </form>

    ${pageContext.request.requestURI}<br/>
    ${pageContext.request.requestURL}<br/>
    ${pageContext.request.contextPath}<br/>
    ${pageContext.request.servletPath}<br/>
</body>
</html>
