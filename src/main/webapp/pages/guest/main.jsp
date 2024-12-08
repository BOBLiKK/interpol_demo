<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 19.10.2024
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to bottom, #f0f0f0, #e0e0e0);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .main-container {
            max-width: 600px;
            width: 100%;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }
        .header {
            background-color: #0d6efd;
            color: #fff;
            padding: 20px;
            text-align: center;
        }
        .body {
            padding: 30px;
            text-align: center;
        }
        .footer {
            background-color: #f8f9fa;
            text-align: center;
            padding: 15px;
            font-size: 0.9rem;
            color: #6c757d;
        }
    </style>
</head>
<body>
<div class="main-container">
    <div class="header">
        <h1>Welcome</h1>
    </div>
    <div class="body">
        <h5>Hello, ${user}!</h5>
        <p>You are now logged in. Feel free to explore.</p>
        <a href="controller?command=logout" class="btn btn-danger btn-lg mt-3">Logout</a>
    </div>
    <div class="footer">
        Thank you for visiting!
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
