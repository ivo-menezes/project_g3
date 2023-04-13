package org.switch2022.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.UserStoryDTO;
import org.switch2022.project.mapper.UserStoryMapper;
import org.switch2022.project.model.*;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.userStory.UserStory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ConsultProductBacklogControllerTest {

    @DisplayName("ensure controller is successfully created")
    @Test
    void ensureControllerCreatedSuccess() {

        //arrange
        ProjectList projectListDouble = mock(ProjectList.class);
        UserStoryMapper userStoryMapperDouble = mock(UserStoryMapper.class);

        //act
        ConsultProductBacklogController controller = new ConsultProductBacklogController(projectListDouble, userStoryMapperDouble);

        //assert
        assertInstanceOf(ConsultProductBacklogController.class, controller);
    }

    @DisplayName("ensure exception is thrown when project list is null")
    @Test
    void nullProjectListThrowsException() {

        //arrange
        ProjectList projectList = null;
        UserStoryMapper userStoryMapper = mock(UserStoryMapper.class);
        String expectedMessage = "Project List must not be null";

        //act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ConsultProductBacklogController(projectList, userStoryMapper);
        });
        String resultMessage = exception.getMessage();

        //assert
        assertEquals(expectedMessage, resultMessage);

    }

    @DisplayName("ensure exception is thrown when userStoryMapper is null")
    @Test
    void nullMapperThrowsException(){

        //arrange
        ProjectList projectList = mock(ProjectList.class);
        UserStoryMapper userStoryMapper = null;
        String expectedMessage = "Mapper must not be null";

        //act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ConsultProductBacklogController(projectList, userStoryMapper);
        });
        String resultMessage = exception.getMessage();

        //assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("ensure controller returns list of userStoryDTOs")
    @Test
    void ensureUserStoryDTOListIsReturned() {

        //arrange
            //creating the necessary mocks
        Project projectDouble = mock(Project.class);
        ProjectList projectListDouble = mock(ProjectList.class);
        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);
        UserStoryMapper userStoryMapperDouble = mock(UserStoryMapper.class);

        when(projectListDouble.getProject(911)).thenReturn(projectDouble);
        when(projectDouble.getProductBacklog()).thenReturn(productBacklogDouble);
            //setting up list of mocked user stories. An empty list works.
        List<UserStory> userStoryList = new ArrayList<>();
        when(productBacklogDouble.getUserStoryList()).thenReturn(userStoryList);
            //setting up list of DTOs
        List<UserStoryDTO> userStoryDTOList = new ArrayList<>();
        UserStoryDTO userStoryDTO1 = new UserStoryDTO();
        userStoryDTO1.id = "US001";
        UserStoryDTO userStoryDTO2 = new UserStoryDTO();
        userStoryDTO2.id = "US002";
        userStoryDTOList.add(userStoryDTO1);
        userStoryDTOList.add(userStoryDTO2);
        when(userStoryMapperDouble.toDTOList(userStoryList)).thenReturn(userStoryDTOList);

            //setting up the expected list
        List<UserStoryDTO> expectedList = new ArrayList<>();
        UserStoryDTO userStoryDTO3 = new UserStoryDTO();
        userStoryDTO3.id = "US001";
        UserStoryDTO userStoryDTO4 = new UserStoryDTO();
        userStoryDTO4.id = "US002";
        expectedList.add(userStoryDTO3);
        expectedList.add(userStoryDTO4);

            //instantiating an actual controller
        ConsultProductBacklogController controller = new ConsultProductBacklogController(projectListDouble, userStoryMapperDouble);

        //act
        List<UserStoryDTO> resultList = controller.getProductBacklog(911);

        //assert
        assertEquals(expectedList, resultList);
    }
}