package org.switch2022.project.datamodel.JPA.assemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.datamodel.JPA.BusinessSectorJPA;
import org.switch2022.project.model.businessSector.BusinessSectorDDD;
import org.switch2022.project.model.valueobject.BusinessSectorDesignation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BusinessSectorDomainDataAssemblerTest {

    @Test
    @DisplayName("Ensure business sector is correctly converted to businessSectorJPA")
    void ensureBusinessSectorIsConvertedToBusinessSectorJPA(){
        //Arrange
        BusinessSectorDesignation businessSectorDesignation = mock(BusinessSectorDesignation.class);
        BusinessSectorDDD businessSector = mock(BusinessSectorDDD.class);

        when(businessSector.getBusinessSectorDesignation()).thenReturn(businessSectorDesignation);
        when(businessSectorDesignation.toString()).thenReturn("Fixed cost");

        BusinessSectorDomainDataAssembler assembler = new BusinessSectorDomainDataAssembler();

        //Act
        BusinessSectorJPA returnedBusinessSectorJPA = assembler.toData(businessSector);

        //Assert
        assertInstanceOf(BusinessSectorJPA.class, returnedBusinessSectorJPA);
    }

    @Test
    @DisplayName("Ensure businessSectorJPA is correctly converted to businessSector")
    void ensureTypologyJpaIsConvertedToTypology(){
        //Arrange
        String businessSectorDesignation = "Fixed cost";
        BusinessSectorJPA businessSectorJPA = mock(BusinessSectorJPA.class);

        when(businessSectorJPA.getBusinessSectorDesignation()).thenReturn(businessSectorDesignation);

        BusinessSectorDomainDataAssembler assembler = new BusinessSectorDomainDataAssembler();

        //Act
        BusinessSectorDDD returnedBusinessSector = assembler.toDomain(businessSectorJPA);

        //Assert
        assertInstanceOf(BusinessSectorDDD.class, returnedBusinessSector);
    }
}