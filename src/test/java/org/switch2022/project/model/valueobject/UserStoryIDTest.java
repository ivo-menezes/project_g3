package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryIDTest {

    final String expectedMessage = "ID must not be null";
    final String ID1 = "US001";
    final String ID2 = "US002";

    @Test
    public void shouldCreateAValidUserStoryID() {
        new UserStoryID(ID1);
    }

    @Test
    public void shouldThrowExceptionUserStoryIDWithNullId() {

        Exception exception = assertThrows(Exception.class, () -> {
            new UserStoryID(null);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionUserStoryIDWithEmptyID() {

        Exception exception = assertThrows(Exception.class, () -> {
            new UserStoryID("");
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionUserStoryIDWithBlankID() {

        Exception exception = assertThrows(Exception.class, () -> {
            new UserStoryID("   ");
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionUserStoryIDWithTabID() {

        Exception exception = assertThrows(Exception.class, () -> {
            new UserStoryID("\t");
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionUserStoryIDWithReturnID() {

        Exception exception = assertThrows(Exception.class, () -> {
            new UserStoryID("\n");
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldReturnFalseEqualsWithNull() {

        UserStoryID userStoryID = new UserStoryID(ID1);

        boolean isEquals = userStoryID.equals(null);

        assertFalse(isEquals);
    }

    @Test
    public void shouldReturnTrueEqualsWithSameObject() {

        UserStoryID userStoryID = new UserStoryID(ID1);

        boolean isEquals = userStoryID.equals(userStoryID);

        assertTrue(isEquals);
    }

    @Test
    public void shouldReturnTrueEqualsWithSameIds() {

        UserStoryID userStoryID1 = new UserStoryID(ID1);
        UserStoryID userStoryID2 = new UserStoryID(ID1);

        boolean isEquals = userStoryID1.equals(userStoryID2);

        assertTrue(isEquals);
    }

    @Test
    public void shouldReturnTrueEqualsWithDifferentIds() {

        UserStoryID userStoryID1 = new UserStoryID(ID1);
        UserStoryID userStoryID2 = new UserStoryID(ID2);

        boolean isEquals = userStoryID1.equals(userStoryID2);

        assertFalse(isEquals);
    }

    @Test
    public void toStringShouldReturnTheIdString() {

        UserStoryID userStoryID = new UserStoryID(ID1);

        assertEquals(userStoryID.toString(), ID1);
    }
}