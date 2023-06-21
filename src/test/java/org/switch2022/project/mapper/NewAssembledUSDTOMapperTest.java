package org.switch2022.project.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.sprint.AssembledUS;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class NewAssembledUSDTOMapperTest {

    @Test
    @DisplayName("Ensure that dto is returned")

        //Arrange
    void ensureDtoIsReturned() {
        AssembledUS assembledUS = mock(AssembledUS.class);
        NewAssembledUSDTOMapper newAssembledUSDTOMapper = new NewAssembledUSDTOMapper();

        //Act
        NewAssembledUSDTO resultDto = newAssembledUSDTOMapper.toDto(assembledUS);

        //Assert
        assertInstanceOf(NewAssembledUSDTO.class, resultDto);
    }

}