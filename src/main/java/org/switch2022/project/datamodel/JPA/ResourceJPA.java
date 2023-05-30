package org.switch2022.project.datamodel.JPA;

import org.switch2022.project.model.valueobject.Email;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "resources")
public class ResourceJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceID;
    private String email;
    private String costPerHour;
    private String role;
    private String percentageOfAllocation;
    private String projectCode;
    private String startDate;
    private String endDate;

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
    public ResourceJPA(String email, String costPerHour, String role, String percentageOfAllocation, String projectCode, String startDate, String endDate) {
        if (email != null && !email.isBlank() && !email.isEmpty()) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email designation must not be null");
        }
        if (costPerHour != null && !costPerHour.isBlank() && !costPerHour.isEmpty()) {
            this.costPerHour = costPerHour;
        } else {
            throw new IllegalArgumentException("CostPerHour designation must not be null");
        }
        if (role != null && !role.isBlank() && !role.isEmpty()) {
            this.role = role;
        } else {
            throw new IllegalArgumentException("Role designation must not be null");
        }
        if (percentageOfAllocation != null && !percentageOfAllocation.isBlank() && !percentageOfAllocation.isEmpty()) {
            this.percentageOfAllocation = percentageOfAllocation;
        } else {
            throw new IllegalArgumentException("PercentageOfAllocation designation must not be null");
        }
        if (projectCode != null && !projectCode.isBlank() && !projectCode.isEmpty()) {
            this.projectCode = projectCode;
        } else {
            throw new IllegalArgumentException("ProjectCode designation must not be null");
        }
        if (startDate != null && !startDate.isBlank() && !startDate.isEmpty()) {
            this.startDate = startDate;
        } else {
            throw new IllegalArgumentException("StartDate designation must not be null");
        }
        if (endDate != null && !endDate.isBlank() && !endDate.isEmpty()) {
            this.endDate = endDate;
        } else {
            throw new IllegalArgumentException("EndDate designation must not be null");
        }
    }

    public Long getResourceID() {
        return resourceID;
    }

    public String getEmail() {
        return email;
    }

    public String getCostPerHour() {
        return costPerHour;
    }

    public String getRole() {
        return role;
    }

    public String getPercentageOfAllocation() {
        return percentageOfAllocation;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
