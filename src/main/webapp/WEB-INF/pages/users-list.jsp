<html>
<head>
    <title>Users List</title>
</head>
<body>
<h2>Users:</h2>
<table>
    <tr>
        <th>user</th>
        <th></th>
    </tr>
    <c:forEach var="user" items="users">
        <td>${user.firstName}</td>
        <td></td>
    </c:forEach>
</table>
</body>
</html>
