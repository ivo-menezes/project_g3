package org.switch2022.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.switch2022.project.mapper.BusinessSectorDTO;
import org.switch2022.project.mapper.CustomerDTO;
import org.switch2022.project.mapper.TypologyDTO;
import org.switch2022.project.model.valueobject.BusinessSectorDesignation;
import org.switch2022.project.model.valueobject.CustomerDesignation;
import org.switch2022.project.model.valueobject.CustomerNIF;
import org.switch2022.project.model.valueobject.TypologyDesignation;
import org.switch2022.project.service.*;

@Profile("!test")
@Component
public class DataLoader implements CommandLineRunner {
    /**
     * This class runs at the same time as application.java.
     * So is necessary to avoid problems to run tests add the annotation @ActiveProfiles("test") in each test class of Services injected.
     *
     */
    @Autowired
    TypologyService typologyService;
    @Autowired
    CustomerService customerService;
    @Autowired
    BusinessSectorService businessSectorService;

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }
    private void loadData() {
        loadCustomers();
        loadBusinessSectors();
        loadTypologies();
    }

    private void loadCustomers(){
        CustomerNIF customerNIFOne = new CustomerNIF("306123987");
        CustomerDesignation customerDesignationOne = new CustomerDesignation("XPTO, SA");

        CustomerNIF customerNIFTwo = new CustomerNIF("306123988");
        CustomerDesignation customerDesignationTwo = new CustomerDesignation("XYZ, Lda");

        CustomerNIF customerNIFThree = new CustomerNIF("306123989");
        CustomerDesignation customerDesignationThree = new CustomerDesignation("Hell, LLC");

        CustomerDTO customerOne = new CustomerDTO();
        customerOne.customerNIF = customerNIFOne;
        customerOne.customerDesignation = customerDesignationOne;

        CustomerDTO customerTwo = new CustomerDTO();
        customerTwo.customerNIF = customerNIFTwo;
        customerTwo.customerDesignation = customerDesignationTwo;

        CustomerDTO customerThree = new CustomerDTO();
        customerThree.customerNIF = customerNIFThree;
        customerThree.customerDesignation = customerDesignationThree;

        customerService.createCustomer(customerOne);
        customerService.createCustomer(customerTwo);
        customerService.createCustomer(customerThree);
    }

    private void loadBusinessSectors(){
        BusinessSectorDesignation businessSectorDesignationOne = new BusinessSectorDesignation("It doesn't matter");
        BusinessSectorDesignation businessSectorDesignationTwo = new BusinessSectorDesignation("Hospitality industry");

        BusinessSectorDTO businessSectorOne = new BusinessSectorDTO();
        businessSectorOne.businessSectorDesignation = businessSectorDesignationOne;

        BusinessSectorDTO businessSectorTwo = new BusinessSectorDTO();
        businessSectorTwo.businessSectorDesignation = businessSectorDesignationTwo;

        businessSectorService.createBusinessSector(businessSectorOne);
        businessSectorService.createBusinessSector(businessSectorTwo);
    }

    private void loadTypologies(){
        TypologyDesignation typologyDesignationOne = new TypologyDesignation("Fixed cost");
        TypologyDesignation typologyDesignationTwo = new TypologyDesignation("Time and materials");

        TypologyDTO typologyOne = new TypologyDTO();
        typologyOne.typologyDesignation = typologyDesignationOne;

        TypologyDTO typologyTwo = new TypologyDTO();
        typologyTwo.typologyDesignation = typologyDesignationTwo;

        typologyService.createTypology(typologyOne);
        typologyService.createTypology(typologyTwo);
    }
}
