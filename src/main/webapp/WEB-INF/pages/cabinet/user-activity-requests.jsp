<%--@elvariable id="userActivities" type="java.util.List"--%>
<%--@elvariable id="currentPage" type="long"--%>
<%--@elvariable id="rowsPerPage" type="int"--%>
<%--@elvariable id="numPages" type="long"--%>
<html>
<head>
    <title><fmt:message key="title.user.activity.requests"/></title>
</head>
<body>
    <h2><fmt:message key="title.user.activity.requests"/> </h2>
    <c:if test="${not empty messageKey}">
        <fmt:message key="${messageKey}"/>
        <c:remove var="messageKey" scope="session"/>
    </c:if>
    <table class="table table-hover table-striped table-bordered">
        <tr>
            <th><fmt:message key="table.column.user.id"/></th>
            <th><fmt:message key="table.column.user.firstname"/></th>
            <th><fmt:message key="table.column.user.lastname"/></th>
            <th><fmt:message key="table.column.activity"/></th>
            <th><fmt:message key="table.column.action"/> </th>
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
                                <input class="btn btn-secondary" type="submit" value="<fmt:message key="button.activity.stop"/>"/>
                            </c:when>
                            <c:otherwise>
                                <input class="btn btn-secondary" type="submit" value="<fmt:message key="button.user.activity.assign"/> "/>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>


    <c:if test="${not empty userActivities}">
        <nav>
            <ul class="pagination pagination-lg px-1 py-1">
                <c:if test="${currentPage != 1}">
                    <li class="page-item">
                        <span class="border px-2 py-1">
                            <a href="${pageContext.request.contextPath}/controller?command=show_activity_requests&rowsPerPage=${rowsPerPage}&currentPage=${currentPage-1}">
                                <fmt:message key="nav.previous"/>
                            </a>
                        </span>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${numPages}" var="i">
                    <span class="border px-2 py-1">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <li class="page-item">${i}</li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a href="${pageContext.request.contextPath}/controller?command=show_activity_requests&rowsPerPage=${rowsPerPage}&currentPage=${i}">${i}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </span>
                </c:forEach>
                <c:if test="${currentPage lt numPages}">
                    <li class="page-item">
                        <span class="border px-2 py-1">
                            <a href="${pageContext.request.contextPath}/controller?command=show_activity_requests&rowsPerPage=${rowsPerPage}&currentPage=${currentPage+1}">
                                <fmt:message key="nav.next"/>
                            </a>
                        </span>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:if>
    <form action="${pageContext.request.contextPath}">
        <input class="btn btn-secondary" type="submit" value="<fmt:message key="button.main"/>"/>
    </form>
</body>
</html>
