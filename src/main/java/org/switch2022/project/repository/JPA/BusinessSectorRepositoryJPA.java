package org.switch2022.project.repository.JPA;

import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.datamodel.JPA.BusinessSectorJPA;

public interface BusinessSectorRepositoryJPA extends CrudRepository<BusinessSectorJPA, Long> {

    boolean existsByBusinessSectorDesignation(String designation);
}
