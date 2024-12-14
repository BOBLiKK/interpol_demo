<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 03.12.2024
  Time: 20:26
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
    <title><fmt:message bundle="${titles}" key="title.edit_criminal"/></title>
    <style>
        form {
            max-width: 600px;
            margin: 0 auto;
        }
        label {
            display: block;
            margin: 10px 0 5px;
        }
        input, textarea, button {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            box-sizing: border-box;
        }
        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        .btn-back {
            background-color: #f44336;
        }
        .btn-back:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
<h1><fmt:message bundle="${titles}" key="title.edit_criminal"/></h1>
<form action="controller" method="post">
    <input type="hidden" name="command" value="edit_criminal" />
    <input type="hidden" name="id" value="${criminal.id}" />

    <label for="name"><fmt:message bundle="${tables}" key="table.name"/></label>
    <input type="text" id="name" name="name" value="${criminal.name}" required />

    <label for="surname"><fmt:message bundle="${tables}" key="table.surname"/></label>
    <input type="text" id="surname" name="surname" value="${criminal.surname}" required />

    <label for="dateOfBirth"><fmt:message bundle="${tables}" key="table.date_of_birth"/></label>
    <input type="date" id="dateOfBirth" name="dateOfBirth" value="${criminal.dateOfBirth}" required />

    <label for="citizenship"><fmt:message bundle="${tables}" key="table.citizenship"/></label>
    <input type="text" id="citizenship" name="citizenship" value="${criminal.citizenship}" required />

    <label for="description"><fmt:message bundle="${tables}" key="table.description"/></label>
    <textarea id="description" name="description" rows="5">${criminal.description}</textarea>

    <label for="reward"><fmt:message bundle="${tables}" key="table.reward"/></label>
    <input type="number" step="0.01" id="reward" name="reward" value="${criminal.reward}" required />

    <button type="submit"><fmt:message bundle="${buttons}" key="button.save_changes"/></button>
    <div class="btn-container">
        <button onclick="history.back()" class="btn-back"><fmt:message bundle="${buttons}" key="button.back"/></button>
    </div>
</form>
</body>

</html>
