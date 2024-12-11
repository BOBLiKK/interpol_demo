<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 11.12.2024
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Criminal List</title>
  <style>
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }
    th, td {
      border: 1px solid #ddd;
      padding: 8px;
      text-align: left;
    }
    th {
      background-color: #f2f2f2;
      font-weight: bold;
    }
    tr:nth-child(even) {
      background-color: #f9f9f9;
    }
    tr:hover {
      background-color: #f1f1f1;
    }
  </style>
</head>
<body>
<h1>Criminal List</h1>
<table>
  <thead>
  <tr>
    <th>Name</th>
    <th>Surname</th>
    <th>Date of Birth</th>
    <th>Citizenship</th>
    <th>Description</th>
    <th>Reward</th>
    <th>Arrested</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="criminal" items="${criminals}">
    <tr>
      <td><c:out value="${criminal.name}" /></td>
      <td><c:out value="${criminal.surname}" /></td>
      <td><c:out value="${criminal.dateOfBirth}" /></td>
      <td><c:out value="${criminal.citizenship}" /></td>
      <td><c:out value="${criminal.description}" /></td>
      <td><c:out value="${criminal.reward}" /></td>
      <td> <c:choose>
        <c:when test="${criminal.arrested}">
          Yes
        </c:when>
        <c:otherwise>
          No
        </c:otherwise>
      </c:choose>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<div class="btn-container">
  <button onclick="history.back()" class="btn-back">Back</button>
</div>
</body>
</html>

