package org.kainos.ea.core;

import org.kainos.ea.cli.Project;

import java.sql.SQLException;

public class ProjectValidator {

    public String isStillUncompleted(Project project) throws SQLException{
        if(project.isCompleted()){
            return "Project is already set to completed";
        }

        return null;
    }
}
