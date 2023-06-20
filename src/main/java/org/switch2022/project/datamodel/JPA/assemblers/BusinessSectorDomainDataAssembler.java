package org.switch2022.project.datamodel.JPA.assemblers;

import org.springframework.stereotype.Component;
import org.switch2022.project.datamodel.JPA.BusinessSectorJPA;
import org.switch2022.project.model.businessSector.BusinessSectorDDD;
import org.switch2022.project.model.valueobject.BusinessSectorDesignation;
import org.switch2022.project.model.valueobject.BusinessSectorID;

@Component
public class BusinessSectorDomainDataAssembler {

    /**
     * Method responsible for converting domain objects into JPA.
     * @param businessSector
     * @return object in JPA format for persistence in the database.
     */
    public BusinessSectorJPA toData(BusinessSectorDDD businessSector) {
        return new BusinessSectorJPA(businessSector.getBusinessSectorDesignation().toString());
    }

    /**
     * Method responsible for converting JPA objects into domain objects.
     * @param businessSectorJPA
     * @return domain objects
     */

    public BusinessSectorDDD toDomain(BusinessSectorJPA businessSectorJPA) {
        BusinessSectorID businessSectorID = new BusinessSectorID(businessSectorJPA.getId());
        BusinessSectorDesignation businessSectorDesignation =
                new BusinessSectorDesignation(businessSectorJPA.getBusinessSectorDesignation());
        return new BusinessSectorDDD(businessSectorID,businessSectorDesignation);
    }
}
