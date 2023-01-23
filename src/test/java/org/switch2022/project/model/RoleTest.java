package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    @DisplayName("ensure return code Role")
    void ensureGetDescription() {

        //arrange
        String description = "Team Member";
        Role role = new Role(description);

        //act
        String result = role.getDescription();

        //assert
        assertEquals(description, result);
    }
}