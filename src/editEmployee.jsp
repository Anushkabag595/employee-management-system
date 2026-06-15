<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="employeemanagementsystem.Employee" %>
<%
    if(session.getAttribute("role") == null || !"Admin".equals(session.getAttribute("role"))) {
        response.sendRedirect("adminlogin.html");
    }
    Employee e = (Employee) request.getAttribute("emp");
    if(e == null) {
        response.sendRedirect("ViewEmployeeServlet");
        return;
    }
%>
<html>
<head><title>Edit Employee</title></head>
<body>
    <h2>Update Employee</h2>
    <a href="adminDashboard.jsp">Dashboard</a> |
    <a href="ViewEmployeeServlet">View All Employees</a> |
    <a href="LogoutServlet">Logout</a><hr>
    
    <form action="UpdateEmployeeServlet" method="post">
        <input type="hidden" name="id" value="<%= e.getId() %>">
        <table>
            <tr><td>Name:</td><td><input type="text" name="name" value="<%= e.getName() %>" required></td></tr>
            <tr><td>Department:</td><td><input type="text" name="department" value="<%= e.getDepartment() %>" required></td></tr>
            <tr><td>Salary:</td><td><input type="number" step="0.01" name="salary" value="<%= e.getSalary() %>" required></td></tr>
            <tr><td>Email:</td><td><input type="email" name="email" value="<%= e.getEmail() %>" required></td></tr>
            <tr><td colspan="2"><input type="submit" value="Update Employee"></td></tr>
        </table>
    </form>
</body>
</html>