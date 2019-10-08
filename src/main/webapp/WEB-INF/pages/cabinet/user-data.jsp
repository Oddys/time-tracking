<%--@elvariable id="messageKey" type="java.lang.String"--%>
<html>
<head>
    <title>User data</title>
</head>
<body>
    <c:if test="${not empty messageKey}">
        ${messageKey}
        <c:remove scope="session" var="messageKey"/>
    </c:if>
    <div class="container">
        <form action="controller" class="needs-validation" novalidate method="post">
            <input type="hidden" name="command" value="add_user"/>
            <div class="form-row">
                <div class="col-md-4 mb-3">
                    <label for="login">Login</label>
                    <input type="text" class="form-control" id="login" name="login" required>
                    <%--                <div class="invalid-feedback">--%>
                    <%--                    Please, provide a valid input.--%>
                    <%--                </div>--%>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                    <%--                <div class="invalid-feedback">--%>
                    <%--                    Please, provide a valid input.--%>
                    <%--                </div>--%>
                </div>
            </div>
            <div class="form-row">
                <div class="col-md-4 mb-3">
                    <label for="firstName">First name</label>
                    <input type="text" class="form-control" id="firstName" name="firstName" value="" required>
                    <%--                <div class="invalid-feedback">--%>
                    <%--                    Please, provide a valid input.--%>
                    <%--                </div>--%>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="lastName">Last name</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" required>
                    <%--                <div class="invalid-feedback">--%>
                    <%--                    Please, provide a valid input.--%>
                    <%--                </div>--%>
                </div>
            </div>
            <div class="form-row">
                <div class="col-md-4 mb-3">
                    <label for="role">Role</label>
                    <!-- FIXME Display all roles from DB -->
                    <select class="custom-select form-inline" name="role" id="role">
                        <option value="User">User</option>
                        <option value="Admin">Admin</option>
                    </select>
                </div>
            </div>
            <button class="btn btn-primary" type="submit"><fmt:message key="button.save"/></button>
        </form>
    </div>
<%--    <form action="controller" method="post">--%>
<%--        <input type="hidden" name="command" value="add_user"/>--%>
<%--        <label for="login">Login</label>--%>
<%--        <input type="text" name="login" value="" id="login" required/>--%>
<%--        <label for="password">Password</label>--%>
<%--        <input type="password" name="password" value="" id="password" required/>--%>
<%--        <label for="firstName">First name</label>--%>
<%--        <input type="text" name="firstName" value="" id="firstName" required/>--%>
<%--        <label for="lastName">Last name</label>--%>
<%--        <input type="text" name="lastName" value="" id="lastName" required/>--%>
<%--        <label for="role">Role</label>--%>
<%--        <select name="role" id="role">--%>
<%--            <option value="User">User</option>--%>
<%--            <option value="Admin">Admin</option>--%>
<%--        </select>--%>
<%--        <input type="submit" value="<fmt:message key="button.save"/>"/>--%>
<%--    </form>--%>
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
