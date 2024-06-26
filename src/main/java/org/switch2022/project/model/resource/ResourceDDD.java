package org.switch2022.project.model.resource;

import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.model.valueobject.*;

public class ResourceDDD implements AggregateRoot<ResourceID> {

    private final ResourceID resourceID;
    private final AccountID accountID;
    private final CostPerHour costPerHour;
    private final Role role;
    private final PercentageOfAllocation percentageOfAllocation;
    private final ProjectCode projectCode;
    private final TimePeriod timePeriod;
    /**
     * Public constructor to instantiate a user story.
     *
     * @param resourceID resource id.
     * @param accountID of resource.
     * @param costPerHour of a resource in a project.
     * @param role of a resource in a project.
     * @param percentageOfAllocation of the resource.
     * @param projectCode of the project allocated to the resource.
     */
    public ResourceDDD(ResourceID resourceID, AccountID accountID, CostPerHour costPerHour, Role role,
                       PercentageOfAllocation percentageOfAllocation, ProjectCode projectCode,
                       TimePeriod timePeriod) {
        this.resourceID = resourceID;
        this.accountID = accountID;
        this.costPerHour = costPerHour;
        this.role = role;
        this.percentageOfAllocation = percentageOfAllocation;
        this.projectCode = projectCode;
        this.timePeriod = timePeriod;
    }

    @Override
    public ResourceID identity() {
        return resourceID;
    }

    public AccountID getAccountID() {
        return accountID;
    }

    public CostPerHour getCostPerHour() {
        return costPerHour;
    }

    public Role getRole() {
        return role;
    }

    public PercentageOfAllocation getPercentageOfAllocation() {
        return percentageOfAllocation;
    }

    public ProjectCode getProjectCode() {
        return projectCode;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }
}
