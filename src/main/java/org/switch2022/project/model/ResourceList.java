package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class ResourceList {
    private List<Resource> resourceList;

    /**
     * Constructor that initializes an empty list.
     */
    public ResourceList () {
        this.resourceList = new ArrayList<Resource>();
    }

    /**
     * Method to add a resource to the list.
     * @param account
     * @param role
     * @param project
     * @param resourceDTO
     * @return true if the resource is added to the list, false otherwise.
     */
    public boolean addResource(Account account, Role role, Project project, ResourceDTO resourceDTO) {
        boolean resourceAdded = false;
        boolean resourceExists;

        resourceExists = isAccountOfResource(account);

        if (resourceExists == false) {
            Resource resource = new Resource(role, account, project, resourceDTO.startDate, resourceDTO.endDate, resourceDTO.percentAllocation, resourceDTO.costPerHour);
            resourceList.add(resource);
            resourceAdded = true;
        }
        return resourceAdded;
    }

    /**
     * Method to check if there are any resources with the account.
     * @param account
     * @return false if the account is not associated with the resource, true otherwise.
     */
    private boolean isAccountOfResource(Account account) {
        boolean resourceExists = false;
        for (Resource resource:resourceList) {
            resourceExists = resource.isAccountOfResource(account);

            if (resourceExists == true) {
                break;
            }
        }
        return resourceExists;
    }
}
