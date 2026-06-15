<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
    if(session.getAttribute("role") == null || !"Admin".equals(session.getAttribute("role"))) {
        response.sendRedirect("login.jsp");
        return;
    }
    String username = (String) session.getAttribute("username");
%>
<html>
<head>
    <title>Admin Dashboard</title>
</head>
<body>
    <h2>Welcome Admin: <%= username %></h2>
    <a href="addEmployee.jsp">Add New Employee</a> | 
    <a href="ViewEmployeeServlet">View All Employees</a> | 
    <a href="logout.jsp">Logout</a>
    <hr>
    <p>Use the links above to manage employees.</p>
</body>
</html>