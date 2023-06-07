package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectCodeTest {

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

        Exception exception = assertThrows(Exception.class, () ->
                new ProjectCode(null));

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionWithBlankProjectCode(){

        Exception exception = assertThrows(Exception.class, () ->
                new ProjectCode(" "));

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionWithEmptyProjectCode(){

        Exception exception = assertThrows(Exception.class, () ->
                new ProjectCode(""));

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionWithATabInProjectCode(){

        Exception exception = assertThrows(Exception.class, () ->
                new ProjectCode("\t"));

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionWithReturnInProjectCode(){

        Exception exception = assertThrows(Exception.class, () ->
                new ProjectCode("\n"));

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldReturnFalseEqualsWithNull(){

        ProjectCode projectCode = new ProjectCode(code1);

        boolean isEquals = projectCode.equals(null);

        assertFalse(isEquals);
    }

    @Test
    public void shouldReturnTrueEqualsSameObject() {

        ProjectCode projectCode = new ProjectCode(code1);

        boolean isEquals = projectCode.equals(projectCode);

        assertTrue(isEquals);
    }

    @Test
    public void shouldReturnTrueEqualsSameCode() {

        ProjectCode projectCode1 = new ProjectCode(code1);
        ProjectCode projectCode2 = new ProjectCode(code1);

        boolean isEquals = projectCode1.equals(projectCode2);

        assertTrue(isEquals);
    }

    @Test
    public void shouldReturnFalseEqualsDifferentCode(){

        ProjectCode projectCode1 = new ProjectCode(code1);
        ProjectCode projectCode2 = new ProjectCode(code2);

        boolean isEquals = projectCode1.equals(projectCode2);

        assertFalse(isEquals);
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