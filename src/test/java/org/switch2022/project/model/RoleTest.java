package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    @DisplayName("ensure return code Role")
    void ensureGetCode() {
        //arrange
        int roleCode = 1;
        Role role = new Role(roleCode, "Team Member");

        //act
        int result = role.getCode();

        //assert
        assertEquals(roleCode, result);
    }
}