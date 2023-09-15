package org.kainos.ea.api;

import org.kainos.ea.cli.*;
import org.kainos.ea.client.*;
import org.kainos.ea.core.ProjectValidator;
import org.kainos.ea.db.ClientDao;
import org.kainos.ea.db.EmployeeDao;
import org.kainos.ea.db.ProjectDao;
import org.kainos.ea.db.ProjectDeliveryEmployeeDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectService {

    private ProjectDao projectDao = new ProjectDao();
    private ClientDao clientDao = new ClientDao();


    private EmployeeDao employeeDao = new EmployeeDao();

    private ProjectDeliveryEmployeeDao projectDeliveryEmployeeDao = new ProjectDeliveryEmployeeDao();

    public void assignClientToProject(int projectId, ClientRequestId client) throws ProjectDoesNotExistsException,
            ClientDoesNotExistException,
            FailedToUpdateProjectException {
        try {
            Project projectToUpdate = projectDao.getProjectById(projectId);
            Client clientToCheck = clientDao.getClientById(client.getClientId());

            if (projectToUpdate == null) {
                throw new ProjectDoesNotExistsException();
            }

            if (clientToCheck == null) {
                throw new ClientDoesNotExistException();
            }

            projectDao.assignClientToProject(projectId, client.getClientId());
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToUpdateProjectException();
        }
    }


    public void setProjectAsCompleted(int projectId) throws ProjectDoesNotExistsException,
            FailedToUpdateProjectException, ProjectIsAllreadyCompletedException {
        try {
            Project projectToUpdate = projectDao.getProjectById(projectId);

            if (projectToUpdate == null) {
                throw new ProjectDoesNotExistsException();
            }

            ProjectValidator validator = new ProjectValidator();

            if (validator.isStillUncompleted(projectToUpdate) != null) {
                throw new ProjectIsAllreadyCompletedException();
            }

            projectDao.setProjectAsCompleted(projectId);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToUpdateProjectException();
        }
    }

    public List<ProjectReport> getProjectsReport() throws FailedToGetEmployeesException {

        try {

            List<ProjectReportTechLead> projectList = projectDao.getProjectsReport();
            List<ProjectReport> projectListFull = new ArrayList<>();

            for (ProjectReportTechLead project: projectList) {
                ProjectReport projectReport = new ProjectReport(
                        project.getId(),
                        project.getProjectName(),
                        project.getTechLeadName());
                projectReport.setDeliveryEmployee(projectDao.getDeliveryEmployeesFromProject(projectReport.getId()));
                projectListFull.add(projectReport);
            }


            return projectListFull;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetEmployeesException();
        }
    }

    public void assignDeliveryEmployeeToProject(ProjectDeliveryEmployeeRequest projectDeliveryEmployee) throws FailedToAssignDeliveryEmployeeToProject, EmployeeDoesNotExistException, ProjectDoesNotExistsException {
        try {
            projectDeliveryEmployeeDao.assignDeliveryEmployeeToProject(projectDeliveryEmployee);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToAssignDeliveryEmployeeToProject();
        }
    }
    public void removeEmployeeFromAProject(int projectId, int employeeId) throws ProjectDeliveryConnectionDoesNotExistException, FailedToRemoveEmployeeFromAProjectException, ProjectDoesNotExistsException, EmployeeDoesNotExistException {
        try{
            Project projectToUpdate = projectDao.getProjectById(projectId);
            Employee employeeToUpdate = employeeDao.getEmployeeById(employeeId);

            if (projectToUpdate == null){
                throw new ProjectDoesNotExistsException();
            }
            if (employeeToUpdate == null){
                throw new EmployeeDoesNotExistException();
            }

            Project_DeliveryEmployee projectDeliveryEmployeeToUpdate =
                    projectDeliveryEmployeeDao.getProject_DeliverEmployeeById(projectId, employeeId);

            if (projectDeliveryEmployeeToUpdate == null){
                throw new ProjectDeliveryConnectionDoesNotExistException();
            }

            projectDao.removeEmployeeFromAProject(projectId, employeeId);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToRemoveEmployeeFromAProjectException();
        }


    }

    public ProjectRequestDetails getProjectById(int id) throws FailedToGetProjectException, ProjectDoesNotExistsException{
        try {
            ProjectRequestDetails project = projectDao.getProjectDetailsById(id);

            if(project == null){
                throw new ProjectDoesNotExistsException();
            }

            return project;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetProjectException();
        }
    }

}
