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
    void validateBusinessSectorIsNewToList() {
        BusinessSector newBusiness = new BusinessSector("Technology");
        BusinessSector anotherBusiness = new BusinessSector("Industrial");
        BusinessSectorList businessList = new BusinessSectorList();
        businessList.addBusiness(newBusiness);
        businessList.addBusiness(anotherBusiness);
        String designation = "Financial";

        assertTrue(businessList.validateBusinessSector(designation));
    }
    @Test
    void validateBusinessSectorAlreadyExists() {
        BusinessSector newBusiness = new BusinessSector("Technology");
        BusinessSector anotherBusiness = new BusinessSector("Financial");
        BusinessSectorList businessList = new BusinessSectorList();
        businessList.addBusiness(newBusiness);
        businessList.addBusiness(anotherBusiness);
        String designation = "Financial";

        assertFalse(businessList.validateBusinessSector(designation));
    }
    @Test
    void validateIfTheBusinessIsNull(){
        BusinessSector newBusiness = new BusinessSector("Technology");
        BusinessSector anotherBusiness = new BusinessSector("Financial");
        BusinessSectorList businessList = new BusinessSectorList();
        businessList.addBusiness(newBusiness);
        businessList.addBusiness(anotherBusiness);
        String designation = null;

        assertFalse(businessList.validateBusinessSector(designation));
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
    @Test
    void checkIfThereIsAnError(){
            IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BusinessSector newBusiness = new BusinessSector("Technology");
            BusinessSector anotherBusiness = new BusinessSector("Industrial");
            BusinessSectorList businessList = new BusinessSectorList();
            businessList.addBusiness(newBusiness);
            businessList.addBusiness(anotherBusiness);
            String designation = "Technology";
            businessList.createBusinessSector(designation);
        });
        Assertions.assertEquals("Designation already exists.", exception.getMessage());
    }

}