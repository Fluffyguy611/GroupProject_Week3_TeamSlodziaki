package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeRequest {
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

    @JsonCreator
    public EmployeeRequest(
            @JsonProperty("Name") String name,
            @JsonProperty("Salary") int salary,
            @JsonProperty("BankAccountNumber") String bankAccount,
            @JsonProperty("NationalInsuranceNumber") String insuranceNumber){
        setName(name);
        setSalary(salary);
        setBankAccount(bankAccount);
        setInsuranceNumber(insuranceNumber);
    }
}
