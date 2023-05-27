package org.switch2022.project.repository.JPA;


import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.datamodel.JPA.TypologyJpa;

import java.util.Optional;

public interface TypologyJpaRepository extends CrudRepository<TypologyJpa, Long> {

    boolean existsByTypologyDesignation(String designation);

    Optional<TypologyJpa> getByTypologyDesignation(String designation);

}


