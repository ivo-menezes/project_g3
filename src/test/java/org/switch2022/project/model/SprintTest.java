package org.switch2022.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SprintTest {

    /***
     * Testing the creation of Sprint successfully
     */
    @Test
    void createSprintSuccessfully() {
        Sprint sprint = new Sprint(1, new Date(2021, Calendar.JANUARY, 1), new Date(2021, Calendar.FEBRUARY, 1));

        assertTrue(sprint.equals(sprint));
    }

    @Test
    void ensureThatHashCodeIsTested() {
        Sprint sprint = new Sprint(1, new Date(2021, Calendar.JANUARY, 1), new Date(2021, Calendar.FEBRUARY, 1));

        assertNotEquals(0, sprint.hashCode());
    }

    @Test
    void ensureThatHashCodeIsTestedAgain() {
        Sprint sprint = new Sprint(1, new Date(2021, Calendar.JANUARY, 1), new Date(2021, Calendar.FEBRUARY, 1));

        assertEquals(sprint.hashCode(), sprint.hashCode());
    }

    /***
     * The tests to ensure any empty field isn't accepted.
     */
    @Test
    void checkIfSprintNumberIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Sprint sprint = new Sprint(0, new Date(2021, Calendar.JANUARY, 1), new Date(2021, Calendar.FEBRUARY, 1));
        });
        Assertions.assertEquals("Missing value, please try again.", exception.getMessage());
    }

    @Test
    void checkIfStartDateIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Sprint sprint = new Sprint(1, null, new Date(2021, Calendar.FEBRUARY, 1));
        });
        Assertions.assertEquals("Missing value, please try again.", exception.getMessage());
    }

    @Test
    void checkIfEndDateIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Sprint sprint = new Sprint(1, new Date(2021, Calendar.JANUARY, 1), null);
        });
        Assertions.assertEquals("Missing value, please try again.", exception.getMessage());
    }

    @Test
    @DisplayName("ensure user story successfully added to sprint Backlog")
    void addUserStoryToSprintBacklogTrue() {
        //Arrange
        UserStory userStory = mock(UserStory.class);
        Sprint sprint = new Sprint(10, new Date(07 - 03 - 2023), new Date(14 - 03 - 2023));

        //Act
        boolean result = sprint.addUserStoryToSprintBacklog(userStory);

        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("ensure the same user story cannot been added twice to sprint Backlog")
    void addUserStoryToSprintBacklogFalse() {
        //Arrange
        UserStory userStory = mock(UserStory.class);
        Sprint sprint = new Sprint(10, new Date(07 - 03 - 2023), new Date(14 - 03 - 2023));

        //Act
        sprint.addUserStoryToSprintBacklog(userStory);
        boolean result = sprint.addUserStoryToSprintBacklog(userStory);

        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("ensure that when a user story equals null it is not added to the Sprint Backlog")
    void addUserStoryToSprintBacklog() {
        //Arrange
        UserStory userStory = null;
        Sprint sprint = new Sprint(10, new Date(07 - 03 - 2023), new Date(14 - 03 - 2023));

        //Act and Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            sprint.addUserStoryToSprintBacklog(userStory);
        });
    }
    @Test
    @DisplayName("ensure the creation and addition of USDTO to Scrum Board list")
    void createAndAddUsDTOToScrumBoardList() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Sprint sprint = new Sprint(1, formatter.parse("01/02/2022"), formatter.parse("15/02/2022"));

        UserStory userStory = mock(UserStory.class);
        UserStory userStoryTwo = mock(UserStory.class);

        sprint.addUserStoryToSprintBacklog(userStory);
        sprint.addUserStoryToSprintBacklog(userStoryTwo);

        List result = sprint.viewScrumBoardList();

        assertNotNull(result);
    }
    @Test
    @DisplayName("ensure the Scrum Board list isn't empty")
    void testingTheScrumBoardListIsNotEmpty() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Sprint sprint = new Sprint(1, formatter.parse("01/02/2022"), formatter.parse("15/02/2022"));

        UserStory userStory = mock(UserStory.class);
        UserStory userStoryTwo = mock(UserStory.class);

        sprint.addUserStoryToSprintBacklog(userStory);
        sprint.addUserStoryToSprintBacklog(userStoryTwo);

        List result = sprint.viewScrumBoardList();

        assertFalse(result.isEmpty());
    }
    @Test
    @DisplayName("ensure the USDTO isn't null")
    void testingTheCreationOfUSDTO() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Sprint sprint = new Sprint(1, formatter.parse("01/02/2022"), formatter.parse("15/02/2022"));
        UserStory userStory = mock(UserStory.class);

        sprint.addUserStoryToSprintBacklog(userStory);
        List result = sprint.viewScrumBoardList();
        UserStoryDTO userStoryDTO = (UserStoryDTO) result.get(0);

        assertNotNull(userStoryDTO);
    }
    @Test
    @DisplayName("Ensure that effort value is not valid")
    void effortValueIsNotValid() {
        //Arrange
        Sprint sprint = new Sprint(10, new Date(07 - 03 - 2023), new Date(15 - 03 - 2023));
        double effort = 7.0;

        //Act
        boolean result = sprint.validEffortEstimate(effort);

        //Assert
        assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.5, 1.0, 2.0, 3.0, 5.0, 8.0, 13.0, 20.0, 40.0})
    @DisplayName("Ensure that effort value is valid")
    void effortValueIsValidPT(double effort) {
        Sprint sprint = new Sprint(10, new Date(07-03-2023), new Date(15-03-2023));
        assertTrue(sprint.validEffortEstimate(effort));
    }

    @Test
    @DisplayName("Ensure that effort value of an user story is estimated")
    void effortValueOfAnUserStoryIsEstimated() {
        //Arrange
        Sprint sprint = new Sprint(10, new Date(07 - 03 - 2023), new Date(15 - 03 - 2023));
        UserStory userStoryDouble = mock(UserStory.class);
        sprint.addUserStoryToSprintBacklog(userStoryDouble);
        Mockito.when(userStoryDouble.getId()).thenReturn("1");

        //Act
        boolean result = sprint.estimateEffortForUserStory("1", 2.0);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure that effort value of an user story is not estimated if user story does not exist")
    void effortValueOfAnUserStoryIsNotEstimatedIfUserStoryDoesNotExist() {
        //Arrange
        Sprint sprint = new Sprint(10, new Date(07 - 03 - 2023), new Date(15 - 03 - 2023));
        UserStory userStoryDouble = mock(UserStory.class);
        sprint.addUserStoryToSprintBacklog(userStoryDouble);
        Mockito.when(userStoryDouble.getId()).thenReturn("1");

        //Act
        boolean result = sprint.estimateEffortForUserStory("2", 2.0);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that effort value of an user story is not estimated when userStoryId is null")
    void effortValueOfAnUserStoryIsNotEstimatedWhenUserStoryIdIsNull() {
        //Arrange
        Sprint sprint = new Sprint(10, new Date(07 - 03 - 2023), new Date(15 - 03 - 2023));
        UserStory userStoryDouble = mock(UserStory.class);
        sprint.addUserStoryToSprintBacklog(userStoryDouble);
        Mockito.when(userStoryDouble.getId()).thenReturn("1");
        String userStoryId = null;

        //Act & Assert
        assertThrows(NullPointerException.class, () -> {sprint.estimateEffortForUserStory(userStoryId, 2.0);} );
    }

    @Test
    @DisplayName("Ensure that effort value of an user story is not estimated when effort is null")
    void effortValueOfAnUserStoryIsNotEstimatedWhenEffortIsNull() {
        //Arrange
        Sprint sprint = new Sprint(10, new Date(07 - 03 - 2023), new Date(15 - 03 - 2023));
        UserStory userStoryDouble = mock(UserStory.class);
        sprint.addUserStoryToSprintBacklog(userStoryDouble);
        Mockito.when(userStoryDouble.getId()).thenReturn("1");

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {sprint.estimateEffortForUserStory("1", 2.8);} );
    }
}