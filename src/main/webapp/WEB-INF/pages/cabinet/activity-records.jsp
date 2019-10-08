<%--@elvariable id="user" type="org.oddys.timetracking.dto.UserDto"--%>
<%--@elvariable id="activityRecords" type="org.oddys.timetracking.dto.ActivityRecordsPage"--%>
<html>
<head>
    <title><fmt:message key="title.activity.records"/></title>
</head>
<body>
    <h2>
        <c:if test="${not empty activityRecords.elements}">
            <fmt:message key='title.user.activity.records'>
                <fmt:param value='${activityRecords.userFirstName}'/>
                <fmt:param value='${activityRecords.userLastName}'/>
                <fmt:param value='${activityRecords.activityName}'/>
            </fmt:message>
        </c:if>
    </h2>
    <c:if test="${user.roleName eq 'USER' and activityRecords.assigned}">
        <%@ include file="/WEB-INF/jspf/add-activity-record.jspf"%>
    </c:if>
    <table class="table table-hover table-striped table-bordered">
        <tr>
            <th><fmt:message key="table.column.date"/></th>
            <th><fmt:message key="table.column.duration"/></th>
        </tr>
        <c:forEach items="${activityRecords.elements}" var="record">
            <tr>
                <td>${record.activityDate}</td>
                <td>${record.duration}</td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${not empty activityRecords.elements}">
        <nav>
            <ul class="pagination pagination-lg px-1 py-1">
                <c:if test="${activityRecords.currentPage != 1}">
                   <li class="page-item">
                        <span class="border px-2 py-1">
                            <a href="${pageContext.request.contextPath}/cabinet/activity-records?command=show_activity_records&userActivityId=${activityRecords.userActivityId}&rowsPerPage=${activityRecords.rowsPerPage}&currentPage=${activityRecords.currentPage-1}">
                                <fmt:message key="nav.previous"/>
                            </a>
                        </span>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${activityRecords.numPages}" var="i">
                    <li class="page-item">
                        <span class="border px-2 py-1">
                            <c:choose>
                                <c:when test="${activityRecords.currentPage eq i}">
                                    ${i}
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/cabinet/activity-records?command=show_activity_records&userActivityId=${activityRecords.userActivityId}&rowsPerPage=${activityRecords.rowsPerPage}&currentPage=${i}">${i}</a>
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </li>

                </c:forEach>
                <c:if test="${activityRecords.currentPage lt activityRecords.numPages}">
                    <li class="page-item">
                        <span class="border px-2 py-1">
                            <a href="${pageContext.request.contextPath}/cabinet/activity-records?command=show_activity_records&userActivityId=${activityRecords.userActivityId}&rowsPerPage=${activityRecords.rowsPerPage}&currentPage=${activityRecords.currentPage+1}">
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
        <input class="btn btn-secondary" type="submit" value="<fmt:message key="button.back.to.activities"/>"/>
    </form>
</body>
</html>
