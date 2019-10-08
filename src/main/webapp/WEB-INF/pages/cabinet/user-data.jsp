<%--@elvariable id="messageKey" type="java.lang.String"--%>
<%--@elvariable id="roles" type="java.util.List"--%>
<%--@elvariable id="errors" type="java.util.Map"--%>
<html>
<head>
    <title>User data</title>
</head>
<body>
    <c:if test="${not empty messageKey}">
        <div class="text-info">${messageKey}</div>
        <c:remove scope="session" var="messageKey"/>
    </c:if>
    <div class="container">
        <form action="${pageContext.request.contextPath}/cabinet/add-user" class="needs-validation" novalidate method="post">
            <input type="hidden" name="command" value="add_user"/>
            <div class="form-row">
                <div class="col-md-4 mb-3">
                    <label for="login">Login</label>
                    <input type="text" class="form-control" id="login" name="login" required>
                    <c:if test="${errors.login}">
                        <div class="text-warning">
                            <fmt:message key="field.notblank"/>
                        </div>
                    </c:if>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                    <c:if test="${errors.password}">
                        <div class="text-warning">
                            <fmt:message key="field.notblank"/>
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="form-row">
                <div class="col-md-4 mb-3">
                    <label for="firstName">First name</label>
                    <input type="text" class="form-control" id="firstName" name="firstName" value="" required>
                    <c:if test="${errors.firstName}">
                        <div class="text-warning">
                            <fmt:message key="field.notblank"/>
                        </div>
                    </c:if>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="lastName">Last name</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" required>
                    <c:if test="${errors.lastName}">
                        <div class="text-warning">
                            <fmt:message key="field.notblank"/>
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="form-row">
                <div class="col-md-4 mb-3">
                    <label for="role">Role</label>
                    <select class="custom-select form-inline" name="roleId" id="role">
                        <c:forEach var="role" items="${roles}">
                            <option value="${role.roleId}"><fmt:message key="${role.roleName}"/></option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <button class="btn btn-primary" type="submit"><fmt:message key="button.save"/></button>
        </form>
        <c:remove var="errors" scope="session"/>
    </div>
    <form action="${pageContext.request.contextPath}">
        <input class="btn btn-secondary" type="submit" value="<fmt:message key="button.main"/>"/>
    </form>

    <script>
        // Example starter JavaScript for disabling form submissions if there are invalid fields
        (function() {
            'use strict';
            window.addEventListener('load', function() {
                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName('needs-validation');
                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function(form) {
                    form.addEventListener('submit', function(event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>
</body>
</html>
