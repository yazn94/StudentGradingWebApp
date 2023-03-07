<%--
Created by IntelliJ IDEA.
User: Yazn
Date: 3/5/2023
Time: 2:38 PM
To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.studentgradingsystemservlets.Model.Grade" %>
<%@ page import="com.example.studentgradingsystemservlets.Database.DAO" %>
<%@ page import="com.example.studentgradingsystemservlets.Model.Student" %>

<html>
<link rel="stylesheet" type="text/css" href="style.css">

<head>
    <title>Finished Courses For Student</title>
</head>

<body>
<div class="container">
    <h1>Finished Courses For Student</h1>

    <form action="finished-courses-for-student.jsp" method="get">
        <label for="studentId">Enter Student ID:</label>
        <input type="text" id="studentId" name="studentId" required>
        <input type="submit" value="Submit">
    </form>
    <%
        if (request.getParameter("studentId") != null) {
            DAO dao = new DAO();
            List<Grade> grades = dao.getFinishedCoursesForStudent(Long.parseLong(request.getParameter("studentId")));
            Student student = dao.getStudentById(Long.parseLong(request.getParameter("studentId")));
    %>
    <br>
    <br>
    <br>

    <h2>Finished courses for <%= student.getFirstName() + " " + student.getLastName()%>
    </h2>
    <br>
    <br>

    <table style="width: 100px !important;">
        <thead>
        <tr>
            <th>Course_Name</th>
            <th>Grade</th>
        </tr>
        </thead>
        <tbody>
        <% for (Grade grade : grades) { %>
        <tr>
            <td><%= grade.getCourseName() %>
            </td>
            <td><%= grade.getGrade() %>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <br>
    <br>
    <br>
    <input type="button" class="button" value="Back"
           onclick="location.href=
                   'http://localhost:8080/StudentGradingSystem_Servlets__war_exploded/' +
                    'student-grading-system.jsp'">

    <%
            dao.close();
        }
    %>
</div>

</body>
</html>
