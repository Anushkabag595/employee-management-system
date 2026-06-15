package employeemanagementsystem;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // 1. Form theke data gulo newa holo
        String name = request.getParameter("name");
        String department = request.getParameter("department");
        double salary = Double.parseDouble(request.getParameter("salary"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // 2. Employee object e set kora holo
        Employee e = new Employee();
        e.setName(name);
        e.setDepartment(department);
        e.setSalary(salary);
        e.setEmail(email);
        e.setPassword(password);
        
        // 3. DAO call kore DB te save kora holo. int return kore
        int status = EmployeeDAO.addEmployee(e);
        
        // 4. Status check kore redirect kora holo
        if(status > 0){
            response.sendRedirect("ViewEmployeeServlet");
        } else {
            out.println("<h3 style='color:red'>Sorry! Unable to save record</h3>");
            request.getRequestDispatcher("addEmployee.html").include(request, response);
        }
        out.close();
    }
}