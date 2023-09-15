package org.kainos.ea.api;

import org.kainos.ea.cli.Client;
import org.kainos.ea.cli.ClientHighestValue;
import org.kainos.ea.cli.ClientsReport;
import org.kainos.ea.cli.SalesEmployee;
import org.kainos.ea.client.ClientDoesNotExistException;
import org.kainos.ea.client.FailedToGetClientsException;
import org.kainos.ea.client.FailedToGetSalesEmployeesException;
import org.kainos.ea.db.ClientDao;

import java.sql.SQLException;
import java.util.List;

public class ClientService {

    ClientDao clientDao = new ClientDao();

    public List<Client> getAllClients() throws FailedToGetClientsException {

        try {
            List<Client> clientList = clientDao.getAllClients();

            return clientList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetClientsException();
        }
    }

    public Client getClientById(int id) throws FailedToGetClientsException, ClientDoesNotExistException {
        try {
            Client client = clientDao.getClientById(id);

            if(client == null){
                throw new ClientDoesNotExistException();
            }

            return client;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetClientsException();
        }
    }

    public List<ClientsReport> getClientsReport() throws FailedToGetClientsException {

        try {
            List<ClientsReport> clientList = clientDao.getClientsReport();

            return clientList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetClientsException();
        }
    }

    public ClientHighestValue getHighestValueClient() throws FailedToGetClientsException {

        try {
            ClientHighestValue client = clientDao.getHighestValueClient();

            return client;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetClientsException();
        }
    }


}
