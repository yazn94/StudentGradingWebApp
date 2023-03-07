<%--
  Created by IntelliJ IDEA.
  User: Yazn
  Date: 3/5/2023
  Time: 3:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.studentgradingsystemservlets.Model.Grade" %>
<%@ page import="com.example.studentgradingsystemservlets.Database.DAO" %>
<%@ page import="com.example.studentgradingsystemservlets.Model.Course" %>

<html>
<link rel="stylesheet" type="text/css" href="style.css">

<head>
    <title>Students in Course</title>
</head>

<body>
<div class="container">
    <h1>Students in Course</h1>
    <form action="students-in-course.jsp" method="get">
        <div class="form-group">
            <label for="courseId">Enter Course ID:</label>
            <input type="text" id="courseId" name="courseId" required>
        </div>
        <br>
        <input type="submit" value="Submit">
    </form>
    <br>
    <br>
    <%
        if (request.getParameter("courseId") != null) {

            DAO dao = new DAO();
            List<Grade> grades = dao.getStudentsInCourse(Long.parseLong(request.getParameter("courseId")));
            Course course = dao.getCourseById(Long.parseLong(request.getParameter("courseId")));
    %>

    <h2>Students who finished the <%= course.getName() + " course"%>
    </h2>
    <br>
    <table>
        <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Grade</th>
        </tr>
        </thead>
        <tbody>
        <% for (Grade grade : grades) { %>
        <tr>
            <td><%= grade.getStudentFirstName() %>
            </td>
            <td><%= grade.getStudentLastName() %>
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

