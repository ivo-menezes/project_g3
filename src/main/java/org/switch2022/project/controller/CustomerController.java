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
import org.switch2022.project.mapper.CustomerMapper;
import org.switch2022.project.mapper.CustomerOutputDTO;
import org.switch2022.project.service.CustomerService;

@Controller
@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerMapper customerMapper;

    public CustomerController (CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Post method responsible for creating a customer.
     * @param customer (dto)
     * @return when successful, the status http 201 (created) is returned, or otherwise 400 (bad request)
     */
    @PostMapping("")
    public ResponseEntity<CustomerOutputDTO> createCustomer(@RequestBody CustomerDTO customer) {

        try {
            CustomerDTO savedCustomer = customerService.createCustomer(customer);
            CustomerOutputDTO customerOutputDTO =  customerMapper.toOutputDTO(savedCustomer);
            return new ResponseEntity<>(customerOutputDTO, HttpStatus.CREATED);
        }
        catch (IllegalArgumentException exception){
            CustomerOutputDTO customerOutputDTO =  customerMapper.toOutputDTO(customer);
            return new ResponseEntity<>(customerOutputDTO, HttpStatus.BAD_REQUEST);
        }

    }
}
