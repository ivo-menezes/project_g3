package org.switch2022.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypologyTest {

    @Test
    @DisplayName("ensure Fixed Cost typology is successfully created")
    void createTypologyFixedCostSuccess() {
        //arrange
        Typology typology = new Typology("Fixed cost");

        //act
        boolean result = typology.isTypologyDesignationValid("Fixed cost");

        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("ensure Time and materials typology is successfully created")
    void createTypologyTimeAndMaterialsSuccess() {
        //arrange
        Typology typology = new Typology("Time and materials");

        //act
        boolean result = typology.isTypologyDesignationValid("Time and materials");

        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("ensure incorrect typology fails to be created")
    void createTypologyAbcFails(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Typology typology = new Typology("Abc");
        });

        Assertions.assertEquals("Typology designation is not valid", exception.getMessage());
    }

    @Test
    @DisplayName("ensure null typology fails to be created")
    void createTypologyNull () {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Typology typology = new Typology("");
        });

        Assertions.assertEquals("Typology designation is not valid", exception.getMessage());
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
    @DisplayName("ensure object does not equal other types of object")
    void objectDoesNotEqualDifferentTypeOfObject() {
        //arrange
        Typology typology = new Typology("Fixed cost");
        TypologyList typologyList = new TypologyList();
        boolean expected = false;
        //act
        boolean result = typology.equals(typologyList);
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