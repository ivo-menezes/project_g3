package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectNameTest {

    @DisplayName("creating projectName with null value should throw Exception")
    @Test
    void createProjectNameWithNullThrowsException() {
        // Arrange
        String projectName = null;
        String expectedMessage = "projectName cannot be null/blank/empty";

        // Act
        IllegalArgumentException result =assertThrows(IllegalArgumentException.class, () -> {
            new ProjectName(projectName);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating projectName with empty value should throw Exception")
    @Test
    void createProjectNameWithEmptyThrowsException() {
        // Arrange
        String projectName = "";
        String expectedMessage = "projectName cannot be null/blank/empty";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new ProjectName(projectName);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating projectName with blank value should throw Exception")
    @Test
    void createUserStoryNumberWithBlankThrowsException() {
        // Arrange
        String projectName = "       ";
        String expectedMessage = "projectName cannot be null/blank/empty";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new ProjectName(projectName);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Ensure object does not equal null")
    void ensureObjectDoesNotEqualNull() {
        //Arrange
        ProjectName projectName = new ProjectName("My Project");

        //Act
        boolean result = projectName.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure object equals same object")
    void ensureObjectEqualsSameObject() {

        //Arrange
        ProjectName projectName = new ProjectName("My Project");

        //Act
        boolean result = projectName.equals(projectName);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure object equals object with same name")
    void ensureObjectEqualsObjectWithSameDesignation() {
        //Arrange
        ProjectName projectName1 = new ProjectName("My Project");
        ProjectName projectName2 = new ProjectName("My Project");

        //Act
        boolean result = projectName1.equals(projectName2);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure object does not equal object with different name")
    void ensureObjectDoesNotEqualObjectWithDifferentName() {
        //Arrange
        ProjectName projectName1 = new ProjectName("My Project");
        ProjectName projectName2 = new ProjectName("My Super Project");

        //Act
        boolean result = projectName1.equals(projectName2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure object does not equal object of different class")
    void objectDoesNotEqualProjectOfDifferentClass() {
        //Arrange
        ProjectName projectName = new ProjectName("My Project");
        String fakeProject = "FakeProject";

        //Act
        boolean result = projectName.equals(fakeProject);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure equal objects have same hashcode")
    void ensureEqualObjectsHaveSameHashcode() {
        //Arrange
        ProjectName projectName1 = new ProjectName("My Project");
        ProjectName projectName2 = new ProjectName("My Project");

        //Act
        int hashCode1 = projectName1.hashCode();
        int hashCode2 = projectName2.hashCode();

        //Assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Ensure equal objects have same hashcode")
    void ensureDifferentObjectsHaveDifferentHashcode() {
        //Arrange
        ProjectName projectName1 = new ProjectName("My Project");
        ProjectName projectName2 = new ProjectName("My Super Project");

        //Act
        int hashCode1 = projectName1.hashCode();
        int hashCode2 = projectName2.hashCode();

        //Assert
        assertNotEquals(hashCode1, hashCode2);
    }


    @DisplayName("toString returns the Project Name String ")
    @Test
    public void toStringShouldReturnProjectNameString(){

        // Arrange
        ProjectName projectName = new ProjectName("My Project");
        // Act & Assert
        assertEquals(projectName.toString(), "My Project");
    }

}