package org.switch2022.project.model;

import java.util.Date;

public class ResourceDTO {
    public String email;
    public int roleCode;
    public int projectCode;
    public Date startDate;
    public Date endDate;
    public double percentAllocation;
    public double costPerHour;

    public ResourceDTO (String email,
                        int roleCode,
                        int projectCode,
                        Date startDate,
                        Date endDate,
                        double percentAllocation,
                        double costPerHour){

        this.email = email;
        this.roleCode = roleCode;
        this.projectCode = projectCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentAllocation = percentAllocation;
        this.costPerHour = costPerHour;
    }

    public ResourceDTO (String email,
                        int roleCode,
                        int projectCode,
                        Date startDate,
                        double percentAllocation,
                        double costPerHour){

        this.email = email;
        this.roleCode = roleCode;
        this.projectCode = projectCode;
        this.startDate = startDate;
        this.percentAllocation = percentAllocation;
        this.costPerHour = costPerHour;
    }
}

