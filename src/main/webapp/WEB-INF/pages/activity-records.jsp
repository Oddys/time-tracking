<%--@elvariable id="activityRecords" type="java.util.List"--%>
<%--@elvariable id="currentPage" type="java.util.Long"--%>
<%--@elvariable id="numPages" type="java.util.Long"--%>
<%--@elvariable id="rowsPerPage" type="java.util.Integer"--%>
<%--@elvariable id="userActivityId" type="java.util.Long"--%>
<%--@elvariable id="user" type="org.oddys.timetracking.dto.UserDto"--%>
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
    <table>
        <tr>
            <th><fmt:message key="date"/></th>
            <th><fmt:message key="duration"/></th>
        </tr>
        <c:forEach items="${activityRecords}" var="record">
            <tr>
                <td>${record.activityDate}</td>
                <td>${record.duration}</td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${not empty activityRecords}">
        <ul>
            <c:if test="${currentPage != 1}">
                <li>
                    <a href="${pageContext.request.contextPath}/controller?command=show_activity_records&userActivityId=${userActivityId}&rowsPerPage=${rowsPerPage}&currentPage=${currentPage-1}">
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
                        <li><a href="${pageContext.request.contextPath}/controller?command=show_activity_records&userActivityId=${userActivityId}&rowsPerPage=${rowsPerPage}&currentPage=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage lt numPages}">
                <li>
                    <a href="${pageContext.request.contextPath}/controller?command=show_activity_records&userActivityId=${userActivityId}&rowsPerPage=${rowsPerPage}&currentPage=${currentPage+1}">
                        <fmt:message key="nav.next"/>
                    </a>
                </li>
            </c:if>
            <c:if test="${user.roleName eq 'USER'}">
                <form action="controller">
                    <input type="hidden" name="command" value="add_activity_record"/>
                    <input type="hidden" name="userActivityId" value="${userActivityId}"/>
                    <input type="submit" value="Add Record"/>
                </form>
            </c:if>
        </ul>
    </c:if>
</body>
</html>
