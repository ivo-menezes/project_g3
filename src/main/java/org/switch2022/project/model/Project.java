package org.switch2022.project.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Project {

    /**
     * Instantiate productBacklog for project.
     */
    ProductBacklog productBacklog = new ProductBacklog(new FactoryUserStoryImpl());
    //Attributes
    private int code;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private String customer;
    private int sprintDuration;
    private int numberOfPlannedSprints;
    private String projectStatus = "Planned";
    private double budget;
    private ResourceList list;

    private SprintList sprintList;

    //Constructors

    public Project(int code, String name, String description, Date startDate, Date endDate, int sprintDuration, int numberOfPlannedSprints, String projectStatus, double budget) {
        if (code <= 0 || name == null || description == null || startDate == null || endDate == null || startDate.after(endDate) || sprintDuration <= 0 || numberOfPlannedSprints <= 0 || projectStatus == null || budget < 0) {
            throw new IllegalArgumentException("Missing mandatory details.");
        }
        this.code = code;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sprintDuration = sprintDuration;
        this.numberOfPlannedSprints = numberOfPlannedSprints;
        this.projectStatus = projectStatus;
        this.budget = budget;
        this.list = new ResourceList();
        this.sprintList = new SprintList();
    }

    public Project(int code, String name, String description) {
        if (code <= 0 || name == null || description == null) {
            throw new IllegalArgumentException("Missing mandatory details.");
        }
        this.code = code;
        this.name = name;
        this.description = description;
        this.list = new ResourceList();
        this.sprintList = new SprintList();
    }

    public Project(int code, String name, Date startDate, Date endDate, String costumer, String projectStatus) {
        if (code <= 0 || name == null || startDate == null || endDate == null || startDate.after(endDate) || costumer == null || projectStatus == null) {
            throw new IllegalArgumentException("Missing mandatory details.");
        }
        this.code = code;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = costumer;
        this.projectStatus = projectStatus;
        this.list = new ResourceList();
        this.sprintList = new SprintList();
    }

    /**
     * Get the project's product backlog.
     *
     * @return productBacklog
     */

    public SprintList getSprintList() {
        return sprintList;
    }

    public ProductBacklog getProductBacklog() {
        return productBacklog;
    }

    /***
     * @return code, name, customer, start&end date e project status
     */
    public String getName() {
        return this.name;
    }

    public String getCustomer() {
        return this.customer;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public String getProjectStatus() {
        return this.projectStatus;
    }


    //Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Project)) {
            return false;
        }
        Project project = (Project) o;
        return code == project.code && sprintDuration == project.sprintDuration && numberOfPlannedSprints == project.numberOfPlannedSprints && budget == project.budget && name.equals(project.name) && description.equals(project.description) && Objects.equals(startDate, project.startDate) && Objects.equals(endDate, project.endDate) && projectStatus.equals(project.projectStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, description, startDate, endDate, sprintDuration, numberOfPlannedSprints, projectStatus, budget);
    }

    public boolean addResource(Account account, Role role, ResourceDTO resourceDTO) {
        return list.addResource(account, role, this, resourceDTO);
    }

    public int getCode() {
        return this.code;
    }

    public ResourceList getList() {
        return list;
    }

    /**
     * Returns a list of DTOs containing the email and the role of each resource
     * in the project.
     *
     * @return lsResources as the list of DTOs.
     */
    public List<ResourceDTO> listResources() {
        return list.listResources();
    }

    /***
     * The methods will create an UserProjectDto, containing only code and name of the project.
     * @param project
     * @return UserProjectsDTO
     */
    public UserProjectsDTO createUserProjectsDTO(Project project) {
        return new UserProjectsDTO(project.getCode(), project.getName());
    }

    /**
     * With SprintDTO as a parameter it adds a sprint to the sprintList
     * @param sprintDTO
     */
    public boolean addSprint(SprintDTO sprintDTO){
        return this.sprintList.createSprint(sprintDTO);
    }
}
