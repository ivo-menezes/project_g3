package org.switch2022.project.model.role;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    @DisplayName("ensure return name Role")
    void ensureGetDescription() {

        //arrange
        String name = "Team Member";
        Role role = new Role(name);

        //act
        String result = role.getName();

        //assert
        assertEquals(name, result);
    }
}