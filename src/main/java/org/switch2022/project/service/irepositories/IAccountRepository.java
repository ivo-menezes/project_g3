package org.switch2022.project.service.irepositories;

import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.ddd.DomainId;
import org.switch2022.project.ddd.RepositoryNew;
import org.switch2022.project.model.account.AccountDDD;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.AccountID;
import org.switch2022.project.model.valueobject.Email;
import org.switch2022.project.model.valueobject.SprintID;

import java.util.Optional;

public interface IAccountRepository {

    AccountDDD save (AccountDDD account);

    Optional<AccountDDD> getByEmail(Email email);
}

