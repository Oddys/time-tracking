<%--@elvariable id="activities" type="java.util.List"--%>
<%--@elvariable id="user" type="org.oddys.timetracking.dto.UserDto"--%>
<%--@elvariable id="currentPage" type="long"--%>
<%--@elvariable id="rowsPerPage" type="int"--%>
<%--@elvariable id="numPages" type="long"--%>
<html>
<head>
    <!-- FIXME -->
    <title>Activities</title>
</head>
<body>
    <h2>Activities</h2>
    <table>
        <tr>
            <th>Name</th>
            <th>Approved</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${activities}" var="activity">
            <tr>
                <td>${activity.name}</td>
                <td>${activity.approved}</td>
                <c:if test="${user.roleName eq 'USER' and activity.approved}">
                    <td>Request for assignment</td>  <!-- TODO Implement -->
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${not empty activities}">
        <ul>
            <c:if test="${currentPage != 1}">
                <li>
                    <a href="${pageContext.request.contextPath}/controller?command=show_activities&rowsPerPage=${rowsPerPage}&currentPage=${currentPage-1}">
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
                        <li><a href="${pageContext.request.contextPath}/controller?command=show_activities&rowsPerPage=${rowsPerPage}&currentPage=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage lt numPages}">
                <li>
                    <a href="${pageContext.request.contextPath}/controller?command=show_activities&rowsPerPage=${rowsPerPage}&currentPage=${currentPage+1}">
                        <fmt:message key="nav.next"/>
                    </a>
                </li>
            </c:if>
        </ul>
    </c:if>
</body>
</html>
