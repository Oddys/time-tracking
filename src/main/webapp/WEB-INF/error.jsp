<%@ page contentType="text/html;UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="i18n.messages"/>
<html lang="${not empty lang ? lang : 'en'}">
    <head>
        <title><fmt:message key="title.main"/>&nbsp;${requestScope['javax.servlet.error.status_code']}</title>
    </head>
    <body>
    <h2><fmt:message key="error.error"/>&nbsp;<c:out value="${requestScope['javax.servlet.error.status_code']}"/></h2>
    <c:choose>
        <c:when test="${requestScope['javax.servlet.error.status_code'] == 400}">
            <fmt:message key="error.400"/>
        </c:when>
        <c:when test="${requestScope['javax.servlet.error.status_code'] == 401}">
            <fmt:message key="error.401"/>
        </c:when>
        <c:when test="${requestScope['javax.servlet.error.status_code'] == 403}">
            <fmt:message key="error.403"/>
        </c:when>
        <c:when test="${requestScope['javax.servlet.error.status_code'] == 404}">
            <fmt:message key="error.404"/>
        </c:when>
        <c:when test="${requestScope['javax.servlet.error.status_code'] == 500}">
            <fmt:message key="error.500"/>
        </c:when>
    </c:choose>
    <p><fmt:message key="error.go" />&nbsp;<a href="${pageContext.request.contextPath}"><fmt:message key="error.home" /></a></p>
    </body>
</html>