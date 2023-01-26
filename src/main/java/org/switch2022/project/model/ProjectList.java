package org.switch2022.project.model;

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
     * creates a new project and adds it to the projectList
     *
     * @param code
     * @param name
     * @param description
     * @return true if project successfully created and save, throw exception otherwise
     */
    public boolean createProject(int code, String name, String description) {
        if (!validateProjectCode(code)) {
            throw new IllegalArgumentException("A project with this code already exists.");
        }
        Project newProject = new Project(code, name, description);
        return addProject(newProject);
    }

    /**
     * validates if a Project with the same code already exists
     *
     * @param code
     * @return true if code is valid, false otherwise
     */
    public boolean validateProjectCode(int code) {
        boolean uniqueCode = true;
        if (code <= 0) {
            uniqueCode = false;
        }
        for (int i = 0; i < this.projectList.size() && uniqueCode; i++) {
            Project c = this.projectList.get(i);
            int cCode = c.getCode();
            if (cCode == (code)) {
                uniqueCode = false;
            }
        }
        return uniqueCode;
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
