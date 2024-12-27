<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 03.12.2024
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://interpoldemo/custom" prefix="custom" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 0;
        }
        h1 {
            text-align: center;
            margin: 20px 0;
            color: #222;
        }
        form {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
        }
        input, textarea, button {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input:focus, textarea:focus {
            outline: none;
            border-color: #4CAF50;
            box-shadow: 0 0 5px rgba(76, 175, 80, 0.4);
        }
        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            padding: 10px 15px;
            font-size: 16px;
            border-radius: 4px;
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
        .btn-container {
            text-align: center;
        }
        @media (max-width: 600px) {
            form {
                padding: 15px;
            }
            button {
                font-size: 14px;
            }
        }
    </style>
</head>
<body>
<h1><fmt:message bundle="${titles}" key="title.edit_criminal"/></h1>
<form method="POST" action="${pageContext.request.contextPath}/controller" class="border p-4 rounded shadow" enctype="multipart/form-data">
    <input type="hidden" name="command" value="edit_criminal" />
    <input type="hidden" name="id" value="${criminal.id}" />

    <label for="name"><fmt:message bundle="${tables}" key="table.name"/></label>
    <input type="text" id="name" name="name" value="${criminal.name}" required />

    <label for="surname"><fmt:message bundle="${tables}" key="table.surname"/></label>
    <input type="text" id="surname" name="surname" value="${criminal.surname}" required />

    <label for="dateOfBirth"><fmt:message bundle="${tables}" key="table.date_of_birth"/></label>
    <input type="date" id="dateOfBirth" name="dateOfBirth" value="${criminal.dateOfBirth}" required />

    <label for="citizenship"><fmt:message bundle="${tables}" key="table.citizenship"/></label>
    <custom:countrySelect/>

    <label for="description"><fmt:message bundle="${tables}" key="table.description"/></label>
    <textarea id="description" name="description" rows="5">${criminal.description}</textarea>

    <label for="reward"><fmt:message bundle="${tables}" key="table.reward"/></label>
    <input type="number" step="0.01" id="reward" name="reward" value="${criminal.reward}" required />

    <label for="image"><fmt:message bundle="${tables}" key="table.image"/></label>
    <input type="file" id="image" name="image" accept="image/*" />
    <c:if test="${not empty criminal.imageBase64}">
        <img src="data:image/png;base64,${fn:escapeXml(criminal.imageBase64)}" alt="Criminal Image" class="image-preview" />
    </c:if>

    <button type="submit"><fmt:message bundle="${buttons}" key="button.save_changes"/></button>
    <div class="btn-container">
        <button onclick="history.back()" class="btn-back"><fmt:message bundle="${buttons}" key="button.back"/></button>
    </div>
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
