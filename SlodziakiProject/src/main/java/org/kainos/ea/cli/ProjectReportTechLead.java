package org.kainos.ea.cli;

import java.util.List;

public class ProjectReportTechLead {
    private int Id;
    private String ProjectName;
    private String TechLeadName;

    public ProjectReportTechLead(int id, String projectName, String techLeadName) {
        setId(id);
        setProjectName(projectName);
        setTechLeadName(techLeadName);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getTechLeadName() {
        return TechLeadName;
    }

    public void setTechLeadName(String techLeadName) {
        TechLeadName = techLeadName;
    }

}
