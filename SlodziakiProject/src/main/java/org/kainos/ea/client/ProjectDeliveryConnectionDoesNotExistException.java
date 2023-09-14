package org.kainos.ea.client;

public class ProjectDeliveryConnectionDoesNotExistException extends Throwable {

    @Override
    public String getMessage() {
        return "Project and Delivery Employee connection does not exist";
    }
}
