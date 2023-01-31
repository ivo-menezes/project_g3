package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ResourceDTOTest {

    @Test
    @DisplayName("test Equals for true")
    void testEqualsTrue() {
        // arrange
        String email1 = "deborah@gmail.com";
        String role = "Team Member";
        ResourceDTO resourceDTO_1 = new ResourceDTO(email1, role);
        ResourceDTO resourceDTO_2 = new ResourceDTO(email1, role);
        // assert
        assertEquals(resourceDTO_1, resourceDTO_2);
    }

    @Test
    @DisplayName("test Equals for false" )
    void testEqualsFalse() {
        // arrange
        String email1 = "deborah@gmail.com";
        String email2 = "pedro@gmail.com";
        String role = "Team Member";
        ResourceDTO resourceDTO_1 = new ResourceDTO(email1, role);
        ResourceDTO resourceDTO_2 = new ResourceDTO(email2, role);
        // assert
        assertNotEquals(resourceDTO_1, resourceDTO_2);
    }
}