<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 29.11.2024
  Time: 19:39
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
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message bundle="${titles}" key="title.admin_dashboard"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to bottom, #f0f0f0, #e0e0e0);
            color: #333;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .navbar {
            background-color: #0f3460;
        }
        .navbar .navbar-brand {
            font-weight: bold;
            color: #fff;
        }
        .navbar .nav-link {
            color: #fff;
        }
        .navbar .nav-link:hover {
            color: #66fcf1;
        }
        .main-container {
            padding-top: 50px;
            text-align: center;
        }
        .logout-button {
            position: fixed;
            bottom: 20px;
            right: 20px;
            background-color: #c0392b;
            color: white;
            border: none;
            border-radius: 50px;
            padding: 10px 20px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
        }
        .logout-button:hover {
            background-color: #a93226;
        }
        .alert {
            display: none;
            position: fixed;
            top: 20px;
            right: 20px;
            z-index: 1050;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
        }
        .alert.show {
            display: block;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container-fluid">
        <span class="navbar-brand"><fmt:message bundle="${messages}" key="message.welcome"/>: ${user}</span>
        <div class="navbar-nav ms-auto">
            <a href="${pageContext.request.contextPath}/controller?command=add_criminal_form&admin=true" class="nav-link"><fmt:message bundle="${buttons}" key="button.add_criminal"/></a>
            <a href="${pageContext.request.contextPath}/controller?command=view_criminal_list&admin=true" class="nav-link"><fmt:message bundle="${buttons}" key="button.view_criminals"/></a>
            <a href="${pageContext.request.contextPath}/controller?command=view_request_list" class="nav-link"><fmt:message bundle="${buttons}" key="button.view_requests"/></a>
        </div>
    </div>
</nav>

<div class="container main-container">
    <h1 class="mb-4"><fmt:message bundle="${titles}" key="header.admin_dashboard"/></h1>
    <p><fmt:message bundle="${messages}" key="message.admin_navigation"/></p>
</div>

<div id="errorAlert" class="alert alert-danger alert-dismissible fade" role="alert">
    ${message}
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>


<form method="GET" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="admin" value="true"/>
    <input type="hidden" name="command" value="logout"/>
    <button type="submit" class="logout-button"><fmt:message bundle="${buttons}" key="button.logout"/></button>
</form>

<script>
    document.addEventListener('DOMContentLoaded', () => {
        const alert = document.getElementById('errorAlert');
        if (alert && alert.textContent.trim().length > 0) {
            alert.classList.add('show');
        }
    })
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
