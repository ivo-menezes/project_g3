package org.switch2022.project.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.switch2022.project.mapper.CustomerDTO;
import org.switch2022.project.model.customer.CustomerDDD;
import org.switch2022.project.model.customer.ICustomerFactory;
import org.switch2022.project.service.irepositories.ICustomerRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class CustomerServiceTest {

    @MockBean
    ICustomerFactory customerFactory;

    @MockBean
    ICustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @DisplayName("assert that creating a CustomerService with null CustomerFactory throws Exception")
    @Test
    void createCustomerNullFactoryThrowsException() {
        //Arrange
        ICustomerFactory factory = null;
        ICustomerRepository customerRepositoryDouble = mock(ICustomerRepository.class);
        String expectedMessage = "customerFactory must not be null.";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new CustomerService(factory, customerRepositoryDouble);
        });
        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that creating a CustomerService with null CustomerRepository throws Exception")
    @Test
    void createCustomerNullRepositoryThrowsException() {
        //Arrange
        ICustomerFactory factoryDouble = mock(ICustomerFactory.class);
        ICustomerRepository customerRepositoryDouble = null;
        String expectedMessage = "customerRepository must not be null.";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new CustomerService(factoryDouble, customerRepositoryDouble);
        });
        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that creating a customer succeeds")
    @Test
    void createCustomerSuccess() {
        //Arrange
        CustomerDTO customer = mock(CustomerDTO.class);
        CustomerDDD customerDDD = mock(CustomerDDD.class);

        ICustomerFactory factory = mock(ICustomerFactory.class);
        when(factory.createCustomer(any(),any(),any())).thenReturn(customerDDD);

        ICustomerRepository repository = mock(ICustomerRepository.class);
        when(repository.save(customerDDD)).thenReturn(customerDDD);

        CustomerService service = new CustomerService(factory,repository);

        //Act
        CustomerDTO result = service.createCustomer(customer);

        //Assert
        assertEquals(customer,result);
    }
}