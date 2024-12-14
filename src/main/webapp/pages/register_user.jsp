<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 07.11.2024
  Time: 11:28
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
    <title><fmt:message bundle="${titles}" key="title.registration"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to bottom, #f0f0f0, #e0e0e0);
        }
        .register-container {
            margin-top: 50px;
        }
        .card {
            border: none;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
        }
    </style>
    <script>
        function toggleForm(type) {
            document.getElementById('standard-form').style.display = type === 'standard' ? 'block' : 'none';
            document.getElementById('email-form').style.display = type === 'email' ? 'block' : 'none';
        }
    </script>
</head>
<body>
<div class="container register-container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header bg-primary text-white text-center">
                    <h3><fmt:message bundle="${titles}" key="title.registration"/></h3>
                </div>
                <div class="card-body">

                    <div class="mb-3">
                        <label class="form-label"><fmt:message bundle="${titles}" key="title.choose_registration_type"/></label>
                        <div>
                            <input type="radio" id="standard-option" name="registrationType" value="standard" onclick="toggleForm('standard')" checked>
                            <label for="standard-option"><fmt:message bundle="${buttons}"  key="button.standard"/></label>

                            <input type="radio" id="email-option" name="registrationType" value="email" onclick="toggleForm('email')">
                            <label for="email-option"><fmt:message bundle="${buttons}" key="button.email"/></label>
                        </div>
                    </div>

                    <!-- Standard Registration Form -->
                    <form id="standard-form" name="registerForm" method="POST" action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="register_user"/>

                        <div class="mb-3">
                            <label for="login" class="form-label"><fmt:message bundle="${titles}" key="title.login"/></label>
                            <input type="text" name="login" id="login" class="form-control" value="${param.login}">
                            <span class="text-danger">
                                <c:if test="${not empty validationErrors['login']}">
                                    ${validationErrors['login']}
                                </c:if>
                            </span>
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label"><fmt:message bundle="${titles}" key="title.password"/></label>
                            <input type="password" name="password" id="password" class="form-control">
                            <span class="text-danger">
                                <c:if test="${not empty validationErrors['password']}">
                                    ${validationErrors['password']}
                                </c:if>
                            </span>
                        </div>

                        <c:if test="${not empty message}">
                            <div class="alert alert-danger" role="alert">
                                    ${message}
                            </div>
                        </c:if>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary"><fmt:message bundle="${buttons}" key="button.register"/></button>
                        </div>
                    </form>

                    <!-- Email Registration Form -->
                    <form id="email-form" name="registerEmailForm" method="POST" action="${pageContext.request.contextPath}/controller" style="display: none;">
                        <input type="hidden" name="command" value="register_user_with_email"/>

                        <div class="mb-3">
                            <label for="email" class="form-label"><fmt:message bundle="${titles}" key="title.email"/></label>
                            <input type="email" name="email" id="email" class="form-control" value="${param.email}">
                            <span class="text-danger">
                                <c:if test="${not empty validationErrors['email']}">
                                    ${validationErrors['email']}
                                </c:if>
                            </span>
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label"><fmt:message bundle="${titles}" key="title.password"/></label>
                            <input type="password" name="password" id="password" class="form-control">
                            <span class="text-danger">
                                <c:if test="${not empty validationErrors['password']}">
                                    ${validationErrors['password']}
                                </c:if>
                            </span>
                        </div>

                        <c:if test="${not empty message}">
                            <div class="alert alert-danger" role="alert">
                                    ${message}
                            </div>
                        </c:if>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary"><fmt:message bundle="${buttons}" key="button.register"/></button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
