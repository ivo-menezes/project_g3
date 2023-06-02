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
import org.switch2022.project.mapper.*;
import org.switch2022.project.service.CustomerService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ActiveProfiles("test")
@SpringBootTest
class CustomerControllerTest {

    @MockBean
    CustomerService customerService;

    @MockBean
    CustomerMapper customerMapper;

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
        CustomerOutputDTO customerOutputDTO = mock(CustomerOutputDTO.class);

        when(customerService.createCustomer(customerDTO)).thenReturn(customerDTO);
        when(customerMapper.toOutputDTO(any(CustomerDTO.class))).thenReturn(customerOutputDTO);
        //Act
        ResponseEntity<CustomerOutputDTO> responseEntity = customerController.createCustomer(customerDTO);

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
        CustomerOutputDTO customerOutputDTO = mock(CustomerOutputDTO.class);

        when(customerService.createCustomer(customerDTO)).thenThrow(new InvalidDataAccessApiUsageException(""));
        when(customerMapper.toOutputDTO(any(CustomerDTO.class))).thenReturn(customerOutputDTO);
        //Act
        ResponseEntity<CustomerOutputDTO> responseEntity = customerController.createCustomer(customerDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    @DisplayName("Ensure the getAll method was successfully returned")
    @Test
    void getAllCustomerSuccess() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        CustomerDTO customerDTO = mock(CustomerDTO.class);
        ArrayList<CustomerDTO> listDTO = new ArrayList<>();
        listDTO.add(customerDTO);

        when(customerService.getAll()).thenReturn(listDTO);

        //Act
        ResponseEntity<ArrayList<CustomerOutputDTO>> responseEntity = customerController.getAll();

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }

    @DisplayName("Ensure the getAll method return http status code 400")
    @Test
    void getAllCustomerFails() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(customerService.getAll()).thenThrow(new InvalidDataAccessApiUsageException(""));

        //Act
        ResponseEntity<ArrayList<CustomerOutputDTO>> responseEntity = customerController.getAll();

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
}