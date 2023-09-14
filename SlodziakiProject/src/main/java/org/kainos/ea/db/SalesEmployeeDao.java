package org.kainos.ea.db;

import org.kainos.ea.cli.SalesEmployee;
import org.kainos.ea.cli.SalesEmployeeRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesEmployeeDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<SalesEmployee> getAllSalesEmployees() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT Id, Name, Salary, BankAccountNumber, NationalInsuranceNumber, CommissionRate FROM Employee" +
                " INNER JOIN SalesEmployee ON Employee.Id = SalesEmployee.EmployeeId");

        List<SalesEmployee> salesEmployeeList = new ArrayList<>();

        while (rs.next()){
            SalesEmployee salesEmployee = new SalesEmployee(
                    rs.getInt("Id"),
                    rs.getString("Name"),
                    rs.getInt("Salary"),
                    rs.getString("BankAccountNumber"),
                    rs.getString("NationalInsuranceNumber"),
                    rs.getDouble("CommissionRate")
            );

            salesEmployeeList.add(salesEmployee);
        }

        return salesEmployeeList;
    }

    public SalesEmployee getSalesEmployeeById(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT Id, Name, Salary, BankAccountNumber, NationalInsuranceNumber, CommissionRate FROM Employee" +
                " INNER JOIN SalesEmployee ON Employee.Id = SalesEmployee.EmployeeId WHERE Id=" + id + ";");

        while (rs.next()){
            return new SalesEmployee(
                    rs.getInt("Id"),
                    rs.getString("Name"),
                    rs.getInt("Salary"),
                    rs.getString("BankAccountNumber"),
                    rs.getString("NationalInsuranceNumber"),
                    rs.getDouble("CommissionRate")
            );

        }

        return null;
    }


    public int createSalesEmployee(SalesEmployeeRequest salesEmployee, int employeeId) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO SalesEmployee (EmployeeId, CommissionRate) VALUES (?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setInt(1, employeeId);
        st.setDouble(2, salesEmployee.getCommissionRate());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()){
            return rs.getInt(1);
        }

        return -1;
    }

    public void updateSalesEmployee(SalesEmployeeRequest employee, int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String updateStatement = "UPDATE SalesEmployee SET CommissionRate = ? WHERE EmployeeId = ?";

        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setDouble(1, employee.getCommissionRate());
        st.setInt(2, id);

        st.executeUpdate();
    }

    public void deleteSalesEmployee(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String deleteStatement = "DELETE FROM SalesEmployee WHERE EmployeeId = ?";

        PreparedStatement st = c.prepareStatement(deleteStatement);

        st.setInt(1, id);

        st.executeUpdate();
    }
}
