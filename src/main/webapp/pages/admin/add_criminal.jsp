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
    <title><fmt:message bundle="${titles}" key="title.add_criminal"/></title
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1><fmt:message bundle="${titles}" key="title.add_criminal"/></h1>
    <form method="POST" action="${pageContext.request.contextPath}/controller" class="border p-4 rounded shadow">
        <input type="hidden" name="command" value="add_criminal"/>

        <div class="mb-3">
            <label for="name"><fmt:message bundle="${tables}" key="table.name"/></label>
            <input type="text" name="name" id="name" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="name"><fmt:message bundle="${tables}" key="table.surname"/></label>
            <input type="text" name="surname" id="surname" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="name"><fmt:message bundle="${tables}" key="table.date_of_birth"/></label>
            <div class="d-flex">
                <!-- Days -->
                <select name="day" id="day" class="form-select me-2" required>
                    <c:forEach var="i" begin="1" end="31">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>

                <!-- Months -->
                <select name="month" id="month" class="form-select me-2" required>
                    <c:forEach var="i" begin="1" end="12">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>

                <!-- Years -->
                <select name="year" id="year" class="form-select" required>
                    <c:forEach var="i" begin="1900" end="2024">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const daySelect = document.getElementById('day');
                const monthSelect = document.getElementById('month');
                const yearSelect = document.getElementById('year');

                // Обновление количества дней в зависимости от месяца и года
                function updateDays() {
                    const month = parseInt(monthSelect.value, 10);
                    const year = parseInt(yearSelect.value, 10);

                    // Определяем количество дней в месяце
                    const daysInMonth = new Date(year, month, 0).getDate();

                    // Сохраняем выбранный день
                    const selectedDay = parseInt(daySelect.value, 10);

                    // Очищаем текущие опции
                    daySelect.innerHTML = '';

                    // Добавляем нужное количество дней
                    for (let i = 1; i <= daysInMonth; i++) {
                        const option = document.createElement('option');
                        option.value = i;
                        option.textContent = i;
                        daySelect.appendChild(option);
                    }

                    // Восстанавливаем выбранный день, если он допустим
                    if (selectedDay <= daysInMonth) {
                        daySelect.value = selectedDay;
                    }
                }

                // Слушатели событий для изменения месяца и года
                monthSelect.addEventListener('change', updateDays);
                yearSelect.addEventListener('change', updateDays);

                // Инициализация при загрузке
                updateDays();
            });
        </script>

        <div class="mb-3">
            <label for="name"><fmt:message bundle="${tables}" key="table.citizenship"/></label>
            <input type="text" name="citizenship" id="citizenship" class="form-control">
        </div>

        <div class="mb-3">
            <label for="name"><fmt:message bundle="${tables}" key="table.description"/></label>
            <textarea name="description" id="description" class="form-control"></textarea>
        </div>

        <div class="mb-3">
            <label for="name"><fmt:message bundle="${tables}" key="table.reward"/></label>
            <input type="number" step="0.01" name="reward" id="reward" class="form-control">
        </div>

        <div class="text-center">
            <button type="submit"><fmt:message bundle="${buttons}" key="button.submit"/></button>
        </div>
    </form>
    <button onclick="history.back()" class="btn-back"><fmt:message bundle="${buttons}" key="button.back"/></button>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
