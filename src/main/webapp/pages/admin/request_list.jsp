<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 23.12.2024
  Time: 16:11
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
  <title><fmt:message bundle="${titles}" key="title.request_list"/></title>
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
    .status-approved {
        background-color: #08ee3f !important;/* Красный для rejected */
    }
    .status-rejected {
        background-color: #f1091e !important; /* Зеленый для approved */
    }
    img.thumbnail {
        max-width: 100px;
        max-height: 100px;
        width: auto;
        height: auto;
    }
  </style>
</head>
<body>
<c:if test="${message}">
  <div style="color: #333; background-color: #f9f9f9; border: 1px solid #ccc; padding: 10px; margin-bottom: 20px;">
      ${message}
  </div>
</c:if>

<!-- JavaScript для изменения цвета строки -->
<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Находим все строки таблицы
        const rows = document.querySelectorAll("tbody tr");
        rows.forEach(row => {
            const statusCell = row.querySelector("td:nth-child(9)"); // 9-й столбец для статуса
            if (statusCell) {
                const status = statusCell.textContent.trim();
                if (status === "REJECTED") {
                    row.classList.add("status-rejected"); // Добавляем класс для красного цвета
                } else if (status === "APPROVED") {
                    row.classList.add("status-approved"); // Добавляем класс для зеленого цвета
                }
            }
        });
    });
</script>

<!-- Конец JavaScript -->
<table>
  <thead>
  <tr>
    <th><fmt:message bundle="${tables}" key="table.user_id"/></th>
    <th><fmt:message bundle="${tables}" key="table.name"/></th>
    <th><fmt:message bundle="${tables}" key="table.surname"/></th>
    <th><fmt:message bundle="${tables}" key="table.date_of_birth"/></th>
    <th><fmt:message bundle="${tables}" key="table.citizenship"/></th>
    <th><fmt:message bundle="${tables}" key="table.description"/></th>
    <th><fmt:message bundle="${tables}" key="table.reward"/></th>
    <th><fmt:message bundle="${tables}" key="table.image"/></th>
    <th><fmt:message bundle="${tables}" key="table.comment"/></th>
    <th><fmt:message bundle="${tables}" key="table.status"/></th>
    <th><fmt:message bundle="${tables}" key="table.actions"/></th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="request" items="${requests}">
      <tr>
          <td><c:out value="${request.userId}" /></td>
          <td><c:out value="${request.criminal.name}" /></td>
          <td><c:out value="${request.criminal.surname}" /></td>
          <td><c:out value="${request.criminal.dateOfBirth}" /></td>
          <td><c:out value="${request.criminal.citizenship}" /></td>
          <td><c:out value="${request.criminal.description}" /></td>
          <td><c:out value="${request.criminal.reward}" /></td>
          <td>
              <c:if test="${not empty request.criminal.imageBase64}">
                  <img src="data:image/png;base64,${fn:escapeXml(request.criminal.imageBase64)}" alt="Criminal Image" class="thumbnail" />
              </c:if>
          </td>
          <td><c:out value="${request.comment}" /></td>
          <td><c:out value="${request.status}" /></td>
          <td>
              <c:choose>
                  <c:when test="${request.status == 'PENDING'}">
                      <form action="controller" method="post" style="display:inline-block;">
                          <input type="hidden" name="command" value="approve_request" />
                          <input type="hidden" name="id" value="${request.requestId}" />
                          <button type="submit" class="btn-approve"><fmt:message bundle="${buttons}" key="button.approve"/></button>
                      </form>
                      <form action="controller" method="post" style="display:inline-block;">
                          <input type="hidden" name="command" value="decline_request" />
                          <input type="hidden" name="id" value="${request.requestId}" />
                          <button type="submit" class="btn-reject"><fmt:message bundle="${buttons}" key="button.reject"/></button>
                      </form>
                  </c:when>
                  <c:otherwise>
                      <span class="disabled-message"><fmt:message bundle="${messages}" key="message.status_locked"/></span>
                  </c:otherwise>
              </c:choose>
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