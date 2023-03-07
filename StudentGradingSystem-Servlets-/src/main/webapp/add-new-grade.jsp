<%--
  Created by IntelliJ IDEA.
  User: Yazn
  Date: 3/5/2023
  Time: 1:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="style.css">

<html>
<head>

    <title>Adding a new grade</title>
</head>
<body>
<div class="container">
    <h1>Adding a new grade</h1>
    <form action="done-adding-new-grade" method="post">
        <div class="form-group">
            <label for="studentId">Student Id:</label>
            <input type="number" name="studentId" id="studentId">
        </div>
        <div class="form-group">
            <label for="courseId">Course Id:</label>
            <input type="number" name="courseId" id="courseId">
        </div>
        <div class="form-group">
            <label for="grade">Grade:</label>
            <input type="number" name="grade" id="grade">
        </div>

        <input type="button" class="button" value="Back"
               onclick="location.href=
                           'http://localhost:8080/StudentGradingSystem_Servlets__war_exploded/' +
                            'student-grading-system.jsp'">
        <input type="submit" value="Add">
    </form>
</div>
</body>
</html>
