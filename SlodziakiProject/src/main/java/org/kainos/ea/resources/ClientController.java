package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.ClientService;
import org.kainos.ea.api.EmployeeService;
import org.kainos.ea.cli.ClientRequestId;
import org.kainos.ea.client.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Tiki Taki API - Client")
@Path("/api")
public class ClientController {
    ClientService clientService = new ClientService();

    @GET
    @Path("/clients/reports")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientsReport() {

        try {
            return Response.ok(clientService.getClientsReport()).build();
        } catch (FailedToGetClientsException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }

    }

    @GET
    @Path("/clients/projects/top")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHighestValueClient() {

        try {
            return Response.ok(clientService.getHighestValueClient()).build();
        } catch (FailedToGetClientsException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }

    }


    @GET
    @Path("/clients")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllClients() {
        try {
            return Response.ok(clientService.getAllClients()).build();
        } catch (FailedToGetClientsException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }

    }
}
