<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Employee</title>
</head>
<body>
<%
    if(session.getAttribute("role") == null || !session.getAttribute("role").equals("Admin")){
        response.sendRedirect("login.jsp");
    }
%>
<h1>Add New Employee</h1>
<form action="AddEmployeeServlet" method="post">
    Name: <input type="text" name="name" required><br><br>
    Department: <input type="text" name="department" required><br><br>
    Salary: <input type="number" name="salary" required><br><br>
    Email: <input type="email" name="email" required><br><br>
    Password: <input type="password" name="password" required><br><br>
    <input type="submit" value="Add Employee">
</form>
<br>
<a href="adminDashboard.jsp">Back to Dashboard</a>
</body>
</html>