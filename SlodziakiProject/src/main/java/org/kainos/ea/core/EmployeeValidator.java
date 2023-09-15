package org.kainos.ea.core;

import org.kainos.ea.cli.SalesEmployeeRequest;

public class EmployeeValidator {
    public String isValidSalesEmployee(SalesEmployeeRequest employee){
        if (employee.getName().length() > 100){
            return "Name grater than 100 characters";
        }

        if (employee.getSalary() <= 0){
            return "Salary can not be lesser/equal 0";
        }

        if (!(employee.getCommissionRate() > 0 && employee.getCommissionRate() < 1)){
            return "Commission rate should be between 0 and 1";
        }

        return null;
    }
}
