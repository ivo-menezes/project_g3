package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.mapper.CustomerDTO;
import org.switch2022.project.model.customer.CustomerDDD;
import org.switch2022.project.model.customer.ICustomerFactory;
import org.switch2022.project.service.irepositories.ICustomerRepository;

import java.util.ArrayList;

@Service
public class CustomerService {

    @Autowired
    private ICustomerFactory customerFactory;

    @Autowired
    private ICustomerRepository customerRepository;

    /**
     * Default constructor that receives the factory and repository as dependency injection.
     * @param customerFactory
     * @param customerRepository
     */
    public CustomerService(ICustomerFactory customerFactory, ICustomerRepository customerRepository) {

        if (customerFactory == null) {
            throw new IllegalArgumentException("customerFactory must not be null.");
        }
        if (customerRepository == null) {
            throw  new IllegalArgumentException("customerRepository must not be null.");
        }
        this.customerFactory = customerFactory;
        this.customerRepository = customerRepository;
    }


    /**
     * Method responsible for creating and saving the customer.
     * @param customerDTO
     * @return the customerDTO.
     */
    public CustomerDTO createCustomer (CustomerDTO customerDTO) {

        CustomerDDD customer= customerFactory.createCustomer(customerDTO.customerID,customerDTO.customerNIF,customerDTO.customerDesignation);
        CustomerDDD customerDDDSaved = customerRepository.save(customer);
        customerDTO.customerID=customerDDDSaved.identity();

        return customerDTO;
    }

    /**
     * Method responsible for return all customers.
     * @return ArrayList<CustomerDTO>.
     */
    public ArrayList<CustomerDTO> getAll () {
        ArrayList<CustomerDTO> customers = new ArrayList();
        ArrayList<CustomerDDD> customersDDD = customerRepository.getAll();

        for (CustomerDDD customer : customersDDD) {

            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.customerID = customer.identity();
            customerDTO.customerNIF = customer.getCustomerNIF();
            customerDTO.customerDesignation = customer.getCustomerDesignation();

            customers.add(customerDTO);
        }

        return customers;
    }
}
