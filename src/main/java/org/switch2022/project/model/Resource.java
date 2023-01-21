package org.switch2022.project.model;

import java.util.Date;

public class Resource {
    private Role role;
    private Account account;
    private Project project;
    private Date startDate;
    private Date endDate;
    private double percentAllocation;
    private double costPerHour;

    /**
     * Constructor to create a resource object.
     * @param role
     * @param account
     * @param project
     * @param startDate
     * @param endDate
     * @param percentAllocation
     * @param costPerHour
     */

    public Resource (Role role, Account account, Project project, Date startDate, Date endDate, double percentAllocation, double costPerHour) {
        this.role= role;
        this.account= account;
        this.project= project;
        this.startDate= startDate;
        this.endDate= endDate;
        this.percentAllocation= percentAllocation;
        this.costPerHour= costPerHour;
    }

    /**
     * Method to verify that the account is the same as the account associated with the resource.
     * @param account
     * @return false if account is different, true otherwise.
     */
    public boolean isAccountOfResource(Account account) {
        return this.account.equals(account);
    }
}
