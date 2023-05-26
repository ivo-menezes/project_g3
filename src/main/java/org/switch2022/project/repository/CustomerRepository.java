package org.switch2022.project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.switch2022.project.datamodel.JPA.CustomerJPA;
import org.switch2022.project.datamodel.JPA.assemblers.CustomerDomainDataAssembler;
import org.switch2022.project.model.customer.CustomerDDD;
import org.switch2022.project.repository.JPA.CustomerRepositoryJPA;
import org.switch2022.project.service.irepositories.ICustomerRepository;

import java.util.Optional;

@Repository
public class CustomerRepository implements ICustomerRepository {

    @Autowired
    CustomerRepositoryJPA customerRepositoryJPA;

    @Autowired
    CustomerDomainDataAssembler customerDomainDataAssembler;


    public CustomerDDD save(CustomerDDD customer) {
        Optional<CustomerJPA> existCustomer = customerRepositoryJPA.findById(customer.getCustomerNIF().toString());

        if (existCustomer.isPresent()) {
            throw new IllegalArgumentException("There is a customer with this ID.");
        }

        CustomerJPA customerJPA = customerDomainDataAssembler.toData(customer);
        CustomerJPA savedCustomerJPA = customerRepositoryJPA.save(customerJPA);

        return customerDomainDataAssembler.toDomain(savedCustomerJPA);
    }
}
