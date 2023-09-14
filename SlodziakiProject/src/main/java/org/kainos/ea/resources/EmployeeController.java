package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.DeliveryEmployeeService;
import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.client.*;

import org.kainos.ea.api.DeliveryEmployeeService;

import org.kainos.ea.cli.DeliveryEmployeeRequest;

import org.kainos.ea.client.*;


import org.kainos.ea.api.EmployeeService;
import org.kainos.ea.cli.SalesEmployeeRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.EmployeeValidator;
import org.kainos.ea.db.EmployeeDao;
import org.kainos.ea.db.SalesEmployeeDao;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;

@Api("Tiki Taki API - Employee")
@Path("/api")
public class EmployeeController {

    DeliveryEmployeeService deliveryEmployeeService = new DeliveryEmployeeService();

    EmployeeService employeeService = new EmployeeService(new EmployeeDao(), new SalesEmployeeDao(), new EmployeeValidator());


    @GET
    @Path("/employees/delivery/{id}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getDeliveryEmployeeById(@PathParam("id") int id) {
        try {
            return Response.ok(deliveryEmployeeService.getDeliveryEmployeeById(id)).build();
        } catch (FailedToGetDeliveryEmployeeExeption e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (EmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }



    @POST
    @Path("/employees/delivery")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDeliveryEmployee(DeliveryEmployeeRequest employee) {
        try {
            return Response.ok(deliveryEmployeeService.createDeliveryEmployee(employee)).build();
        } catch (FailedToCreateDeliveryEmployeeException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }

    }

    @GET
    @Path("/employees/delivery")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getAllDeliveryEmployees() {

        try {
            return Response.ok(deliveryEmployeeService.getAllDeliveryEmployees()).build();
        } catch (FailedToGetDeliveryEmployeesExeption e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }catch (EmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    




    @PUT
    @Path("/employees/delivery/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDeliveryEmployee(@PathParam("id") int id, DeliveryEmployeeRequest employee) {
        try {
            deliveryEmployeeService.updateDeliveryEmployee(employee, id);

            return Response.ok().build();
        } catch (InvalidDeliveryEmployeeExeption  e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }catch (EmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToUpdateDeliveryEmployeeExeption e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/employees/sales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesEmployees(){
        try {
            return Response.ok(employeeService.getAllSalesEmployees()).build();
        } catch (FailedToGetSalesEmployeesException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

    @GET
    @Path("/employees/sales/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesEmployeeById(@PathParam("id") int id){
        try {
            return Response.ok(employeeService.getSalesEmployeeById(id)).build();
        } catch (FailedToGetSalesEmployeesException e){
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (EmployeeDoesNotExistException e){
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/employees/sales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSalesEmployee(SalesEmployeeRequest employee){
        try {
            return Response.ok(employeeService.createSalesEmployee(employee)).build();
        } catch (FailedToCreateSalesEmployeeException e){
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (InvalidSalesEmployeeException e){
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/employees/sales/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSalesEmployee(@PathParam("id") int id, SalesEmployeeRequest employee){
        try{
            employeeService.updateSalesEmployee(employee, id);

            return Response.ok().build();
        } catch (InvalidSalesEmployeeException | EmployeeDoesNotExistException e){
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToUpdateEmployeeException e){
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
    @DELETE
    @Path("/employees/delivery/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDeliveryEmployee(@PathParam("id") int id){
        try {
            deliveryEmployeeService.deleteDeliveryEmployee(id);

            return Response.ok().build();
        } catch (FailedToDeleteDeliveryEmployee e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (EmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }



    @DELETE
    @Path("/employees/sales/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSalesEmployee(@PathParam("id") int id){
        try{
            employeeService.deleteSalesEmployee(id);


            return Response.ok().build();
        } catch (EmployeeDoesNotExistException e){
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();

        }  catch (FailedToDeleteSalesEmployeeException e){

            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}
