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
import java.util.List;
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

    @DisplayName("ensure that creating an account throws an exception when resource service is null")
    @Test
    void shouldReturnExceptionWhenResourceServiceIsNull() {
        //Arrange
        resourceService = null;

        String expectedMessage = "ResourceService must not be null";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () ->
            new ResourceController(resourceService, mapper));

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("ensure that creating an account throws an exception when mapper is null")
    @Test
    void shouldReturnExceptionWhenMapperIsNull() {
        //Arrange
        mapper = null;

        String expectedMessage = "ResourceDTOMapper must not be null";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () ->
            new ResourceController(resourceService, mapper));

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

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

    @DisplayName("ensure getting a list of resources returns correct list of DTOs and HTTP status 200")
    @Test
    void shouldReturnListOfResourcesAndStatusOk() {
        //Arrange
        NewResourceDTO resourceDTODomain1 = mock(NewResourceDTO.class);
        NewResourceDTO resourceDTODomain2 = mock(NewResourceDTO.class);
        List<NewResourceDTO> listDomain = mock(List.class);

        listDomain.add(resourceDTODomain1);
        listDomain.add(resourceDTODomain2);

        ResourceRestDTO resourceDTOrest1 = mock(ResourceRestDTO.class);
        ResourceRestDTO resourceDTOrest2 = mock(ResourceRestDTO.class);
        List<ResourceRestDTO> listRest = mock(List.class);

        listRest.add(resourceDTOrest1);
        listRest.add(resourceDTOrest2);

        when(resourceService.getAllResources()).thenReturn(listDomain);
        when(mapper.toRestDTOList(listDomain)).thenReturn(listRest);

        //Act
        ResponseEntity<?> responseEntity = controller.getResource();

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }

    @DisplayName("ensure returns HTTP status 404 when service throws exception")
    @Test
    void shouldReturnStatusNotFound() {
        //Arrange
        NewResourceDTO resourceDTODomain1 = mock(NewResourceDTO.class);
        NewResourceDTO resourceDTODomain2 = mock(NewResourceDTO.class);
        List<NewResourceDTO> listDomain = mock(List.class);

        listDomain.add(resourceDTODomain1);
        listDomain.add(resourceDTODomain2);

        ResourceRestDTO resourceDTOrest1 = mock(ResourceRestDTO.class);
        ResourceRestDTO resourceDTOrest2 = mock(ResourceRestDTO.class);
        List<ResourceRestDTO> listRest = mock(List.class);

        listRest.add(resourceDTOrest1);
        listRest.add(resourceDTOrest2);

        when(resourceService.getAllResources()).thenThrow(RuntimeException.class);

        //Act
        ResponseEntity<?> responseEntity = controller.getResource();

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 404);

    }
}