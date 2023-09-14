package org.kainos.ea.api;

import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.cli.Employee;

import org.kainos.ea.client.*;
import org.kainos.ea.db.DeliveryEmployeeDao;
import org.kainos.ea.db.EmployeeDao;

import java.sql.SQLException;
import java.util.List;

public class DeliveryEmployeeService {
    private final EmployeeDao employeeDao = new EmployeeDao();
    private final DeliveryEmployeeDao deliveryEmployeeDao = new DeliveryEmployeeDao();

    public List<Employee> getAllDeliveryEmployees() throws FailedToGetDeliveryEmployeesExeption, EmployeeDoesNotExistException  {

        try {
            List<Employee> employeeList = deliveryEmployeeDao.getAllDeliveryEmployees();

            return employeeList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetDeliveryEmployeesExeption();
        }
    }

    public Employee getDeliveryEmployeeById(int id) throws FailedToGetDeliveryEmployeeExeption, EmployeeDoesNotExistException {
        try {
            Employee employee = employeeDao.getEmployeeById(id);

            if(employee == null){
                throw new EmployeeDoesNotExistException();
            }

            return employee;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetDeliveryEmployeeExeption();
        }
    }
    public int createDeliveryEmployee(DeliveryEmployeeRequest employee) throws FailedToCreateDeliveryEmployeeException {
        try {
            int employeeId = employeeDao.createEmployee(employee);

            if (employeeId == -1) {
                throw new FailedToCreateDeliveryEmployeeException();
            }

            deliveryEmployeeDao.createDeliveryEmployee(employeeId);

            return employeeId;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateDeliveryEmployeeException();
        }
    }
    public void updateDeliveryEmployee(DeliveryEmployeeRequest employee, int id) throws InvalidDeliveryEmployeeExeption, EmployeeDoesNotExistException, FailedToUpdateDeliveryEmployeeExeption {
        try{
            Employee deliveryEmployeeToUpdate = employeeDao.getEmployeeById(id);

            if(deliveryEmployeeToUpdate == null){
                throw new EmployeeDoesNotExistException();
            }

            employeeDao.updateEmployee(employee, id);


        } catch (SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToUpdateDeliveryEmployeeExeption();
        }
    }
    public void deleteDeliveryEmployee(int id) throws EmployeeDoesNotExistException, FailedToDeleteDeliveryEmployee {
        try{
            Employee employeeToDelete = employeeDao.getEmployeeById(id);

            if(employeeToDelete == null){
                throw new EmployeeDoesNotExistException();
            }

            deliveryEmployeeDao.deleteDeliveryEmployee(id);
            employeeDao.deleteEmployee(id);
        } catch (SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToDeleteDeliveryEmployee();
        }
    }

}
