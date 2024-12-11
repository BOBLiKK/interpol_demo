<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Interpol Demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to bottom, #f0f0f0, #e0e0e0);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
        }
        .main-container {
            text-align: center;
            max-width: 600px;
            width: 100%;
        }
        .btn-block {
            width: 100%;
            height: auto;
            font-size: 1.2rem;
        }
    </style>
</head>
<body>
<div class="main-container">
    <h1 class="mb-4">Welcome to Interpol Demo</h1>
    <p class="mb-4">Please select an option to proceed:</p>
    <div class="d-grid gap-4">
        <form action="pages/login.jsp" method="GET">
            <button type="submit" class="btn btn-primary btn-block">Log In</button>
        </form>

        <form action="pages/register_user.jsp" method="GET">
            <button type="submit" class="btn btn-secondary btn-block">Register</button>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
