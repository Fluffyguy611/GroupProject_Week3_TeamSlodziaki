package org.kainos.ea.api;

import org.kainos.ea.cli.Employee;
import org.kainos.ea.cli.SalesEmployee;
import org.kainos.ea.cli.SalesEmployeeRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.EmployeeValidator;
import org.kainos.ea.db.EmployeeDao;
import org.kainos.ea.db.SalesEmployeeDao;
import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private EmployeeDao employeeDao;
    private SalesEmployeeDao salesEmployeeDao;
    private EmployeeValidator employeeValidator;

    public EmployeeService(EmployeeDao employeeDao,SalesEmployeeDao salesEmployeeDao, EmployeeValidator employeeValidator) {
        this.employeeDao = employeeDao;
        this.salesEmployeeDao = salesEmployeeDao;
        this.employeeValidator = employeeValidator;
    }

    public List<Employee> getAllEmployees() throws FailedToGetEmployeesException {

        try {
            List<Employee> employeeList = employeeDao.getAllEmployees();

            return employeeList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetEmployeesException();
        }
    }

    public Employee getEmployeeById(int id) throws FailedToGetEmployeesException, EmployeeDoesNotExistException {
        try {
            Employee employee = employeeDao.getEmployeeById(id);

            if(employee == null){
                throw new EmployeeDoesNotExistException();
            }

            return employee;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetEmployeesException();
        }
    }

    public List<SalesEmployee> getAllSalesEmployees() throws FailedToGetSalesEmployeesException {

        try {
            List<SalesEmployee> salesEmployeeList = salesEmployeeDao.getAllSalesEmployees();

            return salesEmployeeList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetSalesEmployeesException();
        }
    }

    public SalesEmployee getSalesEmployeeById(int id) throws FailedToGetSalesEmployeesException, EmployeeDoesNotExistException {
        try {
            SalesEmployee salesEmployee = salesEmployeeDao.getSalesEmployeeById(id);

            if(salesEmployee == null){
                throw new EmployeeDoesNotExistException();
            }

            return salesEmployee;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetSalesEmployeesException();
        }
    }

    public int createSalesEmployee(SalesEmployeeRequest employee) throws InvalidSalesEmployeeException, FailedToCreateSalesEmployeeException {
        try{
            String validation = employeeValidator.isValidSalesEmployee(employee);

            if(validation != null){
                throw new InvalidSalesEmployeeException(validation);
            }

            int id = employeeDao.createEmployee(employee);

            if(id == -1){
                throw new FailedToCreateSalesEmployeeException();
            }

            salesEmployeeDao.createSalesEmployee(employee, id);

            return id;
        } catch (SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToCreateSalesEmployeeException();
        }
    }

    public void updateSalesEmployee(SalesEmployeeRequest employee, int id) throws InvalidSalesEmployeeException, EmployeeDoesNotExistException, FailedToUpdateEmployeeException {
        try{
            String validation = employeeValidator.isValidSalesEmployee(employee);

            if(validation != null){
                throw new InvalidSalesEmployeeException(validation);
            }

            Employee salesEmployeeToUpdate = employeeDao.getEmployeeById(id);

            if(salesEmployeeToUpdate == null){
                throw new EmployeeDoesNotExistException();
            }

            employeeDao.updateEmployee(employee, id);
            salesEmployeeDao.updateSalesEmployee(employee, id);
        } catch (SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToUpdateEmployeeException();
        }
    }

    public void deleteSalesEmployee(int id) throws EmployeeDoesNotExistException, FailedToDeleteSalesEmployeeException {
        try{
            Employee employeeToDelete = employeeDao.getEmployeeById(id);

            if(employeeToDelete == null){
                throw new EmployeeDoesNotExistException();
            }

            salesEmployeeDao.deleteSalesEmployee(id);
            employeeDao.deleteEmployee(id);
        } catch (SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToDeleteSalesEmployeeException();
        }
    }
}
