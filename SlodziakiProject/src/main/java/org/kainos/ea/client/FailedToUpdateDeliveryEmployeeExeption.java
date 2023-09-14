package org.kainos.ea.client;

public class FailedToUpdateDeliveryEmployeeExeption extends Exception {
    @Override
    public String getMessage(){
        return "Failed to update delivery employee";
    }
}
