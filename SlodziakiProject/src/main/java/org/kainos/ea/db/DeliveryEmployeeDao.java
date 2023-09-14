package org.kainos.ea.db;

import org.kainos.ea.cli.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryEmployeeDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    public List<Employee> getAllDeliveryEmployees() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT e.* from Employee e INNER JOIN DeliveryEmployee de ON e.Id = de.EmployeeId;");

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

    public Employee getDeliveryEmployeeById(int id) throws SQLException {
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
    public void createDeliveryEmployee(int id) throws SQLException {
        Connection c =  databaseConnector.getConnection();

        String insertStatement = "INSERT INTO DeliveryEmployee (EmployeeId) VALUES (?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setInt(1, id);

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();
    }
    public void deleteDeliveryEmployee(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String deleteStatement = "DELETE FROM DeliveryEmployee WHERE EmployeeId = ?";

        PreparedStatement st = c.prepareStatement(deleteStatement);

        st.setInt(1, id);

        st.executeUpdate();
    }


}

