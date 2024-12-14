<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 30.11.2024
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.buttons" var="buttons" />
<fmt:setBundle basename="localization.messages" var="messages" />
<fmt:setBundle basename="localization.titles" var="titles"/>
<fmt:setBundle basename="localization.tables" var="tables"/>

<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title><fmt:message bundle="${titles}" key="title.criminal_list"/></title>
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
<title><fmt:message bundle="${titles}" key="title.criminal_list"/></title>
<table>
  <thead>
  <tr>
    <th><fmt:message bundle="${tables}" key="table.name"/></th>
    <th><fmt:message bundle="${tables}" key="table.surname"/></th>
    <th><fmt:message bundle="${tables}" key="table.date_of_birth"/></th>
    <th><fmt:message bundle="${tables}" key="table.citizenship"/></th>
    <th><fmt:message bundle="${tables}" key="table.description"/></th>
    <th><fmt:message bundle="${tables}" key="table.reward"/></th>
    <th><fmt:message bundle="${tables}" key="table.arrested"/></th>
    <th><fmt:message bundle="${tables}" key="table.actions"/></th>
  </tr>
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
          <fmt:message bundle="${messages}" key="message.yes"/>
        </c:when>
        <c:otherwise>
          <fmt:message bundle="${messages}" key="message.no"/>
        </c:otherwise>
      </c:choose>
      </td>
      <td>
        <form action="controller" method="post" style="display:inline-block;">
          <input type="hidden" name="command" value="edit_criminal_form" />
          <input type="hidden" name="id" value="${criminal.id}" />
          <button type="submit" class="btn-edit"><fmt:message bundle="${buttons}" key="button.edit"/></button>
        </form>
        <form action="controller" method="post" style="display:inline-block;">
          <input type="hidden" name="command" value="delete_criminal" />
          <input type="hidden" name="id" value="${criminal.id}" />
          <button type="submit" class="btn-delete" onclick="return confirm('Are you sure you want to delete this criminal?');"><fmt:message bundle="${buttons}" key="button.delete"/></button>
        </form>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<div class="btn-container">
  <button onclick="history.back()" class="btn-back"><fmt:message bundle="${buttons}" key="button.back"/></button>
</div>
</body>
</html>
