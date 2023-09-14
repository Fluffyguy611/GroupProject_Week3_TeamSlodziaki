package org.kainos.ea.client;

public class FailedToGetClientsException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to get clients list from the database";
    }
}
