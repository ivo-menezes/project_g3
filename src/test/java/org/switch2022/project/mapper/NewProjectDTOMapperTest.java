package org.switch2022.project.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.project.ProjectDDD;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class NewProjectDTOMapperTest {

    @Test
    @DisplayName("Ensure that dto is returned")
    void ensureDtoIsReturned() {

        //Arrange
        ProjectDDD projectDDD = mock(ProjectDDD.class);
        NewProjectDTOMapper newProjectDTOMapper = new NewProjectDTOMapper();

        //Act
        NewProjectDTO resultDto = newProjectDTOMapper.toDto(projectDDD);

        //Assert
        assertInstanceOf(NewProjectDTO.class, resultDto);
    }
}