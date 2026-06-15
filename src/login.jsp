<html>
<head><title>Employee Login</title></head>
<body>
<h2>Employee Management System - Login</h2>

<% if(request.getParameter("error") != null) { %>
    <p style="color:red;">Invalid Username or Password</p>
<% } %>

<form action="LoginServlet" method="post">
    Username: <input type="text" name="username" required><br><br>
    Password: <input type="password" name="password" required><br><br>
    <input type="submit" value="Login">
</form>
</body>
</html>