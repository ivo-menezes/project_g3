package org.switch2022.project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.switch2022.project.datamodel.JPA.BusinessSectorJPA;
import org.switch2022.project.datamodel.JPA.assemblers.BusinessSectorDomainDataAssembler;
import org.switch2022.project.model.businessSector.BusinessSectorDDD;
import org.switch2022.project.model.valueobject.BusinessSectorID;
import org.switch2022.project.repository.JPA.BusinessSectorRepositoryJPA;
import org.switch2022.project.service.irepositories.IBusinessSectorRepository;

import java.util.ArrayList;

@Repository
public class BusinessSectorRepository implements IBusinessSectorRepository {

    @Autowired
    BusinessSectorRepositoryJPA businessSectorRepositoryJPA;

    @Autowired
    BusinessSectorDomainDataAssembler businessSectorDomainDataAssembler;

    /**
     * Method responsible for saving the business Sector in the database.
     * @param businessSector
     * @return businessSectorDDD
     */
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

    /**
     * Method responsible for return all BusinessSectors from database.
     * @return ArrayList<BusinessSectorDDD>
     */
    public ArrayList<BusinessSectorDDD> getAll() {
        ArrayList<BusinessSectorDDD> customers = new ArrayList();

        Iterable<BusinessSectorJPA> businessSectorsJPA = businessSectorRepositoryJPA.findAll();

        for (BusinessSectorJPA businessSector : businessSectorsJPA) {
            customers.add(businessSectorDomainDataAssembler.toDomain(businessSector));
        }

        return customers;
    }

    /**
     * Verifies if this id exists
     * @param businessSectorID the id to be checked
     * @return true if it already exists, false otherwise
     */
    public boolean containsID(BusinessSectorID businessSectorID) {
        return businessSectorRepositoryJPA.existsById(businessSectorID.getId());
    }
}
