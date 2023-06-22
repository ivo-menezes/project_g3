package org.switch2022.project.repository;

import org.switch2022.project.datamodel.JPA.AccountJpa;
import org.switch2022.project.datamodel.JPA.assemblers.AccountDomainDataAssembler;
import org.switch2022.project.model.account.AccountDDD;
import org.switch2022.project.model.valueobject.AccountID;
import org.switch2022.project.model.valueobject.Email;
import org.switch2022.project.repository.JPA.AccountJpaRepository;
import org.switch2022.project.service.irepositories.IAccountRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class AccountRepository implements IAccountRepository {
    private final AccountJpaRepository accountJpaRepository;

    private final AccountDomainDataAssembler accountDomainDataAssembler;

    public AccountRepository(AccountJpaRepository accountJpaRepository, AccountDomainDataAssembler accountDomainDataAssembler) {
        this.accountJpaRepository = accountJpaRepository;
        this.accountDomainDataAssembler = accountDomainDataAssembler;
    }

    @Override
    public AccountDDD save(AccountDDD account) {

        AccountJpa accountJpa = accountDomainDataAssembler.toData(account);
        AccountJpa savedAccountJpa = accountJpaRepository.save(accountJpa);
        AccountDDD savedAccount = accountDomainDataAssembler.toDomain(savedAccountJpa);

        return savedAccount;
    }

    public Optional<AccountDDD> getByID (AccountID accountID) {
        long accountIDJpa = accountID.getId();
        Optional<AccountJpa> accountJpaOptional = accountJpaRepository.findById(accountIDJpa);

        if (accountJpaOptional.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(accountDomainDataAssembler.toDomain(accountJpaOptional.get()));
        }
    }

    public Optional<AccountDDD> getByEmail(String email) {
        Optional<AccountJpa> accountJpaOptional = accountJpaRepository.findByEmail(email);
        if (accountJpaOptional.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(accountDomainDataAssembler.toDomain(accountJpaOptional.get()));
    }
}

    public Iterable<AccountDDD> findAll() {
        List<AccountDDD> allAccounts = new ArrayList<>();
        Iterable<AccountJpa> allAccountsJpa = accountJpaRepository.findAll();

        for (AccountJpa accountJpa : allAccountsJpa) {
            allAccounts.add(accountDomainDataAssembler.toDomain(accountJpa));
        }

        return allAccounts;
    }

    public boolean existsByEmail(String email) {

        return accountJpaRepository.existsByEmail(email);
    }

    public AccountID getAccountIDWhenInputEmailEqualsAccountEmail(String email) {
        Optional<AccountDDD> optionalAccount = getByEmail(email);
        if (optionalAccount.isPresent()) {
            AccountDDD account = optionalAccount.get();
            return account.identity();
        } else {
            return null;
        }
    }

    public Email getEmailWhenOutputAccountIDEqualsAccountAccountID (AccountID accountID) {
        Optional<AccountDDD> optionalAccount = getByID(accountID);
        if (optionalAccount.isPresent()) {
            AccountDDD account = optionalAccount.get();
            return account.getEmail();
        } else {
            return null;
        }
    }

    public List<AccountDDD> getAll() {
        List<AccountDDD> listOfAccounts = new ArrayList<>();

        Iterable<AccountJpa> accountJpaList = accountJpaRepository.findAll();

        for(AccountJpa accountJPA : accountJpaList) {
            listOfAccounts.add(accountDomainDataAssembler.toDomain(accountJPA));

        } return listOfAccounts;
    }
}