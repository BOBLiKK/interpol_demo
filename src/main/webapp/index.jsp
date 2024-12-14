<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <title><fmt:message bundle="${titles}" key="title.interpol_demo"/></title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to bottom, #f0f0f0, #e0e0e0);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
        }
        .main-container {
            text-align: center;
            max-width: 600px;
            width: 100%;
        }
        .btn-block {
            width: 100%;
            height: auto;
            font-size: 1.2rem;
        }
    </style>
</head>
<body>
<div class="main-container">
    <h1 class="mb-4"><fmt:message bundle="${messages}" key="message.welcome_index"/></h1>

    <!-- Форма для выбора языка -->
    <form method="GET" action="index.jsp" class="mb-4">
        <select name="lang" onchange="this.form.submit()" class="form-select">
            <option value="en" ${sessionScope.locale == 'en' ? 'selected' : ''}>English</option>
            <option value="ru" ${sessionScope.locale == 'ru' ? 'selected' : ''}>Русский</option>
        </select>
    </form>



    <div class="d-grid gap-4">
        <form action="pages/login.jsp" method="GET">
            <button type="submit" class="btn btn-primary btn-block"><fmt:message bundle="${buttons}"  key="button.login"/></button>
        </form>

        <form action="pages/register_user.jsp" method="GET">
            <button type="submit" class="btn btn-secondary btn-block"><fmt:message bundle="${buttons}"  key="button.register"/></button>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
