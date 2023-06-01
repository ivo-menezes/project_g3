package org.switch2022.project.service.irepositories;

import org.switch2022.project.model.customer.CustomerDDD;

import java.util.ArrayList;

public interface ICustomerRepository {

    CustomerDDD save (CustomerDDD customer);
    ArrayList<CustomerDDD> getAll();
}
