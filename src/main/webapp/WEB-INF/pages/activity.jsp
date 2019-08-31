<html>
<head>
    <title><fmt:message key="title.activity"/></title>
</head>
<body>
<h2><fmt:message key="title.activity.user"/>&nbsp;${user.firstname}&nbsp;${user.lastname}</h2>
    <form method="post" action="controller">
        <input type="date" name="date"/>
        <input type="number" name="duration"/>
        <input type="submit" value="<fmt:message key="button.save"/>"/>
    </form>
</body>
</html>
