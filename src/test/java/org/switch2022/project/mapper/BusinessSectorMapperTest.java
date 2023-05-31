package org.switch2022.project.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.BusinessSectorDesignation;
import org.switch2022.project.model.valueobject.BusinessSectorID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BusinessSectorMapperTest {
    @Test
    @DisplayName("Ensure BusinessSectorDTO is converted to BusinessSectorOutputDTO correctly.")
    void toOutputDTOSuccess() {
        //Arrange
        BusinessSectorID businessSectorID = mock(BusinessSectorID.class);
        when(businessSectorID.getId()).thenReturn(Long.valueOf(1));

        BusinessSectorDesignation businessSectorDesignation = mock(BusinessSectorDesignation.class);
        when(businessSectorDesignation.toString()).thenReturn("Test");

        BusinessSectorDTO businessSectorDTO = mock(BusinessSectorDTO.class);
        businessSectorDTO.businessSectorID = businessSectorID;
        businessSectorDTO.businessSectorDesignation = businessSectorDesignation;

        BusinessSectorMapper businessSectorMapper = new BusinessSectorMapper();
        BusinessSectorOutputDTO businessSectorOutputDTO = new BusinessSectorOutputDTO(Long.valueOf(1),"Test");

        //Act
        BusinessSectorOutputDTO result = businessSectorMapper.toOutputDTO(businessSectorDTO);

        //Assert
        assertEquals(businessSectorOutputDTO, result);
    }

    @Test
    @DisplayName("Ensure BusinessSectorDTO is converted to BusinessSectorOutputDTO correctly with customerID equals null")
    void toOutputDTOWithIDNull() {
        //Arrange
        BusinessSectorDesignation businessSectorDesignation = mock(BusinessSectorDesignation.class);
        when(businessSectorDesignation.toString()).thenReturn("Test");

        BusinessSectorDTO businessSectorDTO = mock(BusinessSectorDTO.class);
        businessSectorDTO.businessSectorDesignation = businessSectorDesignation;

        BusinessSectorMapper businessSectorMapper = new BusinessSectorMapper();
        BusinessSectorOutputDTO businessSectorOutputDTO = new BusinessSectorOutputDTO(null,"Test");

        //Act
        BusinessSectorOutputDTO result = businessSectorMapper.toOutputDTO(businessSectorDTO);

        //Assert
        assertEquals(businessSectorOutputDTO, result);
    }
}