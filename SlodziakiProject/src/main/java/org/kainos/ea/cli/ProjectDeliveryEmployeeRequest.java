package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectDeliveryEmployeeRequest {
    private int projectId;
    private int employeeId;
    private boolean isActive;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @JsonCreator
    public ProjectDeliveryEmployeeRequest(
            @JsonProperty("ProjectId") int projectId,
            @JsonProperty("EmployeeId") int employeeId,
            @JsonProperty("IsActive") boolean isActive) {
        this.projectId = projectId;
        this.employeeId = employeeId;
        this.isActive = isActive;
    }
}