package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessSectorIDTest {

    @Test
    @DisplayName("Ensure id is returned")
    void ensureIdIsReturned(){
        //Arrange
        long expectedId = 1L;
        BusinessSectorID businessSectorID = new BusinessSectorID(expectedId);

        //Act
        long resultId = businessSectorID.getId();

        //Assert
        assertEquals(expectedId, resultId);
    }

}