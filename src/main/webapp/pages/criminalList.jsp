<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 27.10.2024
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Criminal List</title>
</head>
<body>
<h2>List of Criminals</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Date of Birth</th>
        <th>Citizenship</th>
        <th>Description</th>
        <th>Reward</th>
        <th>Arrested</th>
    </tr>
    <c:forEach var="criminal" items="${criminals}">
        <tr>
            <td>${criminal.id}</td>
            <td>${criminal.name}</td>
            <td>${criminal.surname}</td>
            <td>${criminal.dateOfBirth}</td>
            <td>${criminal.citizenship}</td>
            <td>${criminal.description}</td>
            <td>${criminal.reward}</td>
            <td>${criminal.arrested}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
