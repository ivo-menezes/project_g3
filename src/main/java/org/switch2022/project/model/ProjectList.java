package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;

public class ProjectList {
    private List<Project> projectList;

    /**
     * Constructor that initializes an empty list.
     */
    public ProjectList () {
        this.projectList = new ArrayList<Project>();
    }

    /**
     * Retrieves a project from the projectList given a projectCode.
     * @param projectCode
     * @return project
     */
    public Project getProject (int projectCode) {
        Project foundProject = null;

        for (Project project:projectList) {
            if (project.getCode()== projectCode) {
                foundProject= project;
            }
        }
        return foundProject;
    }

    /**
     * Method to add a resource to the project.
     * @param account
     * @param role
     * @param resourceDTO
     * @return true if the resource is added to the project, false otherwise.
     */
    public boolean addResourceToProject (Account account, Role role, ResourceDTO resourceDTO) {
        boolean addedResource = false;

        Project project = this.getProject(resourceDTO.projectCode);
        addedResource = project.addResource(account, role,resourceDTO);

        return addedResource;

    }

    /**
     * Method to add project.
     * @param project
     * @return true if the project was added.
     */
    public boolean addProject (Project project) {
        projectList.add(project);
        return true;
    }

}
