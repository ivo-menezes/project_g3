package org.switch2022.project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.switch2022.project.datamodel.JPA.CustomerJPA;
import org.switch2022.project.datamodel.JPA.assemblers.CustomerDomainDataAssembler;
import org.switch2022.project.model.customer.CustomerDDD;
import org.switch2022.project.model.valueobject.CustomerID;
import org.switch2022.project.repository.JPA.CustomerRepositoryJPA;
import org.switch2022.project.service.irepositories.ICustomerRepository;

import java.util.ArrayList;

@Repository
public class CustomerRepository implements ICustomerRepository {

    @Autowired
    CustomerRepositoryJPA customerRepositoryJPA;

    @Autowired
    CustomerDomainDataAssembler customerDomainDataAssembler;

    /**
     * Method responsible for saving the client in the database.
     * @param customer
     * @return customerDDD
     */
    public CustomerDDD save(CustomerDDD customer) {

         boolean existCustomer = customerRepositoryJPA.existsByCustomerNIF(customer.getCustomerNIF().toString());

        if (existCustomer) {
            throw new IllegalArgumentException("There is a customer with this NIF.");
        }

        CustomerJPA customerJPA = customerDomainDataAssembler.toData(customer);
        CustomerJPA savedCustomerJPA = customerRepositoryJPA.save(customerJPA);

        return customerDomainDataAssembler.toDomain(savedCustomerJPA);
    }

    /**
     * Method responsible for return all customers from database.
     * @return ArrayList<customerDDD>
     */
    public ArrayList<CustomerDDD> getAll() {
        ArrayList<CustomerDDD> customers = new ArrayList();

        Iterable<CustomerJPA> customersJPA = customerRepositoryJPA.findAll();

        for (CustomerJPA customer : customersJPA) {
            customers.add(customerDomainDataAssembler.toDomain(customer));
        }

        return customers;
    }

    /**
     * Verifies if this id exists
     * @param customerID the id to be checked
     * @return true if it already exists, false otherwise
     */
    public boolean containsID(CustomerID customerID) {
        return customerRepositoryJPA.existsById(customerID.getId());
    }
}
