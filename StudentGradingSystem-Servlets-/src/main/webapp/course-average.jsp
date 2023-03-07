<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.studentgradingsystemservlets.Database.DAO" %>
<%@ page import="com.example.studentgradingsystemservlets.Model.Course" %>

<html>
<link rel="stylesheet" type="text/css" href="style.css">

<head>
    <title>Average</title>
</head>

<body>
<div class="container">
    <h1>Course Average Page</h1>

    <form action="course-average.jsp" method="get">
        <label for="courseId">Enter Course ID:</label>
        <input type="text" id="courseId" name="courseId" required>
        <br>
        <input type="submit" value="Submit">
    </form>

    <%
        if (request.getParameter("courseId") != null) {
            DAO dao = new DAO();
            Course course = dao.getCourseById(Long.parseLong(request.getParameter("courseId")));
            Double avg = dao.courseAvg(course.getId());
    %>
    <br>
    <br>
    <br>
    <h2>The average for the <%= course.getName()%> course
    </h2>
    <h2>is <%= avg%>
    </h2>

    <br>
    <br>
    <input type="button" class="button" value="Back"
           onclick="location.href= 'student-grading-system.jsp'
                   ">
    <%
            dao.close();
        }
    %>

</div>

</body>
</html>
