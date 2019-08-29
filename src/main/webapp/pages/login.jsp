<html>
<head>
    <title><fmt:message key="title.main"/> - <fmt:message key="title.login"/></title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="login"/>
        <fmt:message key="auth.loginText"/><br/>
        <input type="text" name="login" value=""/><br/>
        <fmt:message key="auth.passwordText"/><br/>
        <input type="password" name="password" value=""/><br/>
        <input type="submit" value="<fmt:message key="auth.loginButton"/>">
    </form>

    ${pageContext.request.requestURI}<br/>
    ${pageContext.request.requestURL}<br/>
    ${pageContext.request.contextPath}<br/>
    ${pageContext.request.servletPath}<br/>
</body>
</html>
