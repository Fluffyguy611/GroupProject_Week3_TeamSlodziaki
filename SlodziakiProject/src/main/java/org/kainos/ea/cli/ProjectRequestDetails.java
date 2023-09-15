package org.kainos.ea.cli;

public class ProjectRequestDetails {
    private int projectId;
    private String name;
    private double value;
    private boolean isCompleted;
    private int clientId; //FK
    private String clientName;
    private int techLeadId; //FK
    private String techLeadName;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getTechLeadName() {
        return techLeadName;
    }

    public void setTechLeadName(String techLeadName) {
        this.techLeadName = techLeadName;
    }

    public ProjectRequestDetails(int projectId, String name, double value, boolean isCompleted, int clientId, String clientName, int techLeadId, String techLeadName) {
        this.projectId = projectId;
        this.name = name;
        this.value = value;
        this.isCompleted = isCompleted;
        this.clientId = clientId;
        this.clientName = clientName;
        this.techLeadId = techLeadId;
        this.techLeadName = techLeadName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getTechLeadId() {
        return techLeadId;
    }

    public void setTechLeadId(int techLeadId) {
        this.techLeadId = techLeadId;
    }
}
