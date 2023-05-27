package org.switch2022.project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.switch2022.project.datamodel.JPA.CustomerJPA;
import org.switch2022.project.datamodel.JPA.assemblers.CustomerDomainDataAssembler;
import org.switch2022.project.model.customer.CustomerDDD;
import org.switch2022.project.repository.JPA.CustomerRepositoryJPA;
import org.switch2022.project.service.irepositories.ICustomerRepository;

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
            throw new IllegalArgumentException("There is a customer with this ID.");
        }

        CustomerJPA customerJPA = customerDomainDataAssembler.toData(customer);
        CustomerJPA savedCustomerJPA = customerRepositoryJPA.save(customerJPA);

        return customerDomainDataAssembler.toDomain(savedCustomerJPA);
    }
}
