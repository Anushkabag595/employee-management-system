<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%
    session.invalidate(); // Session destroy korlam
    response.sendRedirect("login.jsp"); // Login page e pathalam
%>