package org.switch2022.project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.switch2022.project.datamodel.JPA.AccountJpa;
import org.switch2022.project.datamodel.JPA.assemblers.AccountDomainDataAssembler;
import org.switch2022.project.model.account.AccountDDD;
import org.switch2022.project.model.valueobject.Email;
import org.switch2022.project.repository.JPA.AccountJpaRepository;
import org.switch2022.project.service.irepositories.IAccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository implements IAccountRepository {

    @Autowired
    private final AccountJpaRepository accountJpaRepository;

    @Autowired
    private final AccountDomainDataAssembler accountDomainDataAssembler;

    public AccountRepository(AccountJpaRepository accountJpaRepository, AccountDomainDataAssembler accountDomainDataAssembler) {
        this.accountJpaRepository = accountJpaRepository;
        this.accountDomainDataAssembler = accountDomainDataAssembler;
    }

    @Override
    public AccountDDD save(AccountDDD account) {
        boolean existAccount = accountJpaRepository.existsByEmail(account.getEmail().toString());

        if (existAccount) {
            throw new IllegalArgumentException("Already exists an account with the provided email");
        }

        AccountJpa accountJpa = accountDomainDataAssembler.toData(account);
        AccountJpa savedAccountJpa = accountJpaRepository.save(accountJpa);
        AccountDDD savedAccount = accountDomainDataAssembler.toDomain(savedAccountJpa);

        return savedAccount;
    }

    public Optional<AccountDDD> getByEmail (Email email) {
        String emailJpa = email.toString();
        Optional<AccountJpa> accountJpaOptional = accountJpaRepository.findByEmail(emailJpa);

        if (accountJpaOptional.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(accountDomainDataAssembler.toDomain(accountJpaOptional.get()));
        }
    }

    public Iterable<AccountDDD> findAll() {
        Iterable<AccountJpa> allAccountsJpa = accountJpaRepository.findAll();

        List<AccountDDD> allAccounts = new ArrayList<>();

        for (AccountJpa accountJpa : allAccountsJpa) {
            allAccounts.add(accountDomainDataAssembler.toDomain(accountJpa));
        }

        return allAccounts;
    }

    public void clearRepository() {
        accountJpaRepository.deleteAll();
    }
}