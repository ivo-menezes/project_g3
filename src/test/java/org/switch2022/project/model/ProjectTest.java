package org.switch2022.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    @Test
    void testEqualsAndSame() {
        Project project = new Project(26,"Test","For testing purposes", new Date(2023,01,01), new Date(2023,02,02), 2, 5, "Planned",1000);
        Project projectTest = project;

        assertTrue(project.equals(projectTest));
        assertSame(projectTest, project);
        assertNotEquals(false, project.equals(projectTest));
    }

    @Test
    void testHashCode() {
        Project project = new Project(26, "Test", "For testing purposes", new Date(2023,01,01), new Date(2023,02,02), 2, 5, "Planned", 1000);
        Project projectTest = project;

        assertEquals(projectTest.hashCode(), project.hashCode());
        assertNotEquals(0, project.hashCode());
    }

    @Test
    void checkIfCodeIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(0, "Test", "For testing purposes", new Date(2023, 01, 01), new Date(2023, 02, 02), 2, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
        }

    @Test
    void checkIfNameIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(26, null, "For testing purposes", new Date(2023, 01, 01), new Date(2023, 02, 02), 2, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfDescriptionIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(26, "Test", null, new Date(2023, 01, 01), new Date(2023, 02, 02), 2, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfStartDateIsAfterEndDate() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(26, "Test", "For testing purposes", new Date(2023, 03, 01), new Date(2023, 02, 02), 2, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfStartDateIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(26, "Test", "For testing purposes", null, new Date(2023, 02, 02), 2, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfEndDateIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(26, "Test", "For testing purposes", new Date(2023, 01, 01), null, 2, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfSprintDurationIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(26, "Test", "For testing purposes", new Date(2023, 01, 01), new Date(2023,02,02), 0, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfNumberOfPlannedSprintsAreNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(26, "Test", "For testing purposes", new Date(2023, 01, 01), new Date(2023,02,02), 2, 0, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfProjectStatusIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(26, "Test", "For testing purposes", new Date(2023, 01, 01), new Date(2023,02,02), 2, 5, null, 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfBudgetNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()  -> {

            Project project = new Project(26, "Test", "For testing purposes", new Date(2023, 01, 01), new Date(2023,02,02), 5, 5, "Planned", -230);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }




}