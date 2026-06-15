package employeemanagementsystem;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditEmployeeServlet")
public class EditEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        
        Employee e = EmployeeDAO.getEmployeeById(id);
        
        out.println("<h1>Update Employee</h1>");
        out.print("<form action='EditEmployeeServlet' method='post'>");
        out.print("<input type='hidden' name='id' value='"+e.getId()+"'/>");
        out.print("Name: <input type='text' name='name' value='"+e.getName()+"'/><br><br>");
        out.print("Department: <input type='text' name='department' value='"+e.getDepartment()+"'/><br><br>");
        out.print("Salary: <input type='text' name='salary' value='"+e.getSalary()+"'/><br><br>");
        out.print("Email: <input type='email' name='email' value='"+e.getEmail()+"'/><br><br>");
        out.print("Password: <input type='password' name='password' value='"+e.getPassword()+"'/><br><br>");
        out.print("<input type='submit' value='Update Employee'/>");
        out.print("</form>");
        
        out.close();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        String name = request.getParameter("name");
        String department = request.getParameter("department");
        double salary = Double.parseDouble(request.getParameter("salary"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Employee e = new Employee();
        e.setId(id);
        e.setName(name);
        e.setDepartment(department);
        e.setSalary(salary);
        e.setEmail(email);
        e.setPassword(password);
        
        int status = EmployeeDAO.updateEmployee(e);
        if(status > 0){
            response.sendRedirect("ViewEmployeeServlet");
        } else {
            out.println("Sorry! unable to update record");
        }
        out.close();
    }
}