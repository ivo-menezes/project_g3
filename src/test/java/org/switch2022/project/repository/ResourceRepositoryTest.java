package org.switch2022.project.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.datamodel.JPA.ResourceJPA;
import org.switch2022.project.datamodel.JPA.assemblers.ResourceDomainAssemblerData;
import org.switch2022.project.model.resource.ResourceDDD;
import org.switch2022.project.model.valueobject.*;
import org.switch2022.project.repository.JPA.ResourceRepositoryJPA;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResourceRepositoryTest {

    @Test
    @DisplayName("Ensure Resource is successfully saved")
    void ensureResourceIsSaved(){
        //Arrange
        ResourceRepositoryJPA resourceRepositoryJPA = mock(ResourceRepositoryJPA.class);
        ResourceDomainAssemblerData assembler = mock(ResourceDomainAssemblerData.class);
        ResourceDDD resourceDDD = mock(ResourceDDD.class);
        ResourceDDD savedResourceDDD = mock(ResourceDDD.class);
        ResourceJPA resourceJPA = mock(ResourceJPA.class);
        ResourceJPA savedResourceJPA = mock(ResourceJPA.class);

        when(assembler.toData(resourceDDD)).thenReturn(resourceJPA);
        when(resourceRepositoryJPA.save(resourceJPA)).thenReturn(savedResourceJPA);
        when(assembler.toDomain(savedResourceJPA)).thenReturn(savedResourceDDD);

        ResourceRepository resourceRepository = new ResourceRepository(resourceRepositoryJPA, assembler);

        //Act
        ResourceDDD expectedResource = resourceRepository.save(resourceDDD);

        //Assert
        assertEquals(expectedResource, savedResourceDDD);
    }

    @Test
    @DisplayName("Check if role is occupied and return true")
    void returnTrueWhenIsOccupied(){
        // Arrange
        ResourceDomainAssemblerData resourceDomainAssemblerData = new ResourceDomainAssemblerData();
        ResourceRepositoryJPA resourceRepositoryJPA = mock(ResourceRepositoryJPA.class);
        ResourceDDD resourceDDD = mock(ResourceDDD.class);
        Role role = mock(Role.class);
        when(role.toString()).thenReturn("Product_Owner");
        when(resourceDDD.getRole()).thenReturn(role);
        when(resourceRepositoryJPA.existsByRole("Product_Owner")).thenReturn(true);
        ResourceRepository resourceRepository = new ResourceRepository(resourceRepositoryJPA,resourceDomainAssemblerData);

        // Act
        boolean result = resourceRepository.isRoleOccupied(resourceDDD);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Check if role is not occupied and return false")
    void returnFalseWhenIsNotOccupied(){
        // Arrange
        ResourceDomainAssemblerData resourceDomainAssemblerData = new ResourceDomainAssemblerData();
        ResourceRepositoryJPA resourceRepositoryJPA = mock(ResourceRepositoryJPA.class);
        ResourceDDD resourceDDD = mock(ResourceDDD.class);
        Role role = mock(Role.class);
        when(role.toString()).thenReturn("Product_Owner");
        when(resourceDDD.getRole()).thenReturn(role);
        when(resourceRepositoryJPA.existsByRole("Product_Owner")).thenReturn(false);
        ResourceRepository resourceRepository = new ResourceRepository(resourceRepositoryJPA,resourceDomainAssemblerData);

        // Act
        boolean result = resourceRepository.isRoleOccupied(resourceDDD);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Check if get all works correctly")
    void getAllSuccessfullyWorks(){
        //Arrange
        ResourceRepositoryJPA resourceRepositoryJPA = mock(ResourceRepositoryJPA.class);
        ResourceDomainAssemblerData assembler = mock(ResourceDomainAssemblerData.class);

        List<ResourceJPA> resourceJPAList = new ArrayList<>();
        List<ResourceDDD> resources = new ArrayList<>();

        ResourceJPA resourceJPA = mock(ResourceJPA.class);
        resourceJPAList.add(resourceJPA);

        ResourceDDD resourceDDD = mock(ResourceDDD.class);

        when(resourceRepositoryJPA.findAll()).thenReturn(resourceJPAList);
        when(assembler.toDomain(resourceJPA)).thenReturn(resourceDDD);

        resources.add(resourceDDD);

        ResourceRepository resourceRepository = new ResourceRepository(resourceRepositoryJPA, assembler);

        //Act
        List<ResourceDDD> result = resourceRepository.getAll();

        //Assert
        assertEquals(resources, result);
    }



}