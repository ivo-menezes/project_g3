package org.switch2022.project.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.CustomerDesignation;
import org.switch2022.project.model.valueobject.CustomerID;
import org.switch2022.project.model.valueobject.CustomerNIF;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerMapperTest {

    @Test
    @DisplayName("Ensure CustomerDTO is converted to CostumerOutputDTO correctly.")
    void toOutputDTOSuccess() {
        //Arrange
        CustomerID customerID = mock(CustomerID.class);
        when(customerID.getId()).thenReturn(Long.valueOf(1));

        CustomerNIF customerNIF = mock(CustomerNIF.class);
        when(customerNIF.toString()).thenReturn("306123987");

        CustomerDesignation customerDesignation = mock(CustomerDesignation.class);
        when(customerDesignation.toString()).thenReturn("Test");

        CustomerDTO customerDTO = mock(CustomerDTO.class);
        customerDTO.customerID = customerID;
        customerDTO.customerNIF = customerNIF;
        customerDTO.customerDesignation=customerDesignation;

        CustomerMapper customerMapper = new CustomerMapper();

        CustomerOutputDTO customerOutputDTO = new CustomerOutputDTO(Long.valueOf(1),"306123987","Test");

        //Act
        CustomerOutputDTO result = customerMapper.toOutputDTO(customerDTO);

        //Assert
        assertEquals(customerOutputDTO, result);
    }

    @Test
    @DisplayName("Ensure CustomerDTO is converted to CostumerOutputDTO correctly with customerID equals null")
    void toOutputDTOWithIDNull() {
        //Arrange

        CustomerNIF customerNIF = mock(CustomerNIF.class);
        when(customerNIF.toString()).thenReturn("306123987");

        CustomerDesignation customerDesignation = mock(CustomerDesignation.class);
        when(customerDesignation.toString()).thenReturn("Test");

        CustomerDTO customerDTO = mock(CustomerDTO.class);
        customerDTO.customerNIF = customerNIF;
        customerDTO.customerDesignation=customerDesignation;

        CustomerMapper customerMapper = new CustomerMapper();

        CustomerOutputDTO customerOutputDTO = new CustomerOutputDTO(null,"306123987","Test");

        //Act
        CustomerOutputDTO result = customerMapper.toOutputDTO(customerDTO);

        //Assert
        assertEquals(customerOutputDTO, result);
    }
    @Test
    @DisplayName("Ensure ArrayList<CustomerDTO> is converted to ArrayList<CostumerOutputDTO> correctly.")
    void toOutputDTOListSuccess() {
        //Arrange
        CustomerID customerID = mock(CustomerID.class);
        when(customerID.getId()).thenReturn(Long.valueOf(1));

        CustomerNIF customerNIF = mock(CustomerNIF.class);
        when(customerNIF.toString()).thenReturn("306123987");

        CustomerDesignation customerDesignation = mock(CustomerDesignation.class);
        when(customerDesignation.toString()).thenReturn("Test");

        CustomerDTO customerDTO = mock(CustomerDTO.class);
        customerDTO.customerID = customerID;
        customerDTO.customerNIF = customerNIF;
        customerDTO.customerDesignation=customerDesignation;

        ArrayList<CustomerDTO> listDTO = new ArrayList<>();
        listDTO.add(customerDTO);

        CustomerMapper customerMapper = new CustomerMapper();

        CustomerOutputDTO customerOutputDTO = new CustomerOutputDTO(Long.valueOf(1),"306123987","Test");

        ArrayList<CustomerOutputDTO> expected = new ArrayList<>();
        expected.add(customerOutputDTO);
        //Act
        ArrayList<CustomerOutputDTO> result = customerMapper.toOutputDTO(listDTO);

        //Assert
        assertEquals(expected, result);
    }
}