package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;

public class RoleList {

    private List<Role> roleList;

    /**
     * Constructor that initializes an empty list.
     */
    public RoleList(){
        this.roleList = new ArrayList<Role>();
    }

    /**
     * Retrieves a role from the roleList given a roleCode
     * @param roleCode
     * @return role
     */
    public Role getRole(int roleCode) {
        Role foundRole = null;

        for (Role role : roleList) {
            if (role.getCode() == roleCode) {
                foundRole = role;
            }
        }
        return foundRole;
    }

    /**
     * Method to add a role.
     * @param role
     * @return true if the role was added.
     */

    public boolean addRole(Role role) {
        roleList.add(role);
        return true;
    }
}
