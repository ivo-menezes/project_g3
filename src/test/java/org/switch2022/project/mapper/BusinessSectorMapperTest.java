package org.switch2022.project.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.BusinessSectorDesignation;
import org.switch2022.project.model.valueobject.BusinessSectorID;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
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

        //Act
        BusinessSectorOutputDTO result = businessSectorMapper.toOutputDTO(businessSectorDTO);

        //Assert
        assertInstanceOf(BusinessSectorOutputDTO.class, result);
    }

    @Test
    @DisplayName("Ensure BusinessSectorDTO is converted to BusinessSectorOutputDTO correctly with businessSectorID equals null")
    void toOutputDTOWithIDNull() {
        //Arrange
        BusinessSectorDesignation businessSectorDesignation = mock(BusinessSectorDesignation.class);
        when(businessSectorDesignation.toString()).thenReturn("Test");

        BusinessSectorDTO businessSectorDTO = mock(BusinessSectorDTO.class);
        businessSectorDTO.businessSectorDesignation = businessSectorDesignation;

        BusinessSectorMapper businessSectorMapper = new BusinessSectorMapper();

        //Act
        BusinessSectorOutputDTO output = businessSectorMapper.toOutputDTO(businessSectorDTO);
        Long result = output.businessSectorId;

        //Assert
        assertNull(result);
    }

    @Test
    @DisplayName("Ensure ArrayList<BusinessSectorDTO> is converted to ArrayList<BusinessSectorOutputDTO> correctly.")
    void toOutputDTOListSuccess() {
        //Arrange
        BusinessSectorID businessSectorID = mock(BusinessSectorID.class);
        when(businessSectorID.getId()).thenReturn(Long.valueOf(1));

        BusinessSectorDesignation businessSectorDesignation = mock(BusinessSectorDesignation.class);
        when(businessSectorDesignation.toString()).thenReturn("Test");

        BusinessSectorDTO businessSectorDTO = mock(BusinessSectorDTO.class);
        businessSectorDTO.businessSectorID = businessSectorID;
        businessSectorDTO.businessSectorDesignation = businessSectorDesignation;

        ArrayList<BusinessSectorDTO> listDTO = new ArrayList<>();
        listDTO.add(businessSectorDTO);

        BusinessSectorMapper businessSectorMapper = new BusinessSectorMapper();

        //Act
        ArrayList<BusinessSectorOutputDTO> result = businessSectorMapper.toOutputDTO(listDTO);
        BusinessSectorOutputDTO businessSectorOutputDTO = result.get(0);

        //Assert
        assertInstanceOf(BusinessSectorOutputDTO.class, businessSectorOutputDTO);
    }
}