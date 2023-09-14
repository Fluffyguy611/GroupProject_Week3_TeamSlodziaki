package org.kainos.ea.db;

import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.cli.Employee;
import org.kainos.ea.cli.EmployeeRequest;
import org.kainos.ea.cli.SalesEmployeeRequest;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    public List<Employee> getAllEmployees() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * from Employee;");

        List<Employee> employeeList = new ArrayList<>();

        while (rs.next()){
            Employee employee = new Employee(
                    rs.getInt("Id"),
                    rs.getString("Name"),
                    rs.getInt("Salary"),
                    rs.getString("BankAccountNumber"),
                    rs.getString("NationalInsuranceNumber")
            );

            employeeList.add(employee);
        }

        return employeeList;
    }

    public Employee getEmployeeById(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Employee WHERE Id=" + id + ";");

        while (rs.next()){
            return new Employee(
                    rs.getInt("Id"),
                    rs.getString("Name"),
                    rs.getInt("Salary"),
                    rs.getString("BankAccountNumber"),
                    rs.getString("NationalInsuranceNumber")
            );

        }

        return null;
    }
    public int createEmployee(DeliveryEmployeeRequest employee) throws SQLException {
        Connection c =  databaseConnector.getConnection();

        String insertStatement = "INSERT INTO Employee (Name, Salary, BankAccountNumber, NationalInsuranceNumber) VALUES (?, ?, ?, ?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, employee.getName());
        st.setInt(2, employee.getSalary());
        st.setString(3, employee.getBankAccount());
        st.setString(4, employee.getInsuranceNumber());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }
    public void updateEmployee(DeliveryEmployeeRequest employee, int id) throws SQLException{
        Connection c = databaseConnector.getConnection();

        String updateStatement = "UPDATE Employee SET Name = ?, Salary = ?, BankAccountNumber = ? WHERE Id = ?";

        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setString(1, employee.getName());
        st.setInt(2, employee.getSalary());
        st.setString(3, employee.getBankAccount());
        st.setInt(4, id);

        st.executeUpdate();
    }
    public void deleteEmployee(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String deleteStatement = "DELETE FROM Employee WHERE Id = ?";

        PreparedStatement st = c.prepareStatement(deleteStatement);

        st.setInt(1, id);

        st.executeUpdate();
    }

    public int createEmployee(EmployeeRequest employee) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO Employee (Name, Salary, BankAccountNumber, NationalInsuranceNumber) VALUES (?,?,?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, employee.getName());
        st.setInt(2, employee.getSalary());
        st.setString(3, employee.getBankAccount());
        st.setString(4, employee.getInsuranceNumber());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()){
            return rs.getInt(1);
        }

        return -1;
    }

    public void updateEmployee(EmployeeRequest employee, int id) throws SQLException{
        Connection c = databaseConnector.getConnection();

        String updateStatement = "UPDATE Employee SET Name = ?, Salary = ?, BankAccountNumber = ?, NationalInsuranceNumber = ? WHERE Id = ?";

        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setString(1, employee.getName());
        st.setDouble(2, employee.getSalary());
        st.setString(3, employee.getBankAccount());
        st.setString(4, employee.getInsuranceNumber());
        st.setInt(5, id);

        st.executeUpdate();
    }

}


