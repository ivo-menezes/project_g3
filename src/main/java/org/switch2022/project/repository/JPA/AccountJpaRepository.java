package org.switch2022.project.repository.JPA;

import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.datamodel.JPA.AccountJpa;
import org.switch2022.project.model.account.AccountDDD;
import org.switch2022.project.model.valueobject.Email;

import java.util.Optional;

public interface AccountJpaRepository extends CrudRepository<AccountJpa, Long> {

        boolean existsByEmail(String email);

        Optional<AccountJpa> findByEmail (String email);

}
