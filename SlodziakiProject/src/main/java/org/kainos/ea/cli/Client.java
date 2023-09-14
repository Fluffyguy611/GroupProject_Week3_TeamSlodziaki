package org.kainos.ea.cli;

public class Client {
    private int clientId;
    private String name;
    private String address;
    private String phone;
    private int salesId; //FK

    public Client(int clientId, String name, String address, String phone, int salesId) {
        setClientId(clientId);
        setName(name);
        setAddress(address);
        setPhone(phone);
        setSalesId(salesId);
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }
}
