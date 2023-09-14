package org.kainos.ea.client;

public class FailedToGetEmployeesException extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to get employees";
    }
}
