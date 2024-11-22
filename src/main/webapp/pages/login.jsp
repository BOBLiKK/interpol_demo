<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 20.10.2024
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h1 class="text-center mb-4">Login</h1>
            <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller" class="border p-4 rounded shadow">
                <input type="hidden" name="command" value="login"/>
                <div class="mb-3">
                    <label for="login" class="form-label">Login</label>
                    <input type="text" name="login" id="login" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" name="password" id="password" class="form-control" required>
                </div>
                <% if (request.getAttribute("message") != null) { %>
                <div class="alert alert-danger" role="alert">
                    ${message}
                </div>
                <% } %>

                <div class="text-center">
                    <button type="submit" class="btn btn-primary w-100">Log in</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
