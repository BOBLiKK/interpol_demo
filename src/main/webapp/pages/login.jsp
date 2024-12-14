<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 20.10.2024
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.buttons" var="buttons" />
<fmt:setBundle basename="localization.messages" var="messages" />
<fmt:setBundle basename="localization.titles" var="titles"/>
<fmt:setBundle basename="localization.tables" var="tables"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message bundle="${titles}" key="title.login"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to bottom, #f0f0f0, #e0e0e0);
        }
        .login-container {
            margin-top: 100px;
        }
        .card {
            border: none;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
        }
    </style>
</head>
<body>
<div class="container login-container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card p-4">
                <h1 class="text-center mb-4"><fmt:message bundle="${titles}" key="title.login"/></h1>
                <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="login"/>

                    <div class="mb-3">
                        <label for="login" class="form-label"><fmt:message bundle="${titles}" key="title.login_or_email"/></label>
                        <input type="text" name="login" id="login" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label"><fmt:message bundle="${titles}" key="title.password"/></label>
                        <input type="password" name="password" id="password" class="form-control" required>
                    </div>

                    <c:if test="${not empty message}">
                        <div class="alert alert-danger" role="alert">
                                ${message}
                        </div>
                    </c:if>

                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary"><fmt:message bundle="${buttons}" key="button.login"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
