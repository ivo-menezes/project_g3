package org.switch2022.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.switch2022.project.mapper.CustomerDTO;
import org.switch2022.project.service.CustomerService;

@Controller
@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    public CustomerController (CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Post method responsible for creating a customer.
     * @param customer (dto)
     * @return the http status 201 (created) and the customer object created.
     */
    @PostMapping("")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customer) {

        try {
            CustomerDTO savedCustomer = customerService.createCustomer(customer);
            return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
        }catch (IllegalArgumentException exception){
            return new ResponseEntity<>(customer, HttpStatus.BAD_REQUEST);
        }

    }
}
