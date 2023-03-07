<%--
  Created by IntelliJ IDEA.
  User: Yazn
  Date: 3/4/2023
  Time: 11:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<link rel="stylesheet" type="text/css" href="style.css">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            background-image: url("Images/welcome-page.jpg");
            background-repeat: no-repeat;
            background-size: cover;
            background-position: center top 30%;
            margin: 0;
            padding: 0;
            font-family: 'serif', Arial;
        }


        .container {
            margin-top: 30px;
            align-items: center;
            justify-content: center;
            display: inline-block;
        }

        .dropbtn {
            background-color: var(--primary-color);
            color: white;
            padding: 1rem;
            font-size: 1.2rem;
            border: none;
            border-radius: 0.5rem;
            cursor: pointer;
        }

        .dropdown {
            position: relative;
            display: inline-block;
            margin: 0 1rem;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: white;
            min-width: 16rem;
            box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
            z-index: 1;
            border-radius: 0.5rem;
        }

        .dropdown-content a {
            color: var(--primary-color);
            padding: 1rem;
            text-decoration: none;
            display: block;
            font-size: 1.1rem;
        }

        .dropdown-content a:hover {
            background-color: var(--secondary-color);
            color: white;
        }

        .dropdown:hover .dropdown-content {
            display: block;
        }

        .dropdown:hover .dropbtn {
            background-color: var(--secondary-color);
        }
    </style>
</head>
<body>
<div class="container">
    <br>
    <h1>Student Grading System</h1>
    <br>
    <h3>Welcome to the student grading system</h3>
    <br>
    <br>

    <div class="dropdown">
        <button class="dropbtn">Data Modification</button>
        <div class="dropdown-content">
            <a href="add-new-student.jsp">Add a new student</a>
            <a href="add-new-course.jsp">Add a new course</a>
            <a href="add-new-grade.jsp">Add a grade</a>
        </div>
    </div>

    <div class="dropdown">
        <button class="dropbtn">Show System Data</button>
        <div class="dropdown-content">
            <a href="all-students.jsp">All students</a>
            <a href="all-courses.jsp">All courses</a>
            <a href="finished-courses-for-student.jsp">Finished courses for some student</a>
            <a href="students-in-course.jsp">Students who completed a course</a>
            <a href="course-average.jsp">"Course Average Mark</a>
        </div>
    </div>
</div>
</body>
</html>
