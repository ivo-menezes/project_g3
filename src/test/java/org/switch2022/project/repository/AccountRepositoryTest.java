package org.switch2022.project.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.datamodel.JPA.AccountJpa;
import org.switch2022.project.datamodel.JPA.assemblers.AccountDomainDataAssembler;
import org.switch2022.project.model.account.AccountDDD;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.valueobject.*;
import org.switch2022.project.repository.JPA.AccountJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountRepositoryTest {

    @DisplayName("Ensure that account was successfully saved.")
    @Test
    void saveAccountSuccess() {
        //Arrange
        Email email = mock(Email.class);
        when(email.toString()).thenReturn("xpto@gmail.com");
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);
        Profile profile = mock(Profile.class);
        AccountStatus accountStatus = mock(AccountStatus.class);

        AccountDDD account = mock(AccountDDD.class);
        when(account.getEmail()).thenReturn(email);
        when(account.getName()).thenReturn(name);
        when(account.getPhoneNumber()).thenReturn(phoneNumber);
        when(account.getPhoto()).thenReturn(photo);
        when(account.getProfile()).thenReturn(profile);
        when(account.getAccountStatus()).thenReturn(accountStatus);

        AccountJpa accountJpa = mock(AccountJpa.class);
        AccountJpa savedAccountJpa = mock(AccountJpa.class);
        AccountJpaRepository accountJpaRepository = mock(AccountJpaRepository.class);
        AccountDomainDataAssembler accountDomainDataAssembler = mock(AccountDomainDataAssembler.class);
        AccountRepository accountRepository = new AccountRepository(accountJpaRepository, accountDomainDataAssembler);

        when(accountJpaRepository.existsByEmail(account.getEmail().toString())).thenReturn(false);
        when(accountDomainDataAssembler.toData(account)).thenReturn(accountJpa);
        when(accountJpaRepository.save(accountJpa)).thenReturn(savedAccountJpa);
        when(accountDomainDataAssembler.toDomain(savedAccountJpa)).thenReturn(account);

        //Act
        AccountDDD resultingAccount = accountRepository.save(account);

        //Assert
        assertEquals(account,resultingAccount);
    }

    @DisplayName("Ensure exception is thrown while saving already existing account.")
    @Test
    void ensureExistingAccountThrownExceptionWhenAddingAnotherAccountWithSameEmail() {
        //Arrange
        AccountDDD account = mock(AccountDDD.class);
        AccountJpaRepository accountJpaRepository = mock(AccountJpaRepository.class);
        AccountDomainDataAssembler accountDomainDataAssembler = mock(AccountDomainDataAssembler.class);
        AccountRepository accountRepository = new AccountRepository(accountJpaRepository, accountDomainDataAssembler);

        Email email = mock(Email.class);
        String designation = "xpto@gmail.com";

        when(account.getEmail()).thenReturn(email);
        when(email.toString()).thenReturn(designation);

        when(accountJpaRepository.existsByEmail(account.getEmail().toString())).thenReturn(true);
        String expected = "Already exists an account with the provided email";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                accountRepository.save(account));
        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expected, actualMessage);
    }

    @DisplayName("ensure findAll accounts returns a correct collection of accounts")
    @Test
    void shouldReturnAListOfAccounts() {
        //Arrange
        AccountJpa accountJpa1 = mock(AccountJpa.class);
        AccountJpa accountJpa2 = mock(AccountJpa.class);
        AccountJpa accountJpa3 = mock(AccountJpa.class);

        List<AccountJpa> listAccountsJpa = new ArrayList<>();
        listAccountsJpa.add(accountJpa1);
        listAccountsJpa.add(accountJpa2);
        listAccountsJpa.add(accountJpa3);

        AccountJpaRepository accountJpaRepository = mock(AccountJpaRepository.class);
        AccountDomainDataAssembler assembler = mock(AccountDomainDataAssembler.class);

        AccountDDD account1 = mock(AccountDDD.class);
        AccountDDD account2 = mock(AccountDDD.class);
        AccountDDD account3 = mock(AccountDDD.class);

        when(accountJpaRepository.findAll()).thenReturn(listAccountsJpa);
        when(assembler.toDomain(accountJpa1)).thenReturn(account1);
        when(assembler.toDomain(accountJpa2)).thenReturn(account2);
        when(assembler.toDomain(accountJpa3)).thenReturn(account3);

        List<AccountDDD> allAccounts = new ArrayList<>();
        allAccounts.add(account1);
        allAccounts.add(account2);
        allAccounts.add(account3);

        AccountRepository accountRepository = new AccountRepository(accountJpaRepository, assembler);

        //Act
        Iterable<AccountDDD> result = accountRepository.findAll();

        //Assert
        assertEquals(allAccounts, result);
    }

    @DisplayName("ensure getByEmail returns account")
    @Test
    void shouldReturnAccount() {
        //Arrange
        AccountJpaRepository accountJpaRepository = mock(AccountJpaRepository.class);
        AccountDomainDataAssembler assembler = mock(AccountDomainDataAssembler.class);
        AccountRepository accountRepository = new AccountRepository(accountJpaRepository,assembler);

        AccountDDD account = mock(AccountDDD.class);
        Email email = mock(Email.class);
        when(account.getEmail()).thenReturn(email);

        AccountJpa accountJpa = mock(AccountJpa.class);
        Optional<AccountJpa> accountJpaOptional = Optional.of(accountJpa);

        when(accountJpaRepository.findByEmail(account.getEmail().toString())).thenReturn(accountJpaOptional);
        when(assembler.toDomain(accountJpa)).thenReturn(account);

        Optional<AccountDDD> accountOptional = Optional.of(account);

        //Act
        Optional<AccountDDD> result = accountRepository.getByEmail(email);

        //Assert
        assertEquals(accountOptional,result);
    }

    @DisplayName("ensure getByEmail returns empty")
    @Test
    void shouldReturnEmptyOptional() {
        //Arrange
        AccountJpaRepository accountJpaRepository = mock(AccountJpaRepository.class);
        AccountDomainDataAssembler assembler = mock(AccountDomainDataAssembler.class);
        AccountRepository accountRepository = new AccountRepository(accountJpaRepository,assembler);

        AccountDDD account = mock(AccountDDD.class);
        Email email = mock(Email.class);
        when(account.getEmail()).thenReturn(email);

        when(accountJpaRepository.findByEmail(account.getEmail().toString())).thenReturn(Optional.empty());

        //Act
        Optional<AccountDDD> result = accountRepository.getByEmail(email);

        //Assert
        assertEquals(Optional.empty(),result);
    }
}