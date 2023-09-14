package org.kainos.ea.client;

public class ProjectDoesNotExistsException extends Exception {
    @Override
    public String getMessage() {
        return "Project does not exists";
    }
}
