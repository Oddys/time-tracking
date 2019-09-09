<html>
<head>
    <title>Users List</title>
</head>
<body>
<h2>Users:</h2>
${message}
<table>
    <tr>
        <th>user</th>
        <th></th>
    </tr>
    <c:forEach var="u" items="${users}">
        <td>${u.firstName}</td>
        <td></td>
    </c:forEach>
</table>
</body>
</html>
