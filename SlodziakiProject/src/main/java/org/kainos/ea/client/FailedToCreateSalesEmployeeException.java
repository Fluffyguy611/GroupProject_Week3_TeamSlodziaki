package org.kainos.ea.client;

public class FailedToCreateSalesEmployeeException extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to create sales employee";
    }
}
