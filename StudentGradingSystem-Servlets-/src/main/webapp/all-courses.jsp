<%@ page import="com.example.studentgradingsystemservlets.Database.DAO" %>
<%@ page import="com.example.studentgradingsystemservlets.Model.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%--
  Created by IntelliJ IDEA.
  User: Yazn
  Date: 3/4/2023
  Time: 9:47 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" type="text/css" href="style.css">

<head>
    <title>All Courses Page</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            text-align: left;
            padding: 8px;
        }

        th {
            background-color: #ddd;
            color: black;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        table {
            font-size: large;
        }

    </style>
</head>
<body>
<div class="container">
    <h1>All Courses</h1>
    <%!
        DAO dao = new DAO();
    %>
    <table style="width: 37rem !important;">
        <tr>
            <th>Course ID</th>
            <th>Name</th>
        </tr>
        <% for (Course course : dao.allCourses()) { %>
        <tr>
            <td><%= course.getId() %>
            </td>
            <td><%= course.getName() %>
            </td>
            <% } %>
        </tr>
    </table>
    <br>
    <br>

    <input type="button" class="button" value="Back"
           onclick="location.href=
                   'http://localhost:8080/StudentGradingSystem_Servlets__war_exploded/' +
                    'student-grading-system.jsp'">
</div>
</body>
</html>
