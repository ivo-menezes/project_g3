package org.switch2022.project.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.*;

import java.util.ArrayList;

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

        //Act
        TypologyOutputDTO result = typologyMapper.toOutputDTO(typologyDTO);

        //Assert
        assertInstanceOf(TypologyOutputDTO.class, result);
    }

    @Test
    @DisplayName("Ensure TypologyDTO is converted to TypologyOutputDTO correctly with typologyID equals null")
    void toOutputDTOWithIDNull() {
        //Arrange
        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);
        when(typologyDesignation.toString()).thenReturn("Test");

        TypologyDTO typologyDTO = mock(TypologyDTO.class);
        typologyDTO.typologyDesignation = typologyDesignation;

        TypologyMapper typologyMapper = new TypologyMapper();

        //Act
        TypologyOutputDTO output = typologyMapper.toOutputDTO(typologyDTO);
        Long result = output.typologyId;

        //Assert
        assertNull(result);
    }

    @Test
    @DisplayName("Ensure ArrayList<TypologyDTO> is converted to ArrayList<TypologyOutputDTO> correctly.")
    void toOutputDTOListSuccess() {
        //Arrange
        TypologyID typologyID = mock(TypologyID.class);
        when(typologyID.getId()).thenReturn(Long.valueOf(1));

        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);
        when(typologyDesignation.toString()).thenReturn("Test");

        TypologyDTO typologyDTO = mock(TypologyDTO.class);
        typologyDTO.typologyID = typologyID;
        typologyDTO.typologyDesignation = typologyDesignation;

        ArrayList<TypologyDTO> listDTO = new ArrayList<>();
        listDTO.add(typologyDTO);

        TypologyMapper typologyMapper = new TypologyMapper();

        //Act
        ArrayList<TypologyOutputDTO> result = typologyMapper.toOutputDTO(listDTO);
        TypologyOutputDTO typologyOutputDTO1= result.get(0);

        //Assert
        assertInstanceOf(TypologyOutputDTO.class, typologyOutputDTO1);
    }
}