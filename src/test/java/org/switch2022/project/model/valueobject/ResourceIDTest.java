package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceIDTest {

    @Test
    @DisplayName("Ensure that get a ResourceID")
    void ensureThatGetResourceId() {
        Long expectedResourceID = 26L;
        ResourceID resourceID = new ResourceID(expectedResourceID);

        Long resultResourceID = resourceID.getId();

        assertEquals(expectedResourceID, resultResourceID);

    }
}