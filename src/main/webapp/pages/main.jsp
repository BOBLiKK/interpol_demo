<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 19.10.2024
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card text-center shadow">
                <div class="card-header bg-primary text-white">
                    <h3>Welcome</h3>
                </div>
                <div class="card-body">
                    <h5 class="card-title">Hello, ${user}!</h5>
                    <p class="card-text">You are now logged in. Feel free to explore.</p>
                    <a href="controller?command=logout" class="btn btn-danger">Logout</a>
                </div>
                <div class="card-footer text-muted">
                    Thank you for visiting!
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

