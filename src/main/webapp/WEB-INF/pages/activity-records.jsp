<%--@elvariable id="activityRecords" type="java.util.List"--%>
<%--@elvariable id="currentPage" type="java.util.Long"--%>
<%--@elvariable id="numPages" type="java.util.Long"--%>
<%--@elvariable id="rowsPerPage" type="java.util.Integer"--%>
<%--@elvariable id="userActivityId" type="java.util.Long"--%>
<%--@elvariable id="user" type="org.oddys.timetracking.dto.UserDto"--%>
<%--@elvariable id="userActivityAssigned" type="java.lang.Boolean"--%>
<html>
<head>
    <title><fmt:message key="title.activity.records"/></title>
</head>
<body>
    <h2>
        <c:if test="${not empty activityRecords}">
            <fmt:message key='title.user.activity.records'>
                <fmt:param value='${activityRecords[0].userFirstName}'/>
                <fmt:param value='${activityRecords[0].userLastName}'/>
                <fmt:param value='${activityRecords[0].activityName}'/>
            </fmt:message>
        </c:if>
    </h2>
    <c:if test="${user.roleName eq 'USER' and userActivityAssigned}">
        <%--                <form action="controller">--%>
        <%--                    <input type="hidden" name="command" value="forward"/>--%>
        <%--                    <input type="hidden" name="targetPage" value="/WEB-INF/pages/add-activity-record.jspf"/>--%>
        <%--                    <input type="submit" value="Add Record"/>--%>
        <%--                </form>--%>
        <%@ include file="/WEB-INF/jspf/add-activity-record.jspf"%>
    </c:if>
    <table>
        <tr>
            <th><fmt:message key="table.column.date"/></th>
            <th><fmt:message key="table.column.duration"/></th>
<%--            <th><fmt:message key="status"/></th>--%>
<%--            <c:if test="${user.roleName eq 'USER' and userActivityAssigned}">--%>
<%--                <th></th>--%>
<%--                <th></th>--%>
<%--            </c:if>--%>
        </tr>
        <c:forEach items="${activityRecords}" var="record">
            <tr>
                <td>${record.activityDate}</td>
                <td>${record.duration}</td>
<%--                <td>${record.userActivityStatusChangeRequested}</td>--%>
<%--                <c:if test="${user.roleName eq 'USER' and userActivityAssigned}">--%>
<%--                    <td>Edit</td>  <!-- TODO Implement -->--%>
<%--                    <td>Delete</td>  <!-- TODO Implement -->--%>
<%--                </c:if>--%>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${not empty activityRecords}">
        <ul>
            <c:if test="${currentPage != 1}">
                <li>
                    <a href="${pageContext.request.contextPath}/controller?command=show_activity_records&userActivityAssigned=${userActivityAssigned}&userActivityId=${userActivityId}&rowsPerPage=${rowsPerPage}&currentPage=${currentPage-1}">
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
                        <li><a href="${pageContext.request.contextPath}/controller?command=show_activity_records&userActivityAssigned=${userActivityAssigned}&userActivityId=${userActivityId}&rowsPerPage=${rowsPerPage}&currentPage=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage lt numPages}">
                <li>
                    <a href="${pageContext.request.contextPath}/controller?command=show_activity_records&userActivityAssigned=${userActivityAssigned}&userActivityId=${userActivityId}&rowsPerPage=${rowsPerPage}&currentPage=${currentPage+1}">
                        <fmt:message key="nav.next"/>
                    </a>
                </li>
            </c:if>
        </ul>
    </c:if>
    <form action="controller">
        <input type="hidden" name="command" value="forward"/>
        <input type="hidden" name="targetPage" value="/WEB-INF/pages/user-activities.jsp"/>
        <input type="submit" value="<fmt:message key="button.back.to.activities"/>"/>
    </form>
</body>
</html>
