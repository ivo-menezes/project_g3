package org.switch2022.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.switch2022.project.mapper.BusinessSectorDTO;
import org.switch2022.project.service.BusinessSectorService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class BusinessSectorControllerTest {

    @MockBean
    BusinessSectorService businessSectorService;

    @InjectMocks
    BusinessSectorController businessSectorController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Ensure that the businessSector was successfully created.")
    @Test
    void createCustomerSuccess() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        BusinessSectorDTO businessSectorDTO = mock(BusinessSectorDTO.class);

        when(businessSectorService.createBusinessSector(businessSectorDTO)).thenReturn(businessSectorDTO);

        //Act
        ResponseEntity<BusinessSectorDTO> responseEntity = businessSectorController.createBusinessSector(businessSectorDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);
    }

    @DisplayName("Ensure customer creation failed.")
    @Test
    void createCustomerFails() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        BusinessSectorDTO businessSectorDTO = mock(BusinessSectorDTO.class);
        when(businessSectorService.createBusinessSector(businessSectorDTO)).thenThrow(new IllegalArgumentException(""));

        //Act
        ResponseEntity<BusinessSectorDTO> responseEntity = businessSectorController.createBusinessSector(businessSectorDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
}