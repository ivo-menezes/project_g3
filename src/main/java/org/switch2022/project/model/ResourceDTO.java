package org.switch2022.project.model;

import java.util.Date;
import java.util.Objects;

public class ResourceDTO {
    public String email;
    public String description;
    public int projectCode;
    public Date startDate;
    public Date endDate;
    public double percentAllocation;
    public double costPerHour;
    public String role;

    public ResourceDTO (String email,
                        String description,
                        int projectCode,
                        Date startDate,
                        Date endDate,
                        double percentAllocation,
                        double costPerHour){

        this.email = email;
        this.description = description;
        this.projectCode = projectCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentAllocation = percentAllocation;
        this.costPerHour = costPerHour;
    }

    public ResourceDTO (String email,
                        String description,
                        int projectCode,
                        Date startDate,
                        double percentAllocation,
                        double costPerHour){

        this.email = email;
        this.description = description;
        this.projectCode = projectCode;
        this.startDate = startDate;
        this.percentAllocation = percentAllocation;
        this.costPerHour = costPerHour;
    }

    public ResourceDTO (String email, String role) {
        this.email = email;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResourceDTO)) {
            return false;
        }
        ResourceDTO that = (ResourceDTO) o;
        return Objects.equals(email, that.email) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, role);
    }
}

