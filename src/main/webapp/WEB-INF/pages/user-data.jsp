<html lang="${not empty lang ? lang : 'en'}">
<html>
<head>
    <title>User data</title>
</head>
<body>
    <c:if test="${not empty message}">
        ${message}
    </c:if>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="add_user"/>
        <label for="login">Login</label>
        <input type="text" name="login" value="" id="login" required/>
        <label for="password">Password</label>
        <input type="password" name="password" value="" id="password" required/>
        <label for="firstName">First name</label>
        <input type="text" name="firstName" value="" id="firstName" required/>
        <label for="lastName">Last name</label>
        <input type="text" name="lastName" value="" id="lastName" required/>
        <label for="role">Role</label>
        <select name="role" id="role">
            <option value="User">User</option>
            <option value="Admin">Admin</option>
        </select>
        <input type="submit" value="<fmt:message key="button.save"/>"/>
    </form>
    <form action="${pageContext.request.contextPath}">
        <input type="submit" value="<fmt:message key="button.main"/>"/>
    </form>
</body>
</html>
