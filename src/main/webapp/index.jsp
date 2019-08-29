<html>
<head>
    <title><fmt:message key="title.main"/></title>
</head>
<body>
    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <jsp:forward page="/pages/login.jsp"/>
        </c:when>
        <c:otherwise>
            <jsp:forward page="/pages/cabinet.jsp"/>
        </c:otherwise>
    </c:choose>
</body>
</html>
