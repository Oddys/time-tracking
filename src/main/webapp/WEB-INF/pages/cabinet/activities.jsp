<%--@elvariable id="activities" type="java.util.List"--%>
<%--@elvariable id="user" type="org.oddys.timetracking.dto.UserDto"--%>
<%--@elvariable id="currentPage" type="long"--%>
<%--@elvariable id="rowsPerPage" type="int"--%>
<%--@elvariable id="numPages" type="long"--%>
<%--@elvariable id="messageKey" type="String"--%>
<%--@elvariable id="activityName" type="String"--%>
<html>
<head>
    <title><fmt:message key="title.activities"/></title>
</head>
<body>
    <h2><fmt:message key="title.activities"/></h2>
    <c:if test="${not empty messageKey}">
        <fmt:message key="${messageKey}">
            <fmt:param value="${activityName}"/>
        </fmt:message>
    </c:if>
    <c:if test="${user.roleName eq 'ADMIN'}">
        <h3><fmt:message key="title.activity.add"/> </h3>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="add_activity">
            <label for="activityName"></label>
            <input type="text" name="activityName" id="activityName" placeholder="<fmt:message key="activity.enter"/>" required/>
            <input class="btn btn-primary" type="submit" value="<fmt:message key="button.send"/>"/>
        </form>
    </c:if>
    <table class="table table-hover table-striped table-bordered">
        <tr>
            <th><fmt:message key="table.column.name"/> </th>
            <c:if test="${user.roleName eq 'USER'}">
                <th><fmt:message key="table.column.action"/></th>
            </c:if>
        </tr>
        <c:forEach items="${activities}" var="activity">
            <tr>
                <td>${activity.name}</td>
                <c:if test="${user.roleName eq 'USER'}">
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="assign_activity"/>
                                <%--                                <input type="hidden" name="action" value="add"/>--%>
                            <input type="hidden" name="activityId" value="${activity.id}"/>
                            <input type="hidden" name="userId" value="${user.userId}"/>
                            <input type="hidden" name="activityName" value="${activity.name}"/>
                            <input class="btn btn-secondary" type="submit" value="<fmt:message key="table.column.add.to.my.activities"/>"/>
                        </form>
                    </td>
                </c:if>
<%--                <td>--%>
<%--                    <c:choose>--%>
<%--                        <c:when test="${user.roleName eq 'USER' and activity.approved}">--%>
<%--                            <form action="controller" method="post">--%>
<%--                                <input type="hidden" name="command" value="assign_activity"/>--%>
<%--&lt;%&ndash;                                <input type="hidden" name="action" value="add"/>&ndash;%&gt;--%>
<%--                                <input type="hidden" name="activityId" value="${activity.id}"/>--%>
<%--                                <input type="hidden" name="userId" value="${user.userId}"/>--%>
<%--                                <input type="hidden" name="activityName" value="${activity.name}"/>--%>
<%--                                <input type="submit" value="<fmt:message key="table.column.add.to.my.activities"/>"/>--%>
<%--                            </form>--%>
<%--                        </c:when>--%>
<%--&lt;%&ndash;                        <c:otherwise>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <fmt:message key="user.activity.notavailable"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        </c:otherwise>&ndash;%&gt;--%>
<%--                    </c:choose>--%>
<%--                </td>--%>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${not empty activities}">
        <nav>
            <ul class="pagination pagination-lg px-1 py-1">
                <c:if test="${currentPage != 1}">
                    <li class="page-item">
                        <span class="border px-2 py-1">
                            <a href="${pageContext.request.contextPath}/cabinet/activities?command=show_activities&rowsPerPage=${rowsPerPage}&currentPage=${currentPage-1}">
                                <fmt:message key="nav.previous"/>
                            </a>
                        </span>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${numPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item"><span class="border px-2 py-1">${i}</span></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <span class="border px-2 py-1">
                                    <a href="${pageContext.request.contextPath}/cabinet/activities?command=show_activities&rowsPerPage=${rowsPerPage}&currentPage=${i}">${i}</a>
                                </span>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage lt numPages}">
                    <li class="page-item">
                        <span class="border px-2 py-1">
                            <a href="${pageContext.request.contextPath}/cabinet/activities?command=show_activities&rowsPerPage=${rowsPerPage}&currentPage=${currentPage+1}">
                                <fmt:message key="nav.next"/>
                            </a>
                        </span>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:if>
    <form action="${pageContext.request.contextPath}">
        <input class="btn btn-secondary" type="submit" value="<fmt:message key="button.main"/>"/>
    </form>
</body>
</html>
