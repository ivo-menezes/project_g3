package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.mapper.BusinessSectorDTO;
import org.switch2022.project.model.businessSector.BusinessSectorDDD;
import org.switch2022.project.model.businessSector.IBusinessSectorFactory;
import org.switch2022.project.service.irepositories.IBusinessSectorRepository;

@Service
public class BusinessSectorService {

    @Autowired
    private IBusinessSectorFactory businessSectorFactory;

    @Autowired
    private IBusinessSectorRepository businessSectorRepository;


    /**
     * Default constructor that receives the factory and repository as dependency injection.
     * @param businessSectorFactory
     * @param businessSectorRepository
     */
    public BusinessSectorService(IBusinessSectorFactory businessSectorFactory, IBusinessSectorRepository businessSectorRepository) {
        if (businessSectorFactory == null) {
            throw new IllegalArgumentException("businessSectorFactory must not be null.");
        }
        if (businessSectorRepository == null) {
            throw new IllegalArgumentException("businessSectorRepository must not be null.");
        }
        this.businessSectorFactory = businessSectorFactory;
        this.businessSectorRepository = businessSectorRepository;
    }

    /**
     * Method responsible for creating and saving the business sector.
     * @param businessSectorDTO
     * @return the businessSectorDTO.
     */
    public BusinessSectorDTO createBusinessSector(BusinessSectorDTO businessSectorDTO) {
        BusinessSectorDDD businessSector = businessSectorFactory
                .createBusinessSector(
                        businessSectorDTO.businessSectorID,
                        businessSectorDTO.businessSectorDesignation
                );
        BusinessSectorDDD savedBusinessSector = businessSectorRepository.save(businessSector);
        businessSectorDTO.businessSectorID = savedBusinessSector.identity();

        return businessSectorDTO;

    }
}
