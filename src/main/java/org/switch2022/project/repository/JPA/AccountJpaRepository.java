package org.switch2022.project.repository.JPA;

import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.datamodel.JPA.AccountJpa;

import java.util.Optional;

public interface AccountJpaRepository extends CrudRepository<AccountJpa, Long> {

        boolean existsByEmail(String email);

        Optional<AccountJpa> findById (long accountID);

        Optional<AccountJpa> findByEmail (String email);

}
