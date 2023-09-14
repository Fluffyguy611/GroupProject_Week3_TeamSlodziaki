package org.kainos.ea.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import org.kainos.ea.api.ProjectService;
import org.kainos.ea.cli.ClientRequestId;
import org.kainos.ea.cli.ProjectDeliveryEmployeeRequest;
import org.kainos.ea.cli.Project_DeliveryEmployee;
import org.kainos.ea.cli.SalesEmployeeRequest;
import org.kainos.ea.client.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Tiki Taki API - Project")
@Path("/api")
public class ProjectController {

    private ProjectService projectService = new ProjectService();


    @PUT
    @Path("/project/{projectId}/clients")
    @Produces(MediaType.APPLICATION_JSON)

    public Response assignClientToProject(@PathParam("projectId") int projectId, ClientRequestId client){

        try {
            projectService.assignClientToProject(projectId, client);

            return Response.ok().build();
        } catch (FailedToUpdateProjectException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (ProjectDoesNotExistsException | ClientDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }


    @PUT
    @Path("/project/{projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setProjectAsCompleted(@PathParam("projectId") int projectId){
        try {
            projectService.setProjectAsCompleted(projectId);

            return Response.ok().build();
        } catch (FailedToUpdateProjectException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (ProjectDoesNotExistsException | ProjectIsAllreadyCompletedException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/projects/reports")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts(){
        try {
            return Response.ok(projectService.getProjectsReport()).build();
        } catch (FailedToGetEmployeesException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
    @POST
    @Path("projects/employees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response assignDeliveryEmployeeToProject(ProjectDeliveryEmployeeRequest projectDeliveryEmployee) throws EmployeeDoesNotExistException, ProjectDoesNotExistsException {
        try {
            projectService.assignDeliveryEmployeeToProject(projectDeliveryEmployee);
            return Response.status(Response.Status.CREATED).build();
        } catch (FailedToAssignDeliveryEmployeeToProject e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Failed to assign the delivery employee to the project.")
                    .build();
        }
    }

    @PUT
    @Path("/project/{projectId}/emploeeys/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeEmployeeFromAProject(@PathParam("projectId") int projectId, @PathParam("employeeId") int employeeId){
        try {
            projectService.removeEmployeeFromAProject(projectId, employeeId);
            return Response.ok().build();
        } catch (ProjectDeliveryConnectionDoesNotExistException |  ProjectDoesNotExistsException |
                 EmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToRemoveEmployeeFromAProjectException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }

    }
}
