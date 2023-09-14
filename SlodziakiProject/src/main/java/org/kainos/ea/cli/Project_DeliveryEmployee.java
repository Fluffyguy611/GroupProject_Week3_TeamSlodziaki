package org.kainos.ea.cli;

public class Project_DeliveryEmployee {
    private int ProjectId;
    private int EmployeeId;
    private boolean  isActive;

    public Project_DeliveryEmployee(int projectId, int employeeId, boolean isActive) {
        setProjectId(projectId);
        setEmployeeId(employeeId);
        setActive(isActive);
    }

    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int projectId) {
        ProjectId = projectId;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
