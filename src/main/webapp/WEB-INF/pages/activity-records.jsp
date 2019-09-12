<html>
<head>
    <title>Activity Records</title>
</head>
<body>
    Number of rows: ${numRows}

    <table>
        <tr>
            <th>Date</th>
            <th>Duration</th>
        </tr>
        <c:forEach items="activityRecords" var="record">
            <tr>
                <td>${record.activityDate}</td>
                <td>${record.duration}</td>
            </tr>
        </c:forEach>
    </table>

    <ul>
        <c:if test="${currentPage != 1}">
            <li href="controller/?command=show_activity_records&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
            </li>
        </c:if>
        <c:forEach begin="1" end="${numPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li>${i}</li>
                </c:when>
                <c:otherwise>
                    <li><a href="controller/?command=show_activity_records&recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage lt numPages}">
            <li><a href="controller?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
            </li>
        </c:if>
    </ul>
</body>
</html>
