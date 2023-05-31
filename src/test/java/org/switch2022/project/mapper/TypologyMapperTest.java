package org.switch2022.project.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TypologyMapperTest {

    @Test
    @DisplayName("Ensure TypologyDTO is converted to TypologyOutputDTO correctly.")
    void toOutputDTOSuccess() {
        //Arrange
        TypologyID typologyID = mock(TypologyID.class);
        when(typologyID.getId()).thenReturn(Long.valueOf(1));

        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);
        when(typologyDesignation.toString()).thenReturn("Test");

        TypologyDTO typologyDTO = mock(TypologyDTO.class);
        typologyDTO.typologyID = typologyID;
        typologyDTO.typologyDesignation = typologyDesignation;

        TypologyMapper typologyMapper = new TypologyMapper();

        TypologyOutputDTO typologyOutputDTO = new TypologyOutputDTO(Long.valueOf(1),"Test");

        //Act
        TypologyOutputDTO result = typologyMapper.toOutputDTO(typologyDTO);

        //Assert
        assertEquals(typologyOutputDTO, result);
    }

    @Test
    @DisplayName("Ensure TypologyDTO is converted to TypologyOutputDTO correctly with customerID equals null")
    void toOutputDTOWithIDNull() {
        //Arrange
        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);
        when(typologyDesignation.toString()).thenReturn("Test");

        TypologyDTO typologyDTO = mock(TypologyDTO.class);
        typologyDTO.typologyDesignation = typologyDesignation;

        TypologyMapper typologyMapper = new TypologyMapper();

        TypologyOutputDTO typologyOutputDTO = new TypologyOutputDTO(null, "Test");

        //Act
        TypologyOutputDTO result = typologyMapper.toOutputDTO(typologyDTO);

        //Assert
        assertEquals(typologyOutputDTO, result);
    }
}