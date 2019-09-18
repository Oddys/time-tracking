<%--@elvariable id="userActivities" type="java.util.List"--%>
<%--@elvariable id="currentPage" type="long"--%>
<%--@elvariable id="rowsPerPage" type="int"--%>
<%--@elvariable id="numPages" type="long"--%>
<html>
<head>
    <title>User Activity Requests</title>
</head>
<body>
    <c:if test="${not empty messageKey}">
        ${messageKey}
    </c:if>
    <table>
        <tr>
            <th>User ID</th>
            <th>User name</th>
            <th>User Last name</th>
            <th>Activity</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${userActivities}" var="userActivity">
            <tr>
                <td>${userActivity.userId}</td>
                <td>${userActivity.userFirstName}</td>
                <td>${userActivity.userLastName}</td>
                <td>${userActivity.activityName}</td>
                <td>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="change_activity_status"/>
                        <input type="hidden" name="userActivityId" value="${userActivity.id}"/>
                        <input type="hidden" name="currentAssigned" value="${userActivity.assigned}"/>
                        <c:choose>
                            <c:when test="${userActivity.assigned}">
                                <input type="submit" value="Stop activity"/>
                            </c:when>
                            <c:otherwise>
                                <input type="submit" value="Assign activity"/>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${not empty userActivities}">
        <ul>
            <c:if test="${currentPage != 1}">
                <li>
                    <a href="${pageContext.request.contextPath}/controller?command=show_activity_requests&rowsPerPage=${rowsPerPage}&currentPage=${currentPage-1}">
                        <fmt:message key="nav.previous"/>
                    </a>
                </li>
            </c:if>
            <c:forEach begin="1" end="${numPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li>${i}</li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageContext.request.contextPath}/controller?command=show_activity_requests&rowsPerPage=${rowsPerPage}&currentPage=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage lt numPages}">
                <li>
                    <a href="${pageContext.request.contextPath}/controller?command=show_activity_requests&rowsPerPage=${rowsPerPage}&currentPage=${currentPage+1}">
                        <fmt:message key="nav.next"/>
                    </a>
                </li>
            </c:if>
        </ul>
    </c:if>
    <form action="${pageContext.request.contextPath}">
        <input type="submit" value="<fmt:message key="button.main"/>"/>
    </form>
</body>
</html>
