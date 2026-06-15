package employeemanagementsystem;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try {
            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Connect to database - Change password if needed
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/employeedb", "root", "123456");
            
            // Check username and password from users table
            PreparedStatement ps = con.prepareStatement(
                "SELECT role FROM users WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            // If user exists
            if(rs.next()) {
                String role = rs.getString("role");
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("role", role);
                
                // Redirect based on role
                if("Admin".equals(role)) {
                    response.sendRedirect("adminDashboard.jsp");
                } else {
                    response.sendRedirect("employeeDashboard.jsp");
                }
            } else {
                // Invalid login
                response.sendRedirect("login.jsp?error=1");
            }
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Database Error: " + e.getMessage());
        }
    }
} 