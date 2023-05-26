package org.switch2022.project.repository.JPA;

import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.datamodel.JPA.CustomerJPA;

public interface CustomerRepositoryJPA extends CrudRepository<CustomerJPA, String> {

}
