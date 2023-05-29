package org.switch2022.project.model.businessSector;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BusinessSectorDDDTest {
    @Test
    @DisplayName("BusinessSector constructor throws exception with null BusinessSectorDesignation")
    void nullBusinessSectorDesignationThrowsException() {
        //Arrange
        BusinessSectorID businessSectorID = mock(BusinessSectorID.class);
        String expectedMessage = "businessSectorDesignation, cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new BusinessSectorDDD(businessSectorID, null);
                }
        );

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Test for a successful creation of BusinessSectorDDD")
    public void checkIfTheCostumerDDDIsSuccessfulCreated(){
        //Arrange
        BusinessSectorID businessSectorID = mock(BusinessSectorID.class);
        BusinessSectorDesignation businessSectorDesignation = mock(BusinessSectorDesignation.class);

        //Act
        BusinessSectorDDD businessSector = new BusinessSectorDDD(businessSectorID,businessSectorDesignation);

        //Assert
        assertInstanceOf(BusinessSectorDDD.class, businessSector);
    }

}