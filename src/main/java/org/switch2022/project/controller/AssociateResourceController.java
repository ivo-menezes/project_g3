package org.switch2022.project.controller;

import org.switch2022.project.mapper.ResourceDTO;
import org.switch2022.project.model.*;
import org.switch2022.project.model.account.Account;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.role.Role;

public class AssociateResourceController {
    private AccountList accountList;
    private RoleList roleList;
    private ProjectList projectList;

    /**
     * Constructor that accepts existing accountList,roleList,projectList
     * @param accountList
     * @param roleList
     * @param projectList
     */

    public AssociateResourceController (AccountList accountList, RoleList roleList, ProjectList projectList){
        this.accountList = accountList;
        this.roleList = roleList;
        this.projectList = projectList;
    }

    /**
     * Request to add a resource to a specific project.
     * @param resourceDTO
     * @return true if resource successfully added, false otherwise
     */

    public boolean addResource(ResourceDTO resourceDTO){
        boolean resourceAdded = false;

        Account account = accountList.getAccount(resourceDTO.email);

        if (account.isUser()) {
            //resourceAdded= projectList.addResourceToProject(account,role,resourceDTO);
            Role role = roleList.getRole(resourceDTO.roleName);
            Project project = projectList.getProject(resourceDTO.projectCode);
            resourceAdded = project.addResource(account, role, resourceDTO);
        }

        return resourceAdded;
    }
}
