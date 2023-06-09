package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectCodeTest {
    //Arrange
    final String expectedMessage = "Project code must not be null";

    final String code1 = "P001";
    final String code2 = "P002";

    @Test
    public void shouldCreateAValidProjectCode(){
        ProjectCode code = new ProjectCode(code1);

        assertInstanceOf(ProjectCode.class, code);
    }

    @Test
    public void shouldThrowExceptionWithNullProjectCode(){
        //Act
        Exception exception = assertThrows(Exception.class, () ->
                new ProjectCode(null));

        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionWithBlankProjectCode(){
        //Act
        Exception exception = assertThrows(Exception.class, () ->
                new ProjectCode(" "));

        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionWithEmptyProjectCode(){
        //Act
        Exception exception = assertThrows(Exception.class, () ->
                new ProjectCode(""));

        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionWithATabInProjectCode(){
        //Act
        Exception exception = assertThrows(Exception.class, () ->
                new ProjectCode("\t"));

        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionWithReturnInProjectCode(){
        //Act
        Exception exception = assertThrows(Exception.class, () ->
                new ProjectCode("\n"));

        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldReturnFalseEqualsWithNull(){
        //Arrange
        ProjectCode projectCode = new ProjectCode(code1);

        //Act
        boolean isEquals = projectCode.equals(null);

        //Assert
        assertFalse(isEquals);
    }

    @Test
    public void shouldReturnTrueEqualsSameObject() {
        //Arrange
        ProjectCode projectCode = new ProjectCode(code1);

        //Act
        boolean isEquals = projectCode.equals(projectCode);

        //Assert
        assertTrue(isEquals);
    }

    @Test
    public void shouldReturnTrueEqualsSameCode() {
        //Arrange
        ProjectCode projectCode1 = new ProjectCode(code1);
        ProjectCode projectCode2 = new ProjectCode(code1);

        //Act
        boolean isEquals = projectCode1.equals(projectCode2);

        //Assert
        assertTrue(isEquals);
    }

    @Test
    public void shouldReturnFalseEqualsDifferentCode(){
        //Arrange
        ProjectCode projectCode1 = new ProjectCode(code1);
        ProjectCode projectCode2 = new ProjectCode(code2);

        //Act
        boolean isEquals = projectCode1.equals(projectCode2);

        //Assert
        assertFalse(isEquals);
    }

    @Test
    @DisplayName("Ensure object does not equal object of a different class")
    void ensureObjectDoesNotEqualObjectWithDifferentDesignation(){
        //Arrange
        ProjectCode projectCode1 = new ProjectCode(code1);
        String fakeCode = "FakeCode";

        //Act
        boolean result = projectCode1.equals(fakeCode);

        //Assert
        assertFalse(result);
    }

    @Test
    public void toStringShouldReturnTheCodeString(){

        ProjectCode projectCode = new ProjectCode(code1);

        assertEquals(projectCode.toString(), code1);
    }

    @DisplayName("similar projectCode have same hash code")
    @Test
    void projectCodeHasSameHashCode() {
        // Arrange
        ProjectCode aProjectCode = new ProjectCode("XPTO");
        ProjectCode anotherProjectCode = new ProjectCode("XPTO");

        // Act & Assert
        assertEquals(aProjectCode.hashCode(), anotherProjectCode.hashCode());
    }

    @DisplayName("different projectCode have different hash code")
    @Test
    void projectCodeHasDifferentHashCode() {
        // Arrange
        ProjectCode aProjectCode = new ProjectCode("XPTO");
        ProjectCode anotherProjectCode = new ProjectCode("XYZ");

        // Act & Assert
        assertNotEquals(aProjectCode.hashCode(), anotherProjectCode.hashCode());
    }

}