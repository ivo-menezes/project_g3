package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleListTest {

    @Test
    @DisplayName("ensure return the role ")
    void ensureGetRole_RoleExits() {
        //arrange
        Role role = new Role(1,"Team Member");
        Role roleTwo = new Role(2,"PO");
        RoleList roleList = new RoleList();
        roleList.addRole(role);
        roleList.addRole(roleTwo);

        //act
        Role result = roleList.getRole(1);

        //assert
        assertEquals(role,result);
    }

    @Test
    @DisplayName("ensure return null when doesn't found role.")
    void ensureGetRole_RoleNotExits() {
        //arrange
        Role role = new Role(1,"Team Member");
        Role roleTwo = new Role(2,"PO");
        RoleList roleList = new RoleList();
        roleList.addRole(role);
        roleList.addRole(roleTwo);

        //act
        Role result = roleList.getRole(3);

        //assert
        assertNull(result);
    }
}