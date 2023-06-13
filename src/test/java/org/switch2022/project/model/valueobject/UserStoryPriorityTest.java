package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryPriorityTest {

    final String expectedMessage = "Priority must not be below 0.";
    final int priorityOne = 1;
    final int priorityTwo = 2;
    final int priorityZero = 0;
    final int invalidPriority = -1;

    /***
     * This test will test if the constructor works as intended and produces the
     * value object UserStoryPriority.
     */
    @Test
    @DisplayName(("Test for creation of UserStoryPriority"))
    public void checkIfClassCreatesValidUserStoryPriority(){
        new UserStoryPriority(priorityOne);
    }

    /***
     * This test checks whether the UserStoryPriority was given a negative
     * value and whether it throws an IllegalArgumentException, as required.
     */
    @Test
    @DisplayName(("Test for error in creating object with negative value"))
    public void checkIfUsingNegativeValueThrowsIllegalArgumentException(){
        Exception exception = assertThrows(Exception.class, () -> {
            new UserStoryPriority(invalidPriority);
        });
        String result = exception.getMessage();

        assertTrue(result.contains(expectedMessage));
    }

    @Test
    @DisplayName(("Test for potential Conditional Boundary Changes"))
    public void testChangedConditionalBoundaryMutation() {

        try {
            new UserStoryPriority(invalidPriority);
            fail("Expected IllegalArgumentException to be thrown.");
        } catch (IllegalArgumentException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    /***
     * The test verifies whether creating two objects of UserStoryPriority with the
     * same value will result in an equal object to one another.
     */
    @Test
    @DisplayName(("Test for UserStoryPriority object equals another with same value"))
    public void checkIfCreatingWithSamePriorityWillResultInEqualObjects(){
        UserStoryPriority userStoryPriorityOne = new UserStoryPriority(priorityTwo);
        UserStoryPriority userStoryPriorityTwo = new UserStoryPriority(priorityTwo);

        boolean isEqual = userStoryPriorityOne.equals(userStoryPriorityTwo);

        assertTrue(isEqual);
    }

    /***
     * Ths test checks if one object of UserStoryPriority created with another object will
     * equal itself.
     */
    @Test
    @DisplayName(("Test for UserStoryPriority object equals itself"))
    public void checkIfUserStoryPriorityEqualsItself(){
        UserStoryPriority userStoryPriorityOne = new UserStoryPriority(priorityTwo);
        UserStoryPriority userStoryPriorityTwo = userStoryPriorityOne;

        assertSame(userStoryPriorityOne, userStoryPriorityTwo);
    }
    /***
     * Ths test checks if one object of UserStoryPriority created with another object will
     * equal itself and won't return a different result.
     */
    @Test
    @DisplayName(("Test for UserStoryPriority object equals itself"))
    public void checkIfUserStoryPriorityEqualsItselfWillNotReturnFalse(){
        UserStoryPriority userStoryPriorityOne = new UserStoryPriority(priorityTwo);
        UserStoryPriority userStoryPriorityTwo = userStoryPriorityOne;

        boolean isEqual = userStoryPriorityOne.equals(userStoryPriorityTwo);

        assertNotEquals(false, isEqual);
    }

    /***
     *
     */
    @Test
    @DisplayName(("Test for UserStoryPriority with same value won't cause issue"))
    public void checkIfCreatingWithSamePriorityWillNotResultInError(){
        UserStoryPriority userStoryPriorityOne = new UserStoryPriority(priorityTwo);
        UserStoryPriority userStoryPriorityTwo = new UserStoryPriority(priorityTwo);

        boolean isEqual = userStoryPriorityOne.equals(userStoryPriorityTwo);

        assertNotEquals(false, isEqual);
    }

    @Test
    @DisplayName(("Test for UserStoryPriority object isn't equal to another"))
    public void checkIfCreatingWithAnotherPriorityWillResultInNotEqualObjects(){
        UserStoryPriority userStoryPriorityOne = new UserStoryPriority(priorityOne);
        UserStoryPriority userStoryPriorityTwo = new UserStoryPriority(priorityTwo);

        boolean isNotEqual = userStoryPriorityOne.equals(userStoryPriorityTwo);

        assertFalse(isNotEqual);
    }
    @Test
    @DisplayName(("Test for different UserStoryPriority objects won't result in true "))
    public void checkIfCreatingWithAnotherPriorityWillNotResultInTrue(){
        UserStoryPriority userStoryPriorityOne = new UserStoryPriority(priorityOne);
        UserStoryPriority userStoryPriorityTwo = new UserStoryPriority(priorityTwo);

        boolean isNotEqual = userStoryPriorityOne.equals(userStoryPriorityTwo);

        assertNotEquals(true, isNotEqual);
    }

    @Test
    @DisplayName("Test to ensure the return won't be true for different objects")
    public void checkIfWillNoReturnEquals(){
        UserStoryPriority userStoryPriorityOne = new UserStoryPriority(priorityOne);
        Object userStoryObject = null;

        boolean isNotEqual = userStoryPriorityOne.equals(userStoryObject);

        assertNotEquals(true, isNotEqual);
    }

    @Test
    @DisplayName(("Test for creation of Priority with zero"))
    public void checkIfClassCreatesPriorityWithZero() {
        UserStoryPriority userStoryPriorityOne = new UserStoryPriority(priorityZero);
    }

}