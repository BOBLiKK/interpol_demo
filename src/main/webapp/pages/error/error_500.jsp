<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 19.10.2024
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>500 - Internal Server Error</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container text-center mt-5">
    <div class="card shadow-lg">
        <div class="card-header bg-warning text-dark">
            <h1 class="display-4">Error 500</h1>
            <p class="lead">Internal Server Error</p>
        </div>
        <div class="card-body">
            <p class="mb-4">Something went wrong on our side. Please try again later.</p>
            <p class="text-danger"><strong>Details:</strong> ${message}</p>
            <a href="${pageContext.request.contextPath}/controller?command=logout">Return to Home</a>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
