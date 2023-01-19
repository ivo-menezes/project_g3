package org.switch2022.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessSectorTest {
    @Test
    @DisplayName("Ensure that the designation is the same")
    void checkTheBusinessDesignationIsTheSame(){
        BusinessSector newBusiness = new BusinessSector("Financial");
        BusinessSector sameBusiness = newBusiness;

        Assertions.assertSame(sameBusiness, newBusiness);
        assertTrue(newBusiness.equals(sameBusiness));
        assertNotEquals(false, newBusiness.equals(sameBusiness));
    }
    @Test
    @DisplayName("Testing the hashcode")
    void checkTheHashCode(){
        BusinessSector newBusiness = new BusinessSector("Financial");
        BusinessSector sameBusiness = new BusinessSector("Financial");

        assertEquals(sameBusiness.hashCode(), newBusiness.hashCode());
        assertNotEquals(0, newBusiness.hashCode());
    }
    @Test
    @DisplayName("Ensure that the designation is corrected created")
    void checkTheBusinessDesignationIsCorrectlyCreated(){
        BusinessSector newBusiness = new BusinessSector("Financial");
        BusinessSector sameBusiness = new BusinessSector("Financial");

        assertEquals(sameBusiness, newBusiness);
        Assertions.assertNotNull(newBusiness);
    }
    @Test
    @DisplayName("Ensure the class catches the NULL")
    void ensureThatNULLDesignationIsCaught(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BusinessSector newBusiness = new BusinessSector(null);
        });
        Assertions.assertEquals("Designation cannot be null.", exception.getMessage());
    }

    /***
     * This test will ensure the Business object created isn't the same as a null Object.
     */
    @Test
    @DisplayName("Ensure the business doesn't equal Null")
    void ensureTheObjectDoesNotEqualsNull(){
        BusinessSector newBusiness = new BusinessSector("Technology");
        BusinessSector anotherBusiness = null;
        boolean expected = false;

        boolean result = newBusiness.equals(anotherBusiness);

        assertEquals(expected, result);
    }
    @Test
    @DisplayName("Ensure the business doesn't equal other Types of Object")
    void ensureTheObjectDoesNotEqualsOtherTypes(){
        BusinessSector newBusiness = new BusinessSector("Technology");
        Profile firstProfile = new Profile("User");
        Account account = new Account("Joana","xxxxx@gmail.com","22255588", firstProfile);
        boolean expected = false;

        boolean result = newBusiness.equals(account);

        assertEquals(expected, result);
    }
    @Test
    @DisplayName("Ensure the business doesn't equal other Types of Object")
    void ensureTheBusinessDoesNotEqualsAnotherBusiness(){
        BusinessSector newBusiness = new BusinessSector("Technology");
        BusinessSector anotherBusiness = new BusinessSector("Financial");
        boolean expected = false;

        boolean result = newBusiness.equals(anotherBusiness);

        assertEquals(expected, result);
    }
    @Test
    @DisplayName("Check if the get method doesn't return empty")
    void checkTheGetMethod(){
        BusinessSector newBusiness = new BusinessSector("Technology");
        BusinessSector anotherBusiness = new BusinessSector("Technology");

        assertEquals(anotherBusiness.getDesignation(), newBusiness.getDesignation());
        assertNotEquals("", newBusiness.getDesignation());
    }
}