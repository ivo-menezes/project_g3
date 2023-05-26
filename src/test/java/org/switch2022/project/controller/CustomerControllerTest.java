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
import org.switch2022.project.mapper.CustomerDTO;
import org.switch2022.project.service.CustomerService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerControllerTest {

    @MockBean
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }
    @DisplayName("Ensure that the customer was successfully created.")
    @Test
    void createCustomerSuccess() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        CustomerDTO customerDTO = mock(CustomerDTO.class);

        when(customerService.createCustomer(customerDTO)).thenReturn(customerDTO);

        //Act
        ResponseEntity<CustomerDTO> responseEntity = customerController.createCustomer(customerDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);

    }

    @DisplayName("Ensure customer creation failed.")
    @Test
    void createCustomerFails() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        CustomerDTO customerDTO = mock(CustomerDTO.class);
        when(customerService.createCustomer(customerDTO)).thenThrow(new IllegalArgumentException(""));

        //Act
        ResponseEntity<CustomerDTO> responseEntity = customerController.createCustomer(customerDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
}