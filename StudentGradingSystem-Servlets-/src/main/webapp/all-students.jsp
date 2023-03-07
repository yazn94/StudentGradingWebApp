<%@ page import="com.example.studentgradingsystemservlets.Database.DAO" %>
<%@ page import="com.example.studentgradingsystemservlets.Model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Yazn
  Date: 3/4/2023
  Time: 9:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" type="text/css" href="style.css">

<head>
    <title>All Students Page</title>
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
    </style>
</head>
<body>
<div class="container">

    <h1>All Students</h1>


    <%!
        DAO dao = new DAO();
    %>

    <table>
        <tr>
            <th>Student ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Age</th>
            <th>Phone</th>
        </tr>
        <% for (Student student : dao.allStudents()) { %>
        <tr>
            <td><%= student.getId() %>
            </td>
            <td><%= student.getFirstName() %>
            </td>
            <td><%= student.getLastName() %>
            </td>
            <td><%= student.getAge() %>
            </td>
            <td><%= student.getPhone() %>
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
