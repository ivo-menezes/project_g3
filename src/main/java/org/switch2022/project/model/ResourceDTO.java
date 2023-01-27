package org.switch2022.project.model;

import java.util.Date;
import java.util.Objects;

public class ResourceDTO {
    public String email;
    public int projectCode;
    public Date startDate;
    public Date endDate;
    public double percentAllocation;
    public double costPerHour;
    public String roleName;

    public ResourceDTO (String email,
                        String roleName,
                        int projectCode,
                        Date startDate,
                        Date endDate,
                        double percentAllocation,
                        double costPerHour){

        this.email = email;
        this.roleName = roleName;
        this.projectCode = projectCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentAllocation = percentAllocation;
        this.costPerHour = costPerHour;
    }

    public ResourceDTO (String email,
                        String roleName,
                        int projectCode,
                        Date startDate,
                        double percentAllocation,
                        double costPerHour){

        this.email = email;
        this.roleName = roleName;
        this.projectCode = projectCode;
        this.startDate = startDate;
        this.percentAllocation = percentAllocation;
        this.costPerHour = costPerHour;
    }

    public ResourceDTO (String email, String roleName) {
        this.email = email;
        this.roleName = roleName;
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
        return Objects.equals(email, that.email) && Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, roleName);
    }
}

