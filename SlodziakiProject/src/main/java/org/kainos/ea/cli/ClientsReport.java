package org.kainos.ea.cli;

public class ClientsReport {

    private String client;
    private String salesRepresentative;
    private String projects;


    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getSalesRep() {
        return salesRepresentative;
    }

    public void setSalesRep(String salesRep) {
        this.salesRepresentative = salesRep;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public ClientsReport(String client, String salesRep, String projects) {
        setClient(client);
        setSalesRep(salesRep);
        setProjects(projects);
    }
}
