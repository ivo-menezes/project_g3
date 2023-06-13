package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectBudgetTest {

    @DisplayName("creating projectBudget with value less than zero should generate exception")
    @Test
    void CreatingProjectBudgetWithValueLessThanZeroThrowsException() {
        // Arrange
        float projectBudget = -1235.7f;
        String expectedMessage = "projectBudget cannot be negative";

        // Act
        IllegalArgumentException result =assertThrows(IllegalArgumentException.class, () -> {
            new ProjectBudget(projectBudget);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }


    @DisplayName("Ensure that two projectBudget objects are the same.")
    @Test
    void projectBudgetEqualsSelf() {
        //Arrange
        ProjectBudget projectBudgetOne = new ProjectBudget(1200.0f);
        ProjectBudget projectBudgetTwo = projectBudgetOne;

        //Act and Assert
        assertTrue(projectBudgetOne.equals(projectBudgetTwo));
    }

    @DisplayName("Ensure that two projectBudget objects aren't the same.")
    @Test
    void projectNotEqualsDifferentValue() {
        //Arrange
        ProjectBudget projectBudgetOne = new ProjectBudget(1200.0f);
        ProjectBudget projectBudgetTwo = new ProjectBudget(2000.0f);

        //Act and Assert
        assertFalse(projectBudgetOne.equals(projectBudgetTwo));
    }
    @Test
    @DisplayName("Ensure value is retrieved")
    void ensureValueIsRetrieved(){
        //Arrange
        float expected = 3000.5f;
        ProjectBudget projectBudget = new ProjectBudget(expected);

        //Act
        float result = projectBudget.getValue();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure different objects have different hashcode")
    void ensureDifferentObjectsHaveDifferentHashcode() {
        //Arrange
        ProjectBudget projectBudgetOne = new ProjectBudget(1200.0f);
        ProjectBudget projectBudgetTwo = new ProjectBudget(2000.0f);
        //Act
        int hashCode1 = projectBudgetOne.hashCode();
        int hashCode2 = projectBudgetTwo.hashCode();

        //Assert
        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Return false in equals with null")
    public void returnFalseEqualsWithNull() {

        //Arrange
        float expected = 3000.5f;
        ProjectBudget projectBudget = new ProjectBudget(expected);

        //Act

        boolean isEqual = projectBudget.equals(null);

        // Assert
        assertFalse(isEqual);
    }

    @Test
    @DisplayName(("Test for creation of budget"))
    public void checkIfClassCreatesValidBudget() {
        float budget = 0;
        ProjectBudget projectBudget = new ProjectBudget(budget);
    }

}