package employeemanagementsystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewEmployeeServlet")
public class ViewEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<a href='addEmployee.jsp'>Add New Employee</a>");
        out.println("<h1>Employee List</h1>");
        
        List<Employee> list = EmployeeDAO.getAllEmployees();
        
        out.print("<table border='1' width='100%' cellpadding='10'>");
        out.print("<tr><th>Id</th><th>Name</th><th>Department</th><th>Salary</th><th>Email</th><th>Edit</th><th>Delete</th></tr>");
        for(Employee e : list){
            out.print("<tr><td>"+e.getId()+"</td><td>"+e.getName()+"</td><td>"+e.getDepartment()+"</td><td>"+e.getSalary()+"</td><td>"+e.getEmail()+"</td>");
            out.print("<td><a href='EditEmployeeServlet?id="+e.getId()+"'>Edit</a></td>");
            out.print("<td><a href='DeleteEmployeeServlet?id="+e.getId()+"'>Delete</a></td></tr>");
        }
        out.print("</table>");
        
        out.close();
    }
}