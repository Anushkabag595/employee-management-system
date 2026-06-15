package employeemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    
    // 1. Database Connection Method
    public static Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb","root","123456");
        }catch(Exception e){ 
            e.printStackTrace();
            throw new RuntimeException("DB Connection Failed: " + e.getMessage()); 
        }
        return con;
    }
    
    // 2. Add Employee
    public static int addEmployee(Employee e){
        int status = 0;
        try{
            Connection con = EmployeeDAO.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "insert into employees(name, department, salary, email, password) values(?,?,?,?,?)"
            );
            ps.setString(1, e.getName());
            ps.setString(2, e.getDepartment());
            ps.setDouble(3, e.getSalary());
            ps.setString(4, e.getEmail());
            ps.setString(5, e.getPassword());
            
            status = ps.executeUpdate();
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return status;
    }
    
    // 3. View All Employees
    public static List<Employee> getAllEmployees(){
        List<Employee> list = new ArrayList<Employee>();
        try{
            Connection con = EmployeeDAO.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from employees");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Employee e = new Employee();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("name"));
                e.setDepartment(rs.getString("department"));
                e.setSalary(rs.getDouble("salary"));
                e.setEmail(rs.getString("email"));
                e.setPassword(rs.getString("password"));
                list.add(e);
            }
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return list;
    }
    
    // 4. Get Employee By ID for Edit
    public static Employee getEmployeeById(int id){
        Employee e = new Employee();
        try{
            Connection con = EmployeeDAO.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from employees where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("name"));
                e.setDepartment(rs.getString("department"));
                e.setSalary(rs.getDouble("salary"));
                e.setEmail(rs.getString("email"));
                e.setPassword(rs.getString("password"));
            }
            con.close();
        }catch(Exception ex){ ex.printStackTrace(); }
        return e;
    }

    // 5. Update Employee
    public static int updateEmployee(Employee e){
        int status = 0;
        try{
            Connection con = EmployeeDAO.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "update employees set name=?, department=?, salary=?, email=?, password=? where id=?"
            );
            ps.setString(1, e.getName());
            ps.setString(2, e.getDepartment());
            ps.setDouble(3, e.getSalary());
            ps.setString(4, e.getEmail());
            ps.setString(5, e.getPassword());
            ps.setInt(6, e.getId());
            
            status = ps.executeUpdate();
            con.close();
        }catch(Exception ex){ ex.printStackTrace(); }
        return status;
    }

    // 6. Delete Employee
    public static int deleteEmployee(int id){
        int status = 0;
        try{
            Connection con = EmployeeDAO.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from employees where id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();
            con.close();
        }catch(Exception ex){ ex.printStackTrace(); }
        return status;
    }
}