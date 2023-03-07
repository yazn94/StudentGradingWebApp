<%--
  Created by IntelliJ IDEA.
  User: Yazn
  Date: 3/5/2023
  Time: 2:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" type="text/css" href="style.css">
<head>
    <title>Student Registration</title>
</head>
<body>
<div class="container">
    <h1>Student Information</h1>
    <form action="done-adding-new-student" method="post">
        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" name="firstName" id="firstName">
        </div>
        <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input type="text" name="lastName" id="lastName">
        </div>
        <div class="form-group">
            <label for="age">Age:</label>
            <input type="text" name="age" id="age">
        </div>
        <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="number" name="phone" id="phone">
        </div>
        <div class="form-group">
            <input type="button" value="Back"
                   onclick="location.href=
                       'http://localhost:8080/StudentGradingSystem_Servlets__war_exploded/' +
                        'student-grading-system.jsp'">
            <input type="submit" value="Submit">
        </div>
    </form>
</div>
</body>
</html>
