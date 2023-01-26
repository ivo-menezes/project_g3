package org.switch2022.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessSectorListTest {

    @Test
    void addBusiness() {
        BusinessSector newBusiness = new BusinessSector("Technology");
        BusinessSector anotherBusiness = new BusinessSector("Financial");
        BusinessSectorList businessList = new BusinessSectorList();

        assertTrue(businessList.addBusiness(newBusiness));
        assertNotEquals(false,businessList.addBusiness(anotherBusiness));
    }
    @Test
    void addBusinessIsNotSuccessful(){
        BusinessSector newBusiness = null;
        BusinessSectorList businessList = new BusinessSectorList();

        assertFalse(businessList.addBusiness(newBusiness));
    }


    @Test
    void validateBusinessSectorAlreadyExists() {
        BusinessSector newBusiness = new BusinessSector("Technology");
        BusinessSector anotherBusiness = new BusinessSector("Financial");
        BusinessSectorList businessList = new BusinessSectorList();
        businessList.addBusiness(newBusiness);
        businessList.addBusiness(anotherBusiness);
        BusinessSector thirdBusiness = new BusinessSector("Financial");

        assertFalse(businessList.listDoesNotContainSector(thirdBusiness));
    }
    @Test
    void validateIfTheBusinessIsNotInTheList(){
        BusinessSector newBusiness = new BusinessSector("Technology");
        BusinessSector anotherBusiness = new BusinessSector("Financial");
        BusinessSectorList businessList = new BusinessSectorList();
        businessList.addBusiness(newBusiness);
        businessList.addBusiness(anotherBusiness);
        BusinessSector thirdBusiness = new BusinessSector("Industrial");

        assertTrue(businessList.listDoesNotContainSector(thirdBusiness));
    }

    @Test
    void createBusinessSector() {
        BusinessSector newBusiness = new BusinessSector("Technology");
        BusinessSector anotherBusiness = new BusinessSector("Industrial");
        BusinessSectorList businessList = new BusinessSectorList();
        businessList.addBusiness(newBusiness);
        businessList.addBusiness(anotherBusiness);
        String designation = "Financial";

        assertTrue(businessList.createBusinessSector(designation));
    }

}