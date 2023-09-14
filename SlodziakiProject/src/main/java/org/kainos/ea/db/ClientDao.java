package org.kainos.ea.db;

import org.kainos.ea.cli.Client;
import org.kainos.ea.cli.ClientHighestValue;
import org.kainos.ea.cli.ClientsReport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

    private DatabaseConnector databaseConnector = new DatabaseConnector();
    public List<Client> getAllClients() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * from Client;");

        List<Client> clientList = new ArrayList<>();

        while (rs.next()){
            Client client = new Client(
                    rs.getInt("Id"),
                    rs.getString("Name"),
                    rs.getString("Address"),
                    rs.getString("PhoneNumber"),
                    rs.getInt("SalesRepresentativeId")
            );

            clientList.add(client);
        }

        return clientList;
    }

    public Client getClientById(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        String getStatement = "SELECT * FROM `Client` WHERE `Id`=" + id + ";";
        PreparedStatement st = c.prepareStatement(getStatement);
        ResultSet rs = st.executeQuery();

        while (rs.next()){
            return new Client(
                    rs.getInt("Id"),
                    rs.getString("Name"),
                    rs.getString("Address"),
                    rs.getString("PhoneNumber"),
                    rs.getInt("SalesRepresentativeId")
            );

        }

        return null;
    }


    public List<ClientsReport> getClientsReport() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT Client.Name as 'Client' , Employee.Name as 'Sales Rep', group_concat(Project.Name separator \" , \") as 'Projects' FROM Client " +
                "LEFT JOIN Project ON Client.ID = Project.ClientId " +
                "LEFT JOIN SalesEmployee  ON SalesEmployee.EmployeeId = Client.SalesRepresentativeId " +
                "INNER JOIN Employee ON SalesEmployee.EmployeeId = Employee.Id " +
                "GROUP BY Client.Name;");

        List<ClientsReport> clientList = new ArrayList<>();

        while (rs.next()) {
            ClientsReport client = new ClientsReport(
                    rs.getString("Client"),
                    rs.getString("Sales Rep"),
                    rs.getString("Projects")
            );

            clientList.add(client);
        }

        return clientList;
    }


    public ClientHighestValue getHighestValueClient() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT Client.Id, Client.Name as \"Client\", SUM(Value) as \"Value\" FROM Client " +
                "INNER JOIN Project ON Project.ClientID = Client.Id " +
                "GROUP BY Client.Name " +
                "ORDER BY SUM(VALUE) DESC LIMIT 1;");


        while (rs.next()){
            return new ClientHighestValue(
                    rs.getInt("Client.Id"),
                    rs.getString("Client"),
                    rs.getDouble("Value")
            );
        }

        return null;
    }

}
