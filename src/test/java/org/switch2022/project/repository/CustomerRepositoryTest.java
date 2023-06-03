package org.switch2022.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.switch2022.project.datamodel.JPA.CustomerJPA;
import org.switch2022.project.datamodel.JPA.assemblers.CustomerDomainDataAssembler;
import org.switch2022.project.model.customer.CustomerDDD;
import org.switch2022.project.model.valueobject.CustomerDesignation;
import org.switch2022.project.model.valueobject.CustomerID;
import org.switch2022.project.model.valueobject.CustomerNIF;
import org.switch2022.project.repository.JPA.CustomerRepositoryJPA;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class CustomerRepositoryTest {

    @MockBean
    CustomerRepositoryJPA customerRepositoryJPA;

    @MockBean
    CustomerDomainDataAssembler customerDomainDataAssembler;

    @InjectMocks
    CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Ensure that customer was successfully saved.")
    @Test
    void saveCustomerSuccess() {

        //Arrange
        CustomerNIF customerNIF = mock(CustomerNIF.class);
        when(customerNIF.toString()).thenReturn("");
        CustomerDesignation customerDesignation = mock(CustomerDesignation.class);
        CustomerDDD customer = mock(CustomerDDD.class);
        when(customer.getCustomerNIF()).thenReturn(customerNIF);

        CustomerJPA customerJPA = new CustomerJPA(customerNIF.toString(), customerDesignation.toString());
        CustomerJPA savedCustomerJPA = new CustomerJPA(customerNIF.toString(), customerDesignation.toString());

        when(customerRepositoryJPA.existsByCustomerNIF(customer.getCustomerNIF().toString())).thenReturn(false);
        when(customerDomainDataAssembler.toData(customer)).thenReturn(customerJPA);
        when(customerRepositoryJPA.save(customerJPA)).thenReturn(savedCustomerJPA);
        when(customerDomainDataAssembler.toDomain(savedCustomerJPA)).thenReturn(customer);

        //Act
        CustomerDDD result = customerRepository.save(customer);

        //Assert
        assertEquals(customer,result);
    }
    @DisplayName("Ensure that the same customer cannot saved - throws exception")
    @Test
    void saveTwoCustomerFails() {
        //Arrange
        CustomerNIF customerNIF = mock(CustomerNIF.class);
        when(customerNIF.toString()).thenReturn("306948337");
        CustomerDesignation customerDesignation = mock(CustomerDesignation.class);
        CustomerDDD customer = mock(CustomerDDD.class);
        when(customer.getCustomerNIF()).thenReturn(customerNIF);

        CustomerJPA customerJPA = new CustomerJPA(customerNIF.toString(), customerDesignation.toString());
        CustomerJPA savedCustomerJPA = new CustomerJPA(customerNIF.toString(), customerDesignation.toString());

        when(customerRepositoryJPA.existsByCustomerNIF(customer.getCustomerNIF().toString())).thenReturn(true);
        when(customerDomainDataAssembler.toData(customer)).thenReturn(customerJPA);
        when(customerRepositoryJPA.save(customerJPA)).thenReturn(savedCustomerJPA);
        when(customerDomainDataAssembler.toDomain(savedCustomerJPA)).thenReturn(customer);

        String expectedMessage = "There is a customer with this NIF.";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
             customerRepository.save(customer);
        });

        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("Ensure that getAll method was successfully returned.")
    @Test
    void getAllCustomersSuccess() {

        //Arrange
        CustomerDDD customer = mock(CustomerDDD.class);
        CustomerJPA customerJPA = new CustomerJPA("300123321","test");

        List<CustomerJPA> listJPA = new ArrayList<>();
        listJPA.add(customerJPA);

        ArrayList<CustomerDDD> expected = new ArrayList<>();
        expected.add(customer);

        when(customerRepositoryJPA.findAll()).thenReturn(listJPA);

        when(customerDomainDataAssembler.toDomain(any())).thenReturn(customer);

        //Act
        ArrayList<CustomerDDD> result = customerRepository.getAll();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Should return true when customerID exists")
    void returnsTrueWhenCustomerIDExists(){
        //Arrange
        CustomerID customerID = mock(CustomerID.class);
        when(customerID.getId()).thenReturn(1000L);
        when(customerRepositoryJPA.existsById(customerID.getId())).thenReturn(true);

        //Act
        boolean result = customerRepositoryJPA.existsById(customerID.getId());

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false when customerID does not exist")
    void returnsFalseWhenCustomerIDDoesNotExist(){
        //Arrange
        CustomerID customerID = mock(CustomerID.class);
        when(customerID.getId()).thenReturn(1000L);
        when(customerRepositoryJPA.existsById(customerID.getId())).thenReturn(false);

        //Act
        boolean result = customerRepositoryJPA.existsById(customerID.getId());

        //Assert
        assertFalse(result);
    }

}