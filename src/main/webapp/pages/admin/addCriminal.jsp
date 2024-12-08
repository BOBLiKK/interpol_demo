<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 29.11.2024
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Criminal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Add Criminal</h1>
    <form method="POST" action="${pageContext.request.contextPath}/controller" class="border p-4 rounded shadow">
        <input type="hidden" name="command" value="add_criminal"/>

        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" name="name" id="name" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="surname" class="form-label">Surname</label>
            <input type="text" name="surname" id="surname" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Date of Birth</label>
            <div class="d-flex">
                <select name="day" class="form-select me-2" required>
                    <c:forEach var="i" begin="1" end="31">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>

                <!-- Months -->
                <select name="month" class="form-select me-2" required>
                    <c:forEach var="i" begin="1" end="12">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>

                <!-- Years -->
                <select name="year" class="form-select" required>
                    <c:forEach var="i" begin="1900" end="2024">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="mb-3">
            <label for="citizenship" class="form-label">Citizenship</label>
            <input type="text" name="citizenship" id="citizenship" class="form-control">
        </div>


        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea name="description" id="description" class="form-control"></textarea>
        </div>

        <div class="mb-3">
            <label for="reward" class="form-label">Reward</label>
            <input type="number" step="0.01" name="reward" id="reward" class="form-control">
        </div>

        <div class="text-center">
            <button type="submit" class="btn btn-success w-100">Submit</button>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
