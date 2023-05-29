package org.switch2022.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.datamodel.JPA.CustomerJPA;
import org.switch2022.project.datamodel.JPA.assemblers.CustomerDomainDataAssembler;
import org.switch2022.project.model.customer.CustomerDDD;
import org.switch2022.project.model.valueobject.CustomerDesignation;
import org.switch2022.project.model.valueobject.CustomerNIF;
import org.switch2022.project.repository.JPA.CustomerRepositoryJPA;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

}