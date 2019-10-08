<html>
<head>
    <title><fmt:message key="title.main"/></title>
</head>
<body>
    Context: ${pageContext.request.contextPath}<br/>
    Servlet: ${pageContext.request.servletPath}<br/>
    URI: ${pageContext.request.requestURI}<br/>
    URL: ${pageContext.request.requestURL}<br/>

    Context: <c:url value="/"/>
    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <%@ include file="/WEB-INF/jspf/signin.jspf" %>
        </c:when>
        <c:otherwise>
            <jsp:forward page="/WEB-INF/pages/cabinet.jsp"/>
        </c:otherwise>
    </c:choose>
</body>
</html>
