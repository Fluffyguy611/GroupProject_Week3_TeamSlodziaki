package org.kainos.ea.client;

public class ProjectIsAllreadyCompletedException extends Throwable {

    public String getMessage() {
        return "Project is already completed";
    }
}
