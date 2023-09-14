package org.kainos.ea.cli;

public class ClientHighestValue {

    private int Id;
    private String Name;
    private double Value;

    public ClientHighestValue(int id, String name, double value) {
        setId(id);
        setName(name);
        setValue(value);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        Value = value;
    }
}
