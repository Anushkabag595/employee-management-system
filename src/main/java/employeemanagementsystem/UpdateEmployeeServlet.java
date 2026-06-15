package employeemanagementsystem;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateEmployeeServlet")
public class UpdateEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Form theke data newa
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        String name = request.getParameter("name");
        String department = request.getParameter("department");
        double salary = Double.parseDouble(request.getParameter("salary"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // Employee object e data set
        Employee e = new Employee();
        e.setId(id);
        e.setName(name);
        e.setDepartment(department);
        e.setSalary(salary);
        e.setEmail(email);
        e.setPassword(password);
        
        // DAO call kore update
        int status = EmployeeDAO.updateEmployee(e);
        
        if(status > 0){
            response.sendRedirect("ViewEmployeeServlet"); // Update hole list e chole jabe
        } else {
            out.println("<h3 style='color:red'>Sorry! Unable to update record</h3>");
            request.getRequestDispatcher("EditEmployeeServlet?id="+id).include(request, response);
        }
        out.close();
    }
}