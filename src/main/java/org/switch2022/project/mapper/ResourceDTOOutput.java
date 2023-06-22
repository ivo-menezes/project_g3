package org.switch2022.project.mapper;

import org.switch2022.project.model.valueobject.*;

import java.util.Objects;

public class ResourceDTOOutput {

    public ResourceID resourceID;
    public Email email;
    public CostPerHour costPerHour;
    public Role role;
    public PercentageOfAllocation percentageOfAllocation;
    public ProjectCode projectCode;
    public TimePeriod timePeriod;

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true; }
        if (!(o instanceof NewResourceDTO)) { return false; }
        NewResourceDTO that = (NewResourceDTO) o;
        return Objects.equals(email, that.accountID) && Objects.equals(role, that.role) &&
                Objects.equals(projectCode, that.projectCode) && Objects.equals(timePeriod, that.timePeriod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, role, projectCode, timePeriod);
    }
}
