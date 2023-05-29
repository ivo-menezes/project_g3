package org.switch2022.project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.switch2022.project.datamodel.JPA.BusinessSectorJPA;
import org.switch2022.project.datamodel.JPA.assemblers.BusinessSectorDomainDataAssembler;
import org.switch2022.project.model.businessSector.BusinessSectorDDD;
import org.switch2022.project.repository.JPA.BusinessSectorRepositoryJPA;
import org.switch2022.project.service.irepositories.IBusinessSectorRepository;

@Repository
public class BusinessSectorRepository implements IBusinessSectorRepository {

    @Autowired
    BusinessSectorRepositoryJPA businessSectorRepositoryJPA;

    @Autowired
    BusinessSectorDomainDataAssembler businessSectorDomainDataAssembler;

    @Override
    public BusinessSectorDDD save(BusinessSectorDDD businessSector) {
        boolean existBusinessSector = businessSectorRepositoryJPA.existsByBusinessSectorDesignation
                (businessSector.getBusinessSectorDesignation().toString());

        if (existBusinessSector) {
            throw new IllegalArgumentException("There is a business sector with this designation");
        }

        BusinessSectorJPA businessSectorJPA = businessSectorDomainDataAssembler.toData(businessSector);
        BusinessSectorJPA savedBusinessSectorJPA = businessSectorRepositoryJPA.save(businessSectorJPA);

        return businessSectorDomainDataAssembler.toDomain(savedBusinessSectorJPA);
    }
}
