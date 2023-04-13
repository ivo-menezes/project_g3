package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.role.Role;

import static org.junit.jupiter.api.Assertions.*;

class RoleListTest {

    @Test
    @DisplayName("ensure return the role ")
    void ensureGetRole_RoleExits() {
        //arrange
        Role role = new Role("Team Member");
        Role roleTwo = new Role("PO");
        RoleList roleList = new RoleList();
        roleList.addRole(role);
        roleList.addRole(roleTwo);

        //act
        Role result = roleList.getRole("Team Member");

        //assert
        assertEquals(role,result);
    }

    @Test
    @DisplayName("ensure return null when doesn't found role.")
    void ensureGetRole_RoleNotExits() {
        //arrange
        Role role = new Role("Team Member");
        Role roleTwo = new Role("PO");
        RoleList roleList = new RoleList();
        roleList.addRole(role);
        roleList.addRole(roleTwo);

        //act
        Role result = roleList.getRole("Scrum Master");

        //assert
        assertNull(result);
    }
}