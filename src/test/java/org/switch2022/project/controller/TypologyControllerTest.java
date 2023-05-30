package org.switch2022.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.switch2022.project.mapper.TypologyDTO;
import org.switch2022.project.mapper.TypologyMapper;
import org.switch2022.project.mapper.TypologyOutputDTO;
import org.switch2022.project.service.TypologyService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class TypologyControllerTest {

    @MockBean
    TypologyService service;

    @MockBean
    TypologyMapper typologyMapper;

    @Autowired
    TypologyController controller;

    @Test
    @DisplayName("Ensure controller is correctly instantiated")
    void ensureControllerIsCorrectlyInstantiated(){

        assertInstanceOf(TypologyController.class, controller);
    }

    @Test
    @DisplayName("Ensure typology creation succeeds")
    void ensureTypologyCreationSucceeds(){
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        TypologyDTO typologyDto = mock(TypologyDTO.class);
        TypologyOutputDTO typologyOutputDTO = mock(TypologyOutputDTO.class);

        when(service.createTypology(typologyDto)).thenReturn(typologyDto);
        when(typologyMapper.toOutputDTO(any())).thenReturn(typologyOutputDTO);

        //Act
        ResponseEntity<TypologyOutputDTO> responseEntity = controller.createTypology(typologyDto);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);
    }

    @Test
    @DisplayName("Ensure typology creation fails")
    void ensureTypologyCreationFails(){
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        TypologyDTO typologyDto = mock(TypologyDTO.class);
        TypologyOutputDTO typologyOutputDTO = mock(TypologyOutputDTO.class);

        //trigger to fail
        when(service.createTypology(typologyDto)).thenThrow(new InvalidDataAccessApiUsageException(""));
        when(typologyMapper.toOutputDTO(any())).thenReturn(typologyOutputDTO);

        //Act
        ResponseEntity<TypologyOutputDTO> responseEntity = controller.createTypology(typologyDto);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
}