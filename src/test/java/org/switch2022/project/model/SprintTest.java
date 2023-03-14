package org.switch2022.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

        List result = sprint.createScrumBoardList();

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

        List result = sprint.createScrumBoardList();

        assertFalse(result.isEmpty());
    }
    @Test
    @DisplayName("ensure the USDTO isn't null")
    void testingTheCreationOfUSDTO() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Sprint sprint = new Sprint(1, formatter.parse("01/02/2022"), formatter.parse("15/02/2022"));
        UserStory userStory = mock(UserStory.class);

        sprint.addUserStoryToSprintBacklog(userStory);
        List result = sprint.createScrumBoardList();
        UserStoryDTO userStoryDTO = (UserStoryDTO) result.get(0);

        assertNotNull(userStoryDTO);
    }
}