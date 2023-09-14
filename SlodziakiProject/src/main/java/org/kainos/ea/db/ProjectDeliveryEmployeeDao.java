package org.kainos.ea.db;

import org.kainos.ea.cli.ProjectDeliveryEmployeeRequest;
import org.kainos.ea.cli.Project_DeliveryEmployee;

import java.sql.*;

public class ProjectDeliveryEmployeeDao {

    DatabaseConnector databaseConnector = new DatabaseConnector();

    public Project_DeliveryEmployee getProject_DeliverEmployeeById(int ProjectId, int EmployeeId) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String getString = "SELECT ProjectId, EmployeeId, IsActive FROM Project_DeliveryEmployee WHERE ProjectId = "
                + ProjectId + " " +
                "AND EmployeeId=" + EmployeeId + ";";
        PreparedStatement st = c.prepareStatement(getString);
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
    public void assignDeliveryEmployeeToProject(ProjectDeliveryEmployeeRequest projectDeliveryEmployee) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO Project_DeliveryEmployee (ProjectId, EmployeeId, IsActive) VALUES (?,?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement);

        st.setInt(1, projectDeliveryEmployee.getProjectId());
        st.setInt(2, projectDeliveryEmployee.getEmployeeId());
        st.setBoolean(3, projectDeliveryEmployee.isActive());


        st.executeUpdate();
    }
}
