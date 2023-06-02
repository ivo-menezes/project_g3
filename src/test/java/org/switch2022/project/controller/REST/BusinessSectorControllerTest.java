package org.switch2022.project.controller.REST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.switch2022.project.mapper.BusinessSectorDTO;
import org.switch2022.project.mapper.BusinessSectorMapper;
import org.switch2022.project.mapper.BusinessSectorOutputDTO;
import org.switch2022.project.service.BusinessSectorService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ActiveProfiles("test")
@SpringBootTest
class BusinessSectorControllerTest {

    @MockBean
    BusinessSectorMapper businessSectorMapper;
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
        ResponseEntity<BusinessSectorOutputDTO> responseEntity = businessSectorController.createBusinessSector(businessSectorDTO);

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
        when(businessSectorService.createBusinessSector(businessSectorDTO)).thenThrow(new InvalidDataAccessApiUsageException(""));

        //Act
        ResponseEntity<BusinessSectorOutputDTO> responseEntity = businessSectorController.createBusinessSector(businessSectorDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    @DisplayName("Ensure the getAll method was successfully returned")
    @Test
    void getAllBusinessSectorSuccess() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        BusinessSectorDTO businessSectorDTO = mock(BusinessSectorDTO.class);
        ArrayList<BusinessSectorDTO> listDTO = new ArrayList<>();
        listDTO.add(businessSectorDTO);

        when(businessSectorService.getAll()).thenReturn(listDTO);

        //Act
        ResponseEntity<ArrayList<BusinessSectorOutputDTO>> responseEntity = businessSectorController.getAll();

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }

    @DisplayName("Ensure the getAll method return http status code 400")
    @Test
    void getAllBusinessSectorFails() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(businessSectorService.getAll()).thenThrow(new InvalidDataAccessApiUsageException(""));

        //Act
        ResponseEntity<ArrayList<BusinessSectorOutputDTO>> responseEntity = businessSectorController.getAll();

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
}