package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SalesEmployeeRequest extends EmployeeRequest{
    private double commissionRate;
    private String name;
    private int salary;
    private String bankAccount;
    private String insuranceNumber;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
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

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    @JsonCreator
    public SalesEmployeeRequest(
            @JsonProperty("name") String name,
            @JsonProperty("salary") int salary,
            @JsonProperty("accountNumber") String bankAccount,
            @JsonProperty("nationalInsuranceNumber") String insuranceNumber,
            @JsonProperty("commissionRate") double commissionRate) {
        super(name, salary, bankAccount, insuranceNumber);
        setCommissionRate(commissionRate);
    }
}
