package org.kainos.ea.db;

import org.kainos.ea.cli.Employee;
import org.kainos.ea.cli.EmployeeRequestName;
import org.kainos.ea.cli.Project;
import org.kainos.ea.cli.ProjectReportTechLead;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao {

    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public void assignClientToProject(int projectId, int clientId) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String updateStatement = "UPDATE `Project` " +
                "SET `ClientId` = ? " +
                "WHERE `Id` = ?;";

        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setInt(1, clientId);
        st.setInt(2, projectId);


        st.executeUpdate();
    }

    public void setProjectAsCompleted(int projectId) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String updateStatement = "UPDATE `Project` " +
                "SET `IsCompleted` = ? " +
                "WHERE `Id` = ?;";

        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setInt(1, 1);
        st.setInt(2, projectId);


        st.executeUpdate();
    }


    public Project getProjectById(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        String getStatement = "SELECT * FROM `Project` WHERE `Id`=" + id + ";";
        PreparedStatement st = c.prepareStatement(getStatement);
        ResultSet rs = st.executeQuery();

        while (rs.next()){
            return new Project(
                    rs.getInt("Id"),
                    rs.getString("Name"),
                    rs.getDouble("Value"),
                    rs.getBoolean("IsCompleted"),
                    rs.getInt("ClientId"),
                    rs.getInt("TechleadId")
            );

        }

        return null;
    }


    public List<EmployeeRequestName> getDeliveryEmployeesFromProject(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT " +
                "Employee.Name FROM `Project_DeliveryEmployee` " +
                "INNER JOIN Employee " +
                "ON Employee.Id = Project_DeliveryEmployee.EmployeeId " +
                "WHERE ProjectId = " + id);

        List<EmployeeRequestName> employeeList = new ArrayList<>();

        while (rs.next()){
            EmployeeRequestName employee = new EmployeeRequestName(
                    rs.getString("Employee.Name")
                    );
            employeeList.add(employee);
        }

        return employeeList;
    }

    public List<ProjectReportTechLead> getProjectsReport() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT Project.Id, Project.Name AS `Project Name`, " +
                "e1.Name AS `Tech Lead Name` " +
                "FROM Project " +
                "INNER JOIN Employee e1 " +
                "ON e1.Id = Project.TechLeadId " +
                "INNER JOIN Project_DeliveryEmployee " +
                "ON Project_DeliveryEmployee.ProjectId = Project.Id " +
                "WHERE Project.IsCompleted = 0 " +
                "GROUP BY Project.Id;");

        List<ProjectReportTechLead> projectList = new ArrayList<>();

        while (rs.next()){
            ProjectReportTechLead project = new ProjectReportTechLead(
                    rs.getInt("Project.Id"),
                    rs.getString("Project Name"),
                    rs.getString("Tech Lead Name")
            );

            projectList.add(project);

        }

        return projectList;
    }


    public void removeEmployeeFromAProject(int projectId, int employeeId) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String updateStatement = "UPDATE `Project_DeliveryEmployee` " +
                "SET `IsActive` = 0 " +
                "WHERE `ProjectId` = ? " +
                "AND EmployeeId = ?;";

        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setInt(1, projectId);
        st.setInt(2, employeeId);


        st.executeUpdate();
    }

}
