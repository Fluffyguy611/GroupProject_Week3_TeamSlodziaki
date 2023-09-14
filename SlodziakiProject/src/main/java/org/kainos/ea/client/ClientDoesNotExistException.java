package org.kainos.ea.client;

public class ClientDoesNotExistException extends Throwable {
    @Override
    public String getMessage() {
        return "Client does not exists";
    }
}
