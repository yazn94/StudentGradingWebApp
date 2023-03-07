<%--
  Created by IntelliJ IDEA.
  User: Yazn
  Date: 3/5/2023
  Time: 2:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" type="text/css" href="style.css">
<head>
    <title>Add Courses</title>
</head>
<body>
<div class="container">
    <h1>Course Data</h1>
    <form action="done-adding-course" method="post">
        <label for="name">Course Name:</label>
        <input type="text" name="name" id="name">
        <br>
        <br>
        <input type="button" class="button" value="Back"
               onclick="location.href=
                   'http://localhost:8080/StudentGradingSystem_Servlets__war_exploded/' +
                    'student-grading-system.jsp'">
        <input type="submit" value="Submit">

    </form>
</div>
</body>
</html>
