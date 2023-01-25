package org.switch2022.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypologyTest {

    @Test
    @DisplayName ("ensure invalid typology is not created")
    void getTypologyDesignationFail() {
        //arrange
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
        Typology typology = new Typology("ThisIsATest");
        });
        String expected = "Typology designation is invalid";

        Assertions.assertEquals(expected, exception.getMessage());
    }

    @Test
    @DisplayName ("ensure typology Time and materials is successfully retrieved by designation")
    void getTypologyDesignationSuccess() {
        //arrange
        Typology typology = new Typology("Time and materials");
        String expected = "Time and materials";

        //act
        String result = typology.getTypologyDesignation();

        //assert
        assertEquals(expected, result);
    }

    /**
     * These tests assess equals and hashCode methods
     */

    @Test
    @DisplayName("ensure object does not equal null")
    void objectDoesNotEqualNull(){
        //arrange
        Typology typology = new Typology("Fixed cost");
        Typology anotherTypology = null;
        boolean expected = false;

        //act
        boolean result = typology.equals(anotherTypology);

        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("ensure object equals itself")
    void objectEqualsItself() {
        //arrange
        Typology typology = new Typology("Fixed cost");
        Typology anotherTypology = typology;
        boolean expected = true;

        //act
        boolean result = typology.equals(anotherTypology);

        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("ensure object equals itself part II")
    void objectEqualsItselfTwo() {
        //arrange
        Typology typology = new Typology("Fixed cost");
        Typology anotherTypology = new Typology ("Fixed cost");
        boolean expected = true;

        //act
        boolean result = typology.equals(anotherTypology);

        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("ensure object does not equal another object")
    void objectDoesNotEqualAnotherObject() {
        //arrange
        Typology typology = new Typology("Fixed cost");
        Typology anotherTypology = new Typology("Time and materials");
        boolean expected = false;

        //act
        boolean result = typology.equals(anotherTypology);

        //assert
        assertEquals(expected, result);
    }

    @Test
    void testHashCode(){
        Typology typology = new Typology("Fixed cost");
        Typology testTypology = typology;

        assertEquals(testTypology.hashCode(), typology.hashCode());
        assertNotEquals(0, typology.hashCode());
    }

}