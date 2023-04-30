package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserStoryIDTest {

    final String expectedMessage1 = "userStoryNumber must not be null";
    final String expectedMessage2 = "projectCode must not be null";
    final UserStoryNumber userStoryNumber = mock(UserStoryNumber.class);
    final ProjectCode projectCode = mock(ProjectCode.class);

    @Test
    public void shouldCreateAValidUserStoryID() {
        new UserStoryID(userStoryNumber, projectCode);
    }

    @Test
    public void shouldThrowExceptionUserStoryIDWithNullUserStoryNumber() {

        Exception exception = assertThrows(Exception.class, () -> {
            new UserStoryID(null, projectCode);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage1));
    }

    @Test
    public void shouldThrowExceptionUserStoryIDWithNullProjectCode() {

        Exception exception = assertThrows(Exception.class, () -> {
            new UserStoryID(userStoryNumber,null);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage2));
    }

    @Test
    public void shouldReturnFalseEqualsWithNull() {

        UserStoryID userStoryID = new UserStoryID(userStoryNumber, projectCode);

        boolean isEquals = userStoryID.equals(null);

        assertFalse(isEquals);
    }

    @Test
    public void shouldReturnTrueEqualsWithSameObject() {

        UserStoryID userStoryID = new UserStoryID(userStoryNumber, projectCode);

        boolean isEquals = userStoryID.equals(userStoryID);

        assertTrue(isEquals);
    }

    @Test
    public void shouldReturnTrueEqualsWithSameIds() {

        UserStoryID userStoryID1 = new UserStoryID(userStoryNumber, projectCode);
        UserStoryID userStoryID2 = new UserStoryID(userStoryNumber, projectCode);

        boolean isEquals = userStoryID1.equals(userStoryID2);

        assertTrue(isEquals);
    }

    @Test
    public void shouldReturnFalseEqualsWithDifferentUserStoryNumbers() {

        UserStoryID userStoryID1 = new UserStoryID(userStoryNumber, projectCode);
        UserStoryNumber userStoryNumber2 = mock(UserStoryNumber.class);
        UserStoryID userStoryID2 = new UserStoryID(userStoryNumber2, projectCode);

        boolean isEquals = userStoryID1.equals(userStoryID2);

        assertFalse(isEquals);
    }

}