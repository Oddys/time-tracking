<html>
<head>
    <title><fmt:message key="title.main"/></title>
</head>
<body>
    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <%@ include file="/WEB-INF/jspf/login.jspf" %>
        </c:when>
        <c:otherwise>
            <jsp:forward page="/WEB-INF/pages/cabinet.jsp"/>
        </c:otherwise>
    </c:choose>

    ${pageContext.request.requestURI}<br/>
    ${pageContext.request.requestURL}<br/>
    ${pageContext.request.contextPath}<br/>
    ${pageContext.request.servletPath}<br/>
</body>
</html>
