<html>
<head>
    <title>Add Activity</title>
</head>
<body>
    <h2>Activity Adding</h2>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="add_activity_record"/>
        <%--    FIXME--%>
        ${userActivityId}<br/>
        <label for="date">Date</label>
        <input type="date" name="activityDate" id="date" min="2019-01-01" onchange="checkDate(this)" required/>
        <label for="duration">Duration (in minutes)</label>
        <input type="number" name="duration" id="duration" min="10" max="1440" step="10" required/>
        <input type="submit" value="<fmt:message key="button.save"/>"/>
    </form>
</body>
</html>
