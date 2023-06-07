package org.switch2022.project.service.irepositories;

import org.switch2022.project.model.account.AccountDDD;
import org.switch2022.project.model.valueobject.AccountID;

import java.util.Optional;

public interface IAccountRepository {

    AccountDDD save (AccountDDD account);

    Optional<AccountDDD> getByID(AccountID accountID);

    boolean existsByEmail(String email);
}

