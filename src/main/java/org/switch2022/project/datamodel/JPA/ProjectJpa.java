package org.switch2022.project.datamodel.JPA;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "projects")
public class ProjectJpa {

    @Id
    private String projectCode;

    private String projectName;
    private String description;
    private String projectStatus;
    //we need to specify what the field represents.
    // This way the startDate and endDate fields will be mapped to database columns of type "DATE".
    // See: https://www.baeldung.com/spring-data-jpa-query-by-date
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private int sprintDuration;
    private int projectNumberOfPlannedSprints;
    private long customerID;
    private long businessSectorID;
    private long typologyID;
    private float projectBudget;

    @ElementCollection
    @CollectionTable(name = "productBacklog", joinColumns = @JoinColumn(name = "projectCode"))
    private List<String> productBacklog;


    public ProjectJpa() {
    }

    /**
     * @param projectCode                   the code of the project
     * @param projectName                   the name of the project
     * @param description                   the description of the project
     * @param projectStatus                 the status of the project
     * @param startDate                     the start date of the project
     * @param endDate                       the end date of the project
     * @param sprintDuration                the sprint duration of the project
     * @param projectNumberOfPlannedSprints the number of planned sprints of the project
     * @param customerID                    the ID of the customer associated to the project
     * @param businessSectorID              the ID of the business sector associated to the project
     * @param typologyID                    the ID of the typology associated to the project
     * @param projectBudget                 the budget of the project
     * @param productBacklog                the list of userStory IDs.
     */

    public ProjectJpa(String projectCode, String projectName, String description, String projectStatus, Date startDate, Date endDate, int sprintDuration, int projectNumberOfPlannedSprints, long customerID, long businessSectorID, long typologyID, float projectBudget, List<String> productBacklog) {
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.description = description;
        this.projectStatus = projectStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sprintDuration = sprintDuration;
        this.projectNumberOfPlannedSprints = projectNumberOfPlannedSprints;
        this.customerID = customerID;
        this.businessSectorID = businessSectorID;
        this.typologyID = typologyID;
        this.projectBudget = projectBudget;
        this.productBacklog = productBacklog;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getSprintDuration() {
        return sprintDuration;
    }

    public int getProjectNumberOfPlannedSprints() {
        return projectNumberOfPlannedSprints;
    }

    public long getCustomerID() {
        return customerID;
    }

    public long getBusinessSectorID() {
        return businessSectorID;
    }

    public long getTypologyID() {
        return typologyID;
    }

    public float getProjectBudget() {
        return projectBudget;
    }

    public List<String> getProductBacklog() {
        return productBacklog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        ProjectJpa that = (ProjectJpa) o;
        return projectCode.equals(that.projectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCode);
    }

}


