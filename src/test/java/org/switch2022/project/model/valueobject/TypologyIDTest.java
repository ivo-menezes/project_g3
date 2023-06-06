package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TypologyIDTest {

    @Test
    @DisplayName("Ensure id is returned")
    void ensureIdIsReturned(){
        //Arrange
        long expectedId = 1L;
        TypologyID typologyID = new TypologyID(expectedId);

        //Act
        long resultId = typologyID.getId();

        //Assert
        assertEquals(expectedId, resultId);
    }
}