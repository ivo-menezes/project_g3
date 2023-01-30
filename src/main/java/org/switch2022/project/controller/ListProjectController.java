package org.switch2022.project.controller;

import org.switch2022.project.model.Project;
import org.switch2022.project.model.ProjectDTO;
import org.switch2022.project.model.ProjectList;

import java.util.ArrayList;
import java.util.List;

public class ListProjectController {

    private ProjectList projectList;
    public ListProjectController(ProjectList projectList){
        if(projectList.listSize()<=0){
            throw new IllegalArgumentException("List is empty");
        }
        this.projectList = projectList;
    }

    /***
     * The methods receives a projectList and will create a list of projectDTOs to send.
     * containing code, name, customer, start and end date and project status of each project in the list.
     * @param projectList
     * @return ProjectListDTO
     */
    public List listProject(ProjectList projectList){
        List<ProjectDTO> ProjectDTOList = new ArrayList<>();
        Project project = null;

        int listSize = this.projectList.listSize();

        for(int index = 0; index < listSize; index++){
            project = projectList.getProjectByIndex(index);
            ProjectDTOList.add(projectList.createProjectDTO(project));
        }
        return ProjectDTOList;
    }
}


