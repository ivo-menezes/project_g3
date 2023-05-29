package org.switch2022.project.datamodel.JPA;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessSectorJPATest {

    @Test
    @DisplayName("BusinessSectorJPA constructor throws exception with null  businessSectorDesignation")
    void nullBusinessSectorDesignationThrowsException() {
        //Arrange
        String businessSectorDesignation = null;
        String expectedMessage = "businessSectorDesignation cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new BusinessSectorJPA(businessSectorDesignation);
                }
        );

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Test for a successful creation of BusinessSectorJPA")
    public void checkIfTheCostumerJPAIsSuccessfulCreated(){
        //Arrange
        String businessSectorDesignation = "Fixed Cost";

        //Act
        BusinessSectorJPA businessSector = new BusinessSectorJPA(businessSectorDesignation);

        //Assert
        assertInstanceOf(BusinessSectorJPA.class, businessSector);
    }

}