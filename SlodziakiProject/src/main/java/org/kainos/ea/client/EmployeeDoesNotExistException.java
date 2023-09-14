package org.kainos.ea.client;

public class EmployeeDoesNotExistException extends Throwable {
    @Override
    public String getMessage(){
        return "Employee does not exist";
    }
}
