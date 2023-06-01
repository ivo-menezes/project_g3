package org.switch2022.project.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.datamodel.JPA.ResourceJPA;
import org.switch2022.project.datamodel.JPA.assemblers.ResourceDomainAssemblerData;
import org.switch2022.project.model.resource.ResourceDDD;
import org.switch2022.project.repository.JPA.ResourceRepositoryJPA;

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

}