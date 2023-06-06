package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerIDTest {
    @Test
    @DisplayName("Ensure id is returned")
    void ensureIdIsReturned(){
        //Arrange
        long expectedId = 1L;
        CustomerID customerID = new CustomerID(expectedId);

        //Act
        long resultId = customerID.getId();

        //Assert
        assertEquals(expectedId, resultId);
    }

}