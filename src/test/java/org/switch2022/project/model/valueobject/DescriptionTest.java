package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DescriptionTest {

    final String expectedMessage = "Description must not be null";
    final String description1 = "description1";
    final String description2 = "description2";

    @Test
    public void shouldCreateAValidDescription() {
        new UserStoryID(description1);
    }

    @Test
    public void shouldThrowExceptionDescriptionWithNullDescription() {

        Exception exception = assertThrows(Exception.class, () -> {
            new Description(null);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionDescriptionWithEmptyDescription() {

        Exception exception = assertThrows(Exception.class, () -> {
            new Description("");
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionDescriptionWithBlankDescription() {

        Exception exception = assertThrows(Exception.class, () -> {
            new Description("   ");
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionDescriptionWithTabDescription() {

        Exception exception = assertThrows(Exception.class, () -> {
            new Description("\t");
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionDescriptionWithReturnDescription() {

        Exception exception = assertThrows(Exception.class, () -> {
            new Description("\n");
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldReturnFalseEqualsWithNull() {

        Description description = new Description(description1);

        boolean isEquals = description.equals(null);

        assertFalse(isEquals);
    }

    @Test
    public void shouldReturnTrueEqualsWithSameObject() {

        Description description = new Description(description1);

        boolean isEquals = description.equals(description);

        assertTrue(isEquals);
    }

    @Test
    public void shouldReturnTrueEqualsWithSameDescription() {

        Description descriptionOne = new Description(description1);
        Description descriptionTwo = new Description(description1);

        boolean isEquals = descriptionOne.equals(descriptionTwo);

        assertTrue(isEquals);
    }

    @Test
    public void shouldReturnTrueEqualsWithDifferentDescription() {

        Description descriptionOne = new Description(description1);
        Description descriptionTwo = new Description(description2);

        boolean isEquals = descriptionOne.equals(descriptionTwo);

        assertFalse(isEquals);
    }

    @Test
    public void toStringShouldReturnTheDescriptionString() {

        Description description = new Description(description1);

        assertEquals(description.toString(), description1);
    }

}