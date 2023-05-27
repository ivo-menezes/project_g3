package org.switch2022.project.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.mapper.TypologyDTO;
import org.switch2022.project.model.typology.ITypologyFactory;
import org.switch2022.project.model.typology.TypologyDDD;
import org.switch2022.project.model.valueobject.TypologyDesignation;
import org.switch2022.project.model.valueobject.TypologyID;
import org.switch2022.project.service.irepositories.ITypologyRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class TypologyServiceTest {

    @MockBean
    ITypologyFactory factory;

    @MockBean
    ITypologyRepository repository;

    @Autowired
    TypologyService service;

    @Test
    @DisplayName("Ensure Service is correctly instantiated")
    void ensureServiceIsCreated(){

        assertInstanceOf(TypologyService.class, service);
    }

    @Test
    @DisplayName("Ensure exception is returned when factory is null")
    void ensureExceptionReturnedWhenFactoryNull(){
        //Arrange
        ITypologyFactory factory = null;

        String expectedMessage = "TypologyFactory must not be null";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TypologyService(factory, repository));
        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Ensure exception is returned when repository is null")
    void ensureExceptionReturnedWhenRepositoryNull(){
        //Arrange
        ITypologyRepository repository = null;

        String expectedMessage = "TypologyRepository must not be null";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TypologyService(factory, repository));
        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Ensure typology is successfully created")
    void ensureTypologyIsCreated(){
        //Arrange
        TypologyID typologyID = mock(TypologyID.class);
        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);
        TypologyDDD typology = mock(TypologyDDD.class);
        TypologyDDD savedTypology = mock(TypologyDDD.class);
        TypologyDTO typologyDTO = mock(TypologyDTO.class);

        typologyDTO.typologyID = typologyID;
        typologyDTO.typologyDesignation = typologyDesignation;

        when(factory.createTypology(typologyID, typologyDesignation)).thenReturn(typology);
        when(repository.save(typology)).thenReturn(savedTypology);

        //Act
        TypologyDTO result = service.createTypology(typologyDTO);

        //Assert
        assertEquals(typologyDTO, result);
    }
}