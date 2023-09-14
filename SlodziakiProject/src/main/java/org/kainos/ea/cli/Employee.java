package org.kainos.ea.cli;


public class Employee {
    private int employeeId;
    private String name;
    private int salary;
    private String bankAccount;
    private String insuranceNumber;

    public Employee(int employeeId, String name, int salary, String bankAccount, String insuranceNumber) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
        this.bankAccount = bankAccount;
        this.insuranceNumber = insuranceNumber;
    }


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }
}


