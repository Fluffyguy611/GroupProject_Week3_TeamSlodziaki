package org.kainos.ea.client;

public class FailedToRemoveEmployeeFromAProjectException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to remove the employee from a project";
    }
}
