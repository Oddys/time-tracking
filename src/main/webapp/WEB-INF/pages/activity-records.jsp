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
        <%@ include file="/WEB-INF/jspf/add-activity-record.jspf"%>
    </c:if>
    <table class="table table-hover table-striped table-bordered">
        <tr>
            <th><fmt:message key="table.column.date"/></th>
            <th><fmt:message key="table.column.duration"/></th>
        </tr>
        <c:forEach items="${activityRecords}" var="record">
            <tr>
                <td>${record.activityDate}</td>
                <td>${record.duration}</td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${not empty activityRecords}">
        <nav>
            <ul class="pagination pagination-lg px-1 py-1">
                <c:if test="${currentPage != 1}">
                   <li class="page-item">
                        <span class="border px-2 py-1">
                            <a href="${pageContext.request.contextPath}/controller?command=show_activity_records&userActivityAssigned=${userActivityAssigned}&userActivityId=${userActivityId}&rowsPerPage=${rowsPerPage}&currentPage=${currentPage-1}">
                                <fmt:message key="nav.previous"/>
                            </a>
                        </span>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${numPages}" var="i">

                    <li class="page-item">
                        <span class="border px-2 py-1">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    ${i}
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/controller?command=show_activity_records&userActivityAssigned=${userActivityAssigned}&userActivityId=${userActivityId}&rowsPerPage=${rowsPerPage}&currentPage=${i}">${i}</a>
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </li>

                </c:forEach>
                <c:if test="${currentPage lt numPages}">
                    <li class="page-item">
                        <span class="border px-2 py-1">
                            <a href="${pageContext.request.contextPath}/controller?command=show_activity_records&userActivityAssigned=${userActivityAssigned}&userActivityId=${userActivityId}&rowsPerPage=${rowsPerPage}&currentPage=${currentPage+1}">
                                <fmt:message key="nav.next"/>
                            </a>
                        </span>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:if>
    <form action="controller">
        <input type="hidden" name="command" value="forward"/>
        <input type="hidden" name="targetPage" value="/WEB-INF/pages/user-activities.jsp"/>
        <input type="submit" value="<fmt:message key="button.back.to.activities"/>"/>
    </form>
</body>
</html>
