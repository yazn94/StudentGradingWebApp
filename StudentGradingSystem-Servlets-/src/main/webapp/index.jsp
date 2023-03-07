<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="style.css">

<head>
    <title>Welcome Page - Servlets</title>
    <style>
        body {
            background-image: url("Images/welcome-page.jpg");
            background-repeat: no-repeat;
            background-size: cover;
            background-position: center top 30%;
        }
    </style>
</head>
<body>
<div class="container" style="margin-top: 100px">
    <h1><%= "Welcome to the student grading system" %>
    </h1>
    <br/>
    <input type="button" class="button" value="Register!"
           onclick="location.href=
                   'http://localhost:8080/StudentGradingSystem_Servlets__war_exploded/' +
                    'registration.jsp'">
</div>
</body>
</html>