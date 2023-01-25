package org.switch2022.project.model;

import java.util.Date;

public class Resource {
    private final Role role;
    private final Account account;
    private final Project project;
    private final Date startDate;
    private final double percentAllocation;
    private final double costPerHour;
    private Date endDate;

    /**
     * Constructor to create a resource object.
     *
     * @param role
     * @param account
     * @param project
     * @param startDate
     * @param percentAllocation
     * @param costPerHour
     */

    public Resource(Role role, Account account, Project project, Date startDate, double percentAllocation, double costPerHour) {
        this.role = role;
        this.account = account;
        this.project = project;
        this.startDate = startDate;
        this.percentAllocation = percentAllocation;
        this.costPerHour = costPerHour;
    }

    /**
     * Method to verify that the account is the same as the account associated with the resource.
     *
     * @param account
     * @return false if account is different, true otherwise.
     */
    public boolean isAccountOfResource(Account account) {
        return this.account.equals(account);
    }

    public boolean hasRole(Role role) {
        return this.role == role;
    }

    public boolean isActive() {
        return this.endDate == null;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Retrieves the email of the account associated to the resource.
     *
     * @return a string with the email address of the account.
     */
    public String getEmailOfResource() {
        return account.getEmail();
    }

    /**
     * Retrieves a string identifying the role of the resource.
     *
     * @return a string with the role name.
     */
    public String getRole() {
        return this.role.getDescription();
    }

    /**
     * Creates a Resource DTO containing the email and the role of the resource.
     *
     * @return resourceDTO.
     */
    public ResourceDTO createResourceDTO() {
        String email = getEmailOfResource();
        String role = getRole();
        ResourceDTO resourceDTO = new ResourceDTO(email, role);

        return resourceDTO;
    }

    public Account getAccount() {
        return account;
    }

}
