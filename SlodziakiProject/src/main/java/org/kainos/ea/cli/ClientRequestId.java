package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientRequestId {
    private int Id;

    @JsonCreator
    public ClientRequestId( @JsonProperty("clientId") int id) {
        setId(id);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
