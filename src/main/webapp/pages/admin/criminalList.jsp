<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 30.11.2024
  Time: 19:30
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
    <th>ID</th>
    <th>Name</th>
    <th>Surname</th>
    <th>Date of Birth</th>
    <th>Citizenship</th>
    <th>Description</th>
    <th>Reward</th>
    <th>Arrested</th>
    <th>Actions</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="criminal" items="${criminals}">
    <tr>
      <td><c:out value="${criminal.id}" /></td>
      <td><c:out value="${criminal.name}" /></td>
      <td><c:out value="${criminal.surname}" /></td>
      <td><c:out value="${criminal.dateOfBirth}" /></td>
      <td><c:out value="${criminal.citizenship}" /></td>
      <td><c:out value="${criminal.description}" /></td>
      <td><c:out value="${criminal.reward}" /></td>
      <td><c:out value="${criminal.arrested}" /></td>
      <td>
        <form action="controller" method="post" style="display:inline-block;">
          <input type="hidden" name="command" value="edit_criminal_form" />
          <input type="hidden" name="id" value="${criminal.id}" />
          <button type="submit" class="btn-edit">Edit</button>
        </form>
        <form action="controller" method="post" style="display:inline-block;">
          <input type="hidden" name="command" value="delete_criminal" />
          <input type="hidden" name="id" value="${criminal.id}" />
          <button type="submit" class="btn-delete" onclick="return confirm('Are you sure you want to delete this criminal?');">Delete</button>
        </form>
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
