package org.switch2022.project.model.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.UserStoryID;
import org.switch2022.project.model.valueobject.UserStoryPriority;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectDDDTest {

    /***
     * The test is used to ensure the ProjectDDD is correctly created, using a
     * mock for the ProjectCode class.
     */
    @Test
    @DisplayName("Test for a successful creation of ProjectDDD")
    public void checkIfTheProjectDDDIsSuccessfulCreated(){
        ProjectCode projectCodeMock = mock(ProjectCode.class);

        ProjectDDD projectOne = new ProjectDDD(projectCodeMock);
    }

    /***
     * This tests to ensure the mock object is correctly inserted into the product backlog.
     */
    @Test
    @DisplayName("Test for a successful addition to Backlog")
    public void checkIfTheProjectDDDAddsToBacklog(){

        ProjectCode projectCodeMock = mock(ProjectCode.class);
        ProjectDDD project = new ProjectDDD(projectCodeMock);
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryPriority priority = mock(UserStoryPriority.class);

        boolean result = project.addToProductBacklog(userStoryID, priority);

        assertTrue(result);
    }

    /***
     * This tests for a successful call for the list, using the UserStoryID.
     */
    @Test
    @DisplayName("Test for a successful call of List")
    public void checkIfTheProjectDDDReturnsProductBacklog(){

        ProjectCode projectCodeMock = mock(ProjectCode.class);
        ProjectDDD project = new ProjectDDD(projectCodeMock);
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryPriority priority = mock(UserStoryPriority.class);

        project.addToProductBacklog(userStoryID, priority);

        assertEquals(userStoryID, project.getProductBacklog().get(project.getProductBacklog().size() - 1));
    }

    /***
     *
     */
    @Test
    @DisplayName("Test for the list returning more than one UserStory")
    public void checkIfTheProjectDDDDoesNotReturnProductBacklogWithNegativeValueSize(){

        ProjectCode projectCodeMock = mock(ProjectCode.class);
        ProjectDDD project = new ProjectDDD(projectCodeMock);
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryPriority priority = mock(UserStoryPriority.class);
        when(priority.getValue()).thenReturn(0);
        UserStoryID userStoryIDTwo = mock(UserStoryID.class);
        UserStoryPriority priorityTwo = mock(UserStoryPriority.class);
        when(priority.getValue()).thenReturn(1);

        project.addToProductBacklog(userStoryID, priority);
        project.addToProductBacklog(userStoryIDTwo, priorityTwo);

        assertNotEquals(-2, project.getProductBacklog().get(project.getProductBacklog().size() - 1));
    }
    @Test
    @DisplayName("Test for the list returning more than one UserStory")
    public void checkIfTheProjectDDDDoesNotReturnProductBacklogWithValuesAboveTheTrueSize(){

        ProjectCode projectCodeMock = mock(ProjectCode.class);
        ProjectDDD project = new ProjectDDD(projectCodeMock);
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryPriority priority = mock(UserStoryPriority.class);
        when(priority.getValue()).thenReturn(0);
        UserStoryID userStoryIDTwo = mock(UserStoryID.class);
        UserStoryPriority priorityTwo = mock(UserStoryPriority.class);
        when(priority.getValue()).thenReturn(1);

        project.addToProductBacklog(userStoryID, priority);
        project.addToProductBacklog(userStoryIDTwo, priorityTwo);

        assertNotEquals(3, project.getProductBacklog().get(project.getProductBacklog().size() - 1));
    }
}