package org.kainos.ea.cli;

import java.util.List;

public class ProjectReport extends ProjectReportTechLead{

    List<EmployeeRequestName> DeliveryEmployee;
    public ProjectReport(int id, String projectName, String techLeadName) {
        super(id, projectName, techLeadName);
    }

    public List<EmployeeRequestName> getDeliveryEmployee() {
        return DeliveryEmployee;
    }

    public void setDeliveryEmployee(List<EmployeeRequestName> deliveryEmployee) {
        DeliveryEmployee = deliveryEmployee;
    }
}
