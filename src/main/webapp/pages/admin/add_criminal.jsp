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
    <title><fmt:message bundle="${titles}" key="title.add_criminal"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to bottom, #f0f0f0, #e0e0e0);
            color: #333;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .container {
            margin-top: 50px;
        }
        .form-container {
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        .btn-back {
            margin-top: 20px;
            background-color: #6c757d;
            color: white;
        }
        .btn-back:hover {
            background-color: #5a6268;
        }
    </style>
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
            <label for="citizenship"><fmt:message bundle="${tables}" key="table.citizenship"/></label>
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
<script>
    const countries = [
        "Afghanistan", "Albania", "Algeria", "Andorra", "Angola",
        "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan",
        "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus",
        "Belgium", "Belize", "Benin", "Bhutan", "Bolivia",
        "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso",
        "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde",
        "Central African Republic", "Chad", "Chile", "China",
        "Colombia", "Comoros", "Congo", "Costa Rica", "Croatia",
        "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti",
        "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador",
        "Estonia", "Eswatini", "Ethiopia", "Fiji", "Finland",
        "France", "Gabon", "Gambia", "Georgia", "Germany",
        "Ghana", "Greece", "Grenada", "Guatemala", "Guinea",
        "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary",
        "Iceland", "India", "Indonesia", "Iran", "Iraq",
        "Ireland", "Israel", "Italy", "Jamaica", "Japan",
        "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait",
        "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho",
        "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg",
        "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali",
        "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico",
        "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro",
        "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru",
        "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger",
        "Nigeria", "North Korea", "North Macedonia", "Norway", "Oman",
        "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay",
        "Peru", "Philippines", "Poland", "Portugal", "Qatar",
        "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis",
        "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa",
        "San Marino", "Sao Tome and Principe", "Saudi Arabia",
        "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore",
        "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa",
        "South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan",
        "Suriname", "Sweden", "Switzerland", "Syria", "Taiwan",
        "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo",
        "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey",
        "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates",
        "United Kingdom", "United States", "Uruguay", "Uzbekistan",
        "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen",
        "Zambia", "Zimbabwe"
    ];

    document.addEventListener('DOMContentLoaded', function() {
        const select = document.createElement('select');
        select.name = "citizenship";
        select.id = "citizenship";
        select.className = "form-select";

        countries.forEach(country => {
            const option = document.createElement('option');
            option.value = country;
            option.textContent = country;
            select.appendChild(option);
        });

        const inputField = document.getElementById('citizenship');
        inputField.replaceWith(select);
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
