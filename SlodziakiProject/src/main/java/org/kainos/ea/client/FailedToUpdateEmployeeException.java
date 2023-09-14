package org.kainos.ea.client;

public class FailedToUpdateEmployeeException extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to update employee";
    }
}
