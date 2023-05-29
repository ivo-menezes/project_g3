package org.switch2022.project.model.businessSector;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BusinessSectorFactoryImplTest {

    @DisplayName("assert that creating BusinessSector succeeds")
    @Test
    void createBusinessSectorSucceeds() {
        //Arrange
        BusinessSectorID businessSectorIDDouble = mock(BusinessSectorID.class);
        BusinessSectorDesignation businessSectorDesignationDouble = mock(BusinessSectorDesignation.class);

        BusinessSectorFactoryImpl factory = new BusinessSectorFactoryImpl();

        //Act
        BusinessSectorDDD businessSector = factory.createBusinessSector(businessSectorIDDouble,businessSectorDesignationDouble);

        //Assert
        assertInstanceOf(BusinessSectorDDD.class, businessSector);
    }

    @DisplayName("assert that trying to create businessSector with null businessSectorDesignation throws Exception")
    @Test
    void createBusinessSectorNullBusinessSectorDesignationThrowsException() {
        //Arrange
        BusinessSectorID businessSectorIDDouble = mock(BusinessSectorID.class);
        BusinessSectorDesignation businessSectorDesignationDouble = null;

        BusinessSectorFactoryImpl factory = new BusinessSectorFactoryImpl();

        String expectedMessage = "businessSectorDesignation cannot be null";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createBusinessSector(businessSectorIDDouble, businessSectorDesignationDouble);
        });

        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }
}