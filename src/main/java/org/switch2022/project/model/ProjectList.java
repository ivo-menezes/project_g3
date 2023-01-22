package org.switch2022.project.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ProjectList {
    private List<Project> projectList;

    /**
     * Constructor that initializes an empty list.
     */
    public ProjectList() {
        this.projectList = new ArrayList<Project>();
    }

    /**
     * Retrieves a project from the projectList given a projectCode.
     *
     * @param projectCode
     * @return project
     */
    public Project getProject(int projectCode) {
        Project foundProject = null;

        for (Project project : projectList) {
            if (project.getCode() == projectCode) {
                foundProject = project;
            }
        }
        return foundProject;
    }

    /**
     * Method to add a resource to the project.
     *
     * @param account
     * @param role
     * @param resourceDTO
     * @return true if the resource is added to the project, false otherwise.
     */
    public boolean addResourceToProject(Account account, Role role, ResourceDTO resourceDTO) {
        boolean addedResource = false;

        Project project = this.getProject(resourceDTO.projectCode);
        addedResource = project.addResource(account, role, resourceDTO);

        return addedResource;

    }

    /**
     * Method to add project.
     *
     * @param project
     * @return true if the project was added.
     */
    public boolean addProject(Project project) {
        projectList.add(project);
        return true;
    }

    /***
     * For the US015, needed to access the profileList size.
     * Created the method listSize in order to give the Controller the int value
     * of the list size.
     * @return number of elements in projectList
     */
    public int listSize() {
        return this.projectList.size();
    }

    /***
     * For US015, this will access the project in the index given.
     * @param index of element to be retrieved
     * @return project at index
     */
    public Project getProjectIndex(int index) {
        Project project = this.projectList.get(index);
        return project;
    }

    /***
     * The method will be called to create a project DTO, using the data from the true account.
     * @return projectDTO
     */
    public ProjectDTO createProjectDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO(project.getCode(), project.getName(), project.getCustomer(), project.getStartDate(), project.getEndDate(), project.getProjectStatus());

        return projectDTO;
    }
}
