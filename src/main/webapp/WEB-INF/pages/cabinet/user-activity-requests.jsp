<%--@elvariable id="userActivities" type="org.oddys.timetracking.dto.PageDto"--%>
<%--@elvariable id="messageKey" type="java.lang.String"--%>
<html>
<head>
    <title><fmt:message key="title.user.activity.requests"/></title>
</head>
<body>
    <h2><fmt:message key="title.user.activity.requests"/> </h2>
    <c:if test="${not empty messageKey}">
        <div class="text-info"><fmt:message key="${messageKey}"/></div>
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
        <c:forEach items="${userActivities.elements}" var="userActivity">
            <tr>
                <td>${userActivity.userId}</td>
                <td>${userActivity.userFirstName}</td>
                <td>${userActivity.userLastName}</td>
                <td>${userActivity.activityName}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/cabinet/change-activity-status" method="post">
                        <input type="hidden" name="command" value="change_activity_status"/>
                        <input type="hidden" name="userActivityId" value="${userActivity.id}"/>
                        <input type="hidden" name="currentAssigned" value="${userActivity.assigned}"/>
                        <input type="hidden" name="rowsPerPage" value="${userActivities.rowsPerPage}"/>
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
                <c:if test="${userActivities.currentPage != 1}">
                    <li class="page-item">
                        <span class="border px-2 py-1">
                            <a href="${pageContext.request.contextPath}/cabinet/show-activity-requests?command=show_activity_requests&rowsPerPage=${userActivities.rowsPerPage}&currentPage=${userActivities.currentPage-1}">
                                <fmt:message key="nav.previous"/>
                            </a>
                        </span>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${userActivities.numPages}" var="i">
                    <span class="border px-2 py-1">
                        <c:choose>
                            <c:when test="${userActivities.currentPage eq i}">
                                <li class="page-item">${i}</li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a href="${pageContext.request.contextPath}/cabinet/show-activity-requests?command=show_activity_requests&rowsPerPage=${userActivities.rowsPerPage}&currentPage=${i}">${i}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </span>
                </c:forEach>
                <c:if test="${userActivities.currentPage lt userActivities.numPages}">
                    <li class="page-item">
                        <span class="border px-2 py-1">
                            <a href="${pageContext.request.contextPath}/cabinet/show-activity-requests?command=show_activity_requests&rowsPerPage=${userActivities.rowsPerPage}&currentPage=${userActivities.currentPage+1}">
                                <fmt:message key="nav.next"/>
                            </a>
                        </span>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:if>
    <form action="${pageContext.request.contextPath}/cabinet">
        <input class="btn btn-secondary" type="submit" value="<fmt:message key="button.main"/>"/>
    </form>
</body>
</html>
