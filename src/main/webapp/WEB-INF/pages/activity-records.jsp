<%--@elvariable id="activityRecords" type="java.util.List"--%>
<%--@elvariable id="currentPage" type="java.util.Long"--%>
<%--@elvariable id="numPages" type="java.util.Long"--%>
<%--@elvariable id="rowsPerPage" type="java.util.Integer"--%>
<html>
<head>
    <title>Activity Records</title>
</head>
<body>
    <h2>
        <c:if test="${not empty activityRecords}">
            <fmt:message key='title.activity.records'>
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

    <ul>
        <c:if test="${currentPage != 1}">
            <li>
                <a href="${pageContext.request.contextPath}/controller?command=show_activity_records&rowsPerPage=${rowsPerPage}&currentPage=${currentPage-1}">
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
                    <li><a href="${pageContext.request.contextPath}/controller?command=show_activity_records&rowsPerPage=${rowsPerPage}&currentPage=${i}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage lt numPages}">
            <li>
                <a href="${pageContext.request.contextPath}/controller?command=show_activity_records&rowsPerPage=${rowsPerPage}&currentPage=${currentPage+1}">
                    <fmt:message key="nav.next"/>
                </a>
            </li>
        </c:if>
    </ul>
</body>
</html>
