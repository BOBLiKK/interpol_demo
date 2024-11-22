<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Interpol-Demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5 text-center">
    <h1 class="mb-4">Welcome to Interpol-Demo</h1>
    <p class="mb-4">Please select an option to proceed:</p>

    <div class="d-grid gap-3 col-md-4 mx-auto">
        <form action="pages/login.jsp" method="GET">
            <button type="submit" class="btn btn-primary btn-lg">Log In</button>
        </form>

        <form action="pages/registeruser.jsp" method="GET">
            <button type="submit" class="btn btn-secondary btn-lg">Register</button>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</body>
</html>