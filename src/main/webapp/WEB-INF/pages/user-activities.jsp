<html>
<head>
    <title><fmt:message key="title.activities.user"/></title>
</head>
<body>
    <h2><fmt:message key="title.activities.user"/>&nbsp;${user.firstname}&nbsp;${user.lastname}</h2>
    <table>
        <tr>
            <th><fmt:message key="title.activity"/></th>
            <th></th>
        </tr>
        <c:forEach var="activity" items="activities">
            <td><c:out value="${activity}"/></td>
            <td></td>
        </c:forEach>
    </table>
</body>
</html>
