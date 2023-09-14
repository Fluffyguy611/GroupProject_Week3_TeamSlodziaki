package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientRequestId {
    private int clientId;

    @JsonCreator
    public ClientRequestId( @JsonProperty("clientId") int clientId) {
        setClientId(clientId);
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
