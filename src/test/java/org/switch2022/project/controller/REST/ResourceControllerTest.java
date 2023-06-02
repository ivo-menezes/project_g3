package org.switch2022.project.controller.REST;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.switch2022.project.mapper.NewResourceDTO;
import org.switch2022.project.mapper.REST.ResourceRestDTO;
import org.switch2022.project.mapper.REST.ResourceRestDTOMapper;
import org.switch2022.project.service.ResourceService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class ResourceControllerTest {

    @MockBean
    ResourceService resourceService;

    @MockBean
    ResourceRestDTOMapper mapper;

    @Autowired
    ResourceController controller;

    @DisplayName("ensure that create a resource correctly and HTTP status 201 - Created")
    @Test
    void shouldReturnCorrectDtoStatusCreated() {
        //Arrange
        ResourceRestDTO restDTO = mock(ResourceRestDTO.class);
        NewResourceDTO domainDTO = mock(NewResourceDTO.class);
        NewResourceDTO savedDomainDTO = mock(NewResourceDTO.class);
        ResourceRestDTO savedRestDTO = mock(ResourceRestDTO.class);

        when(mapper.toDomainDto(restDTO)).thenReturn(domainDTO);
        when(resourceService.createResource(domainDTO)).thenReturn(savedDomainDTO);
        when(mapper.toRestDto(savedDomainDTO)).thenReturn(savedRestDTO);

        //Act
        ResponseEntity<?> response = controller.createResource(restDTO);

        //Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(savedRestDTO, response.getBody());
    }

    @DisplayName("ensure that create a resource fails and HTTP status 400 - Bad Request")
    @Test
    void ensureResourceCreationFailsAndStatusBadRequest() {
        //Arrange
        ResourceRestDTO restDTO = mock(ResourceRestDTO.class);
        NewResourceDTO domainDTO = mock(NewResourceDTO.class);

        when(mapper.toDomainDto(restDTO)).thenReturn(domainDTO);
        when(resourceService.createResource(domainDTO)).thenThrow(RuntimeException.class);

        //Act
        ResponseEntity<?> response = controller.createResource(restDTO);

        //Assert
        assertEquals(400, response.getStatusCodeValue());
        assertEquals(restDTO, response.getBody());
    }
}