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
<div class="btn-container">
    <button onclick="history.back()" class="btn-back"><fmt:message bundle="${buttons}" key="button.back"/></button>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
