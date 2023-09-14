package org.kainos.ea.db;

import org.kainos.ea.cli.Employee;
import org.kainos.ea.cli.Project_DeliveryEmployee;

import java.sql.*;

public class Project_DeliveryEmployeeDao {

    DatabaseConnector databaseConnector = new DatabaseConnector();

    public Project_DeliveryEmployee getProject_DeliverEmployeeById(int ProjectId, int EmployeeId) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String getString = "SELECT ProjectId, EmployeeId, IsActive FROM Project_DeliveryEmployee WHERE ProjectId = ?" +
                "AND EmployeeId = ? ;";
        PreparedStatement st = c.prepareStatement(getString);
        st.setInt(1, ProjectId);
        st.setInt(2, EmployeeId);

        ResultSet rs = st.executeQuery();

        while (rs.next()){
            return new Project_DeliveryEmployee(
                    rs.getInt("ProjectId"),
                    rs.getInt("EmployeeId"),
                    rs.getBoolean("IsActive")
            );

        }

        return null;
    }
}
