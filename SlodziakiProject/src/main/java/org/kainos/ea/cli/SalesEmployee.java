package org.kainos.ea.cli;

public class SalesEmployee extends Employee{
    private double commissionRate;

    public SalesEmployee(int employeeId, String name, int salary, String bankAccount, String insuranceNumber, double commissionRate) {
        super(employeeId, name, salary, bankAccount, insuranceNumber);
        setCommissionRate(commissionRate);
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }
}
