package org.switch2022.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    @Test
    void EqualsIsTrueWhenComparingSameProject() {
        Project project = new Project(26,"Test","For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023,Calendar.FEBRUARY,2), 2, 5, "Planned",1000);
        Project projectTest = project;

        boolean result = project.equals(projectTest);

        Assertions.assertTrue(result);
    }

    @Test
    void EqualsIsFalseWhenComparingDifferentProjects() {
        Project project = new Project(26,"Test","For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023,Calendar.FEBRUARY,2), 2, 5, "Planned",1000);
        Project projectTest = new Project(25, "Testing", "For Nothing", new Date(2023, Calendar.JANUARY, 1), new Date(2023,Calendar.FEBRUARY,2), 2, 5, "Planned",1000);

        boolean result = project.equals(projectTest);

        Assertions.assertFalse(result);
    }

    @Test
    void EqualsIsFalseWhenComparingWithNullProject() {
        Project project = new Project(26,"Test","For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023,Calendar.FEBRUARY,2), 2, 5, "Planned",1000);
        Project projectTest = null;

        boolean result = project.equals(projectTest);

        Assertions.assertFalse(result);
    }

    @Test
    void testEqualsAndSame() {
        Project project = new Project(26,"Test","For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023,Calendar.FEBRUARY,2), 2, 5, "Planned",1000);
        Project projectTest = project;


        assertSame(projectTest, project);
        assertNotEquals(false, project.equals(projectTest));
    }

    @Test
    void testHashCode() {
        Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023,Calendar.FEBRUARY,2), 2, 5, "Planned", 1000);
        Project projectTest = project;

        assertEquals(projectTest.hashCode(), project.hashCode());
        assertNotEquals(0, project.hashCode());
    }

    @Test
    void checkIfCodeIsNullWhenIsZero() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(0, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023,Calendar.FEBRUARY,2), 2, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
        }

    @Test
    void checkIfCodeIsNullWhenIsNegative() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(-2, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023,Calendar.FEBRUARY,2), 2, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfNameIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(26, null, "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023,Calendar.FEBRUARY,2), 2, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfDescriptionIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(26, "Test", null, new Date(2023, Calendar.JANUARY, 1), new Date(2023,Calendar.FEBRUARY,2), 2, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfStartDateIsAfterEndDate() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.MARCH, 1), new Date(2023, Calendar.FEBRUARY, 2), 2, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfStartDateIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(26, "Test", "For testing purposes", null, new Date(2023, Calendar.FEBRUARY, 2), 2, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfEndDateIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), null, 2, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfSprintDurationIsNullWhenIsZero() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023,Calendar.FEBRUARY,2), 0, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfSprintDurationIsNullWhenIsNegative() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023,Calendar.FEBRUARY,2), -15, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfNumberOfPlannedSprintsAreNullWhenIsZero() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023,Calendar.FEBRUARY,2), 2, 0, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfNumberOfPlannedSprintsAreNullWhenIsNegative() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023,Calendar.FEBRUARY,2), 2, -15, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfProjectStatusIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023,Calendar.FEBRUARY,2), 2, 5, null, 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfBudgetNullWhenIsNegative() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023,Calendar.FEBRUARY,2), 5, 5, "Planned", -230);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void EqualsIsTrueWhenComparingSameProjectSecondConstructor() {
        Project project = new Project(26,"Test","For testing purposes");
        Project projectTest = project;

        boolean result = project.equals(projectTest);

        Assertions.assertTrue(result);
    }

    @Test
    void EqualsIsFalseWhenComparingDifferentProjectsSecondConstructor() {
        Project project = new Project(26,"Test","For testing purposes");
        Project projectTest = new Project(25, "Testing", "For Nothing");

        boolean result = project.equals(projectTest);

        Assertions.assertFalse(result);
    }

    @Test
    void EqualsIsFalseWhenComparingWithNullProjectSecondConstructor() {
        Project project = new Project(26,"Test","For testing purposes");
        Project projectTest = null;

        boolean result = project.equals(projectTest);

        Assertions.assertFalse(result);
    }

    @Test
    void testEqualsAndSameSecondConstructor() {
        Project project = new Project(26,"Test","For testing purposes");
        Project projectTest = project;

        assertSame(projectTest, project);
        assertNotEquals(false, project.equals(projectTest));
    }

    @Test
    void testHashCodeSecondConstructor() {
        Project project = new Project(26, "Test", "For testing purposes");
        Project projectTest = project;

        assertEquals(projectTest.hashCode(), project.hashCode());
        assertNotEquals(0, project.hashCode());
    }

    @Test
    void checkIfCodeIsNullWhenIsZeroSecondConstructor() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(0, "Test", "For testing purposes");
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfCodeIsNullWhenIsNegativeSecondConstructor() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(-2, "Test", "For testing purposes");
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfNameIsNullSecondConstructor() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(26, null, "For testing purposes");
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfDescriptionIsNullSecondConstructor() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(26, "Test", null);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    @DisplayName("ensure the resource has been added.")
    void ensureAddResourceTrue() {
        //arrange
        Project project = new Project(1, "test", "test");
        ResourceDTO resourceDTO = new ResourceDTO("deborah@hotmail.com", 1, 1, new Date(), 25, 100);
        Account account = new Account("Deborah", "deborah@hotmail.com", "938966782", new Profile("User"));
        Role role = new Role(1, "Team Member");

        //act
        boolean result = project.addResource(account,role,resourceDTO);

        //assert
        assertTrue(result);
    }
    @Test
    @DisplayName("ensure the resource has not been added.")
    void ensureAddResourceFalse() {
        //arrange
        Project project = new Project(1, "test", "test");
        ResourceDTO resourceDTO = new ResourceDTO("deborah@hotmail.com", 1, 1, new Date(), 25, 100);
        Account account = new Account("Deborah", "deborah@hotmail.com", "938966782", new Profile("User"));
        Role role = new Role(1, "Team Member");

        //act
        boolean added = project.addResource(account,role,resourceDTO);
        boolean result = project.addResource(account,role,resourceDTO);

        //assert
        assertFalse(result);
    }

}