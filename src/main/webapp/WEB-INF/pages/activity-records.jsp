<%--@elvariable id="activityRecords" type="java.util.List"--%>
<%--@elvariable id="currentPage" type="java.util.Long"--%>
<%--@elvariable id="numPages" type="java.util.Long"--%>
<%--@elvariable id="recordsPerPage" type="java.util.Integer"--%>
<html>
<head>
    <title>Activity Records</title>
</head>
<body>
    <h2>Activity ${activityRecords[0].activityName}</h2>
    DEBUG:<br/>
    Current page: ${currentPage}<br/>
    Records per page: ${recordsPerPage}<br/>
    Num pages: ${numPages}<br/>
    <table>
        <tr>
            <th>Date</th>
            <th>Duration</th>
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
            <li><a href="${pageContext.request.contextPath}/controller?command=show_activity_records&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
            </li>
        </c:if>
        <c:forEach begin="1" end="${numPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li>${i}</li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageContext.request.contextPath}/controller?command=show_activity_records&recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage lt numPages}">
            <li><a href="${pageContext.request.contextPath}/controller?command=show_activity_records&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
            </li>
        </c:if>
    </ul>
</body>
</html>
