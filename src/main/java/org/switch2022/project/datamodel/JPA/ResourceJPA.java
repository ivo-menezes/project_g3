package org.switch2022.project.datamodel.JPA;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "resources")
public class ResourceJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceID;
    private String email;
    private double costPerHour;
    private String role;
    private double percentageOfAllocation;
    private String projectCode;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;

    /**
     * Default constructor used by the persistence framework
     */
    protected ResourceJPA() {

    }

    /**
     * Constructor to instantiate a ResourceJPA
     * @param email of a resource
     * @param costPerHour of a resource
     * @param role of a resource
     * @param percentageOfAllocation of a resource
     * @param projectCode where a resource are allocated
     * @param startDate of the resource in the project
     * @param endDate of the resource in the project
     */
    public ResourceJPA(String email, double costPerHour, String role, double percentageOfAllocation,
                       String projectCode, Date startDate, Date endDate) {

            this.email = email;
            this.costPerHour = costPerHour;
            this.role = role;
            this.percentageOfAllocation = percentageOfAllocation;
            this.projectCode = projectCode;
            this.startDate = startDate;
            this.endDate = endDate;

    }

    public Long getResourceID() {
        return resourceID;
    }

    public String getEmail() {
        return email;
    }

    public double getCostPerHour() {
        return costPerHour;
    }

    public String getRole() {
        return role;
    }

    public double getPercentageOfAllocation() {
        return percentageOfAllocation;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
