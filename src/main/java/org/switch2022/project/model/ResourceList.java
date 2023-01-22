package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The ResourceList class represents all human resources associated with a project, present and past.
 * Business rules:
 * - a resource with the Team Member role can only be added if there is no current resource (active) with the same account
 * - additionally, there can only be one current resource with the Product Owner, Scrum Master and Project Manager roles
 */
public class ResourceList {
    private final List<Resource> resourceList;

    /**
     * Constructor that initializes an empty list.
     */
    public ResourceList() {
        this.resourceList = new ArrayList<>();
    }

    /**
     * Adds a resource to the list.
     * A resource cannot be added if the same account is already active in the project.
     * A resource with the "Product Owner", "Scrum Master" and "Project Manager" roles cannot be added
     * if there is an an active resource with the same role in the project.
     *
     * @param account     to be associated with resource
     * @param role        to be associated with resource
     * @param resourceDTO containing resource data
     * @return true if the resource is added to the list, false otherwise.
     */
    public boolean addResource(Account account, Role role, Project project, ResourceDTO resourceDTO) {
        boolean canResourceBeAdded = false;

        if (role.getDescription().equals("Team Member")) {
            canResourceBeAdded = !existsActiveResourceWithAccount(account);
        } else if (role.getDescription().equals("Product Owner") || role.getDescription().equals("Scrum Master")) {
            canResourceBeAdded = !existsActiveResourceWithAccount(account) && !isRoleOccupied(role);
        }

        boolean resourceAdded = false;
        if (canResourceBeAdded) {
            Resource resource = new Resource(role, account, project, resourceDTO.startDate, resourceDTO.percentAllocation, resourceDTO.costPerHour);
            resourceAdded = this.resourceList.add(resource);
        }

        return resourceAdded;
    }

    /**
     * Checks if there is a current (active) resource with the given account.
     *
     * @param account to be verified
     * @return true if resource with account exists and is active, false otherwise
     */
    private boolean existsActiveResourceWithAccount(Account account) {
        boolean resourceExists = false;

        for (int i = 0; i < this.resourceList.size() && !resourceExists; i++) {
            Resource resource = this.resourceList.get(i);
            if (resource.isAccountOfResource(account) && resource.isActive()) {
                resourceExists = true;
            }
        }

        return resourceExists;
    }

    /**
     * Checks if there is a current (active) resource with the given role.
     * Used to verify project positions occupied by only one person at a time (PO, SM, PM).
     *
     * @param role to be verified
     * @return true if resource with role exists and is active, false otherwise
     */
    private boolean isRoleOccupied(Role role) {
        boolean roleOccupied = false;

        for (int i = 0; i < this.resourceList.size() && !roleOccupied; i++) {
            Resource resource = this.resourceList.get(i);
            if (resource.hasRole(role) && resource.isActive()) {
                roleOccupied = true;
            }
        }

        return roleOccupied;
    }
}
