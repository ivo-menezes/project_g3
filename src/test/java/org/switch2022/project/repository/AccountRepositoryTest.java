package org.switch2022.project.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.switch2022.project.datamodel.JPA.AccountJpa;
import org.switch2022.project.datamodel.JPA.assemblers.AccountDomainDataAssembler;
import org.switch2022.project.model.account.AccountDDD;
import org.switch2022.project.model.valueobject.*;
import org.switch2022.project.repository.JPA.AccountJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
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
        ProfileName profile = mock(ProfileName.class);
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

    @DisplayName("ensure findById returns account")
    @Test
    void shouldReturnAccount() {
        //Arrange
        AccountJpaRepository accountJpaRepository = mock(AccountJpaRepository.class);
        AccountDomainDataAssembler assembler = mock(AccountDomainDataAssembler.class);
        AccountRepository accountRepository = new AccountRepository(accountJpaRepository,assembler);

        AccountDDD account = mock(AccountDDD.class);
        AccountID accountID = mock(AccountID.class);
        when(account.identity()).thenReturn(accountID);

        AccountJpa accountJpa = mock(AccountJpa.class);
        Optional<AccountJpa> accountJpaOptional = Optional.of(accountJpa);

        when(accountJpaRepository.findById(account.identity().getId())).thenReturn(accountJpaOptional);
        when(assembler.toDomain(accountJpa)).thenReturn(account);

        Optional<AccountDDD> accountOptional = Optional.of(account);

        //Act
        Optional<AccountDDD> result = accountRepository.getByID(accountID);

        //Assert
        assertEquals(accountOptional,result);
    }

    @DisplayName("ensure getByEmail returns optional")
    @Test
    void shouldReturnAccountOptional() {
        //Arrange
        AccountJpaRepository accountJpaRepository = mock(AccountJpaRepository.class);
        AccountDomainDataAssembler assembler = mock(AccountDomainDataAssembler.class);
        AccountRepository accountRepository = new AccountRepository(accountJpaRepository, assembler);

        AccountDDD account = mock(AccountDDD.class);
        String email = "xxx@gmail.com";
        AccountJpa accountJpa = mock(AccountJpa.class);
        Optional<AccountJpa> accountJpaOptional = Optional.of(accountJpa);

        when(accountJpaRepository.findByEmail(email)).thenReturn(accountJpaOptional);
        when(assembler.toDomain(accountJpa)).thenReturn(account);

        Optional<AccountDDD> accountOptional = Optional.of(account);

        //Act
        Optional<AccountDDD> result = accountRepository.getByEmail(email);

        //Assert
        assertEquals(accountOptional, result);
    }

    @DisplayName("ensure getByEmail returns empty")
    @Test
    void shouldReturnEmptyOptional() {
        //Arrange
        AccountJpaRepository accountJpaRepository = mock(AccountJpaRepository.class);
        AccountDomainDataAssembler assembler = mock(AccountDomainDataAssembler.class);
        AccountRepository accountRepository = new AccountRepository(accountJpaRepository,assembler);

        AccountDDD account = mock(AccountDDD.class);
        AccountID accountID = mock(AccountID.class);
        when(account.identity()).thenReturn(accountID);

        when(accountJpaRepository.findById(account.identity().getId())).thenReturn(Optional.empty());

        //Act
        Optional<AccountDDD> result = accountRepository.getByID(accountID);

        //Assert
        assertEquals(Optional.empty(),result);
    }

    @DisplayName("getting an Email with an accountID")
    @Test
    void shouldReturnEmail() {
        // Arrange
        AccountJpaRepository accountJpaRepository = mock(AccountJpaRepository.class);
        AccountDomainDataAssembler assembler = mock(AccountDomainDataAssembler.class);
        AccountRepository accountRepository = new AccountRepository(accountJpaRepository, assembler);

        AccountDDD account = mock(AccountDDD.class);
        AccountID accountID = mock(AccountID.class);
        when(account.identity()).thenReturn(accountID);

        AccountJpa accountJpa = mock(AccountJpa.class);
        Optional<AccountJpa> accountJpaOptional = Optional.of(accountJpa);

        when(accountJpaRepository.findById(account.identity().getId())).thenReturn(accountJpaOptional);
        when(assembler.toDomain(accountJpa)).thenReturn(account);

        Optional<AccountDDD> accountOptional = Optional.of(account);

        Email expectedEmail = mock(Email.class);
        when(account.getEmail()).thenReturn(expectedEmail);

        // Act
        Email result = accountRepository.getEmailWhenOutputAccountIDEqualsAccountAccountID(accountID);

        // Assert
        assertEquals(expectedEmail, result);
    }


    @DisplayName("getting an email with an accountID returns null")
    @Test
    void shouldReturnNullEmail() {
        //Arrange
        AccountJpaRepository accountJpaRepository = mock(AccountJpaRepository.class);
        AccountDomainDataAssembler assembler = mock(AccountDomainDataAssembler.class);
        AccountRepository accountRepository = new AccountRepository(accountJpaRepository,assembler);

        AccountDDD account = mock(AccountDDD.class);
        AccountID accountID = mock(AccountID.class);
        when(account.identity()).thenReturn(accountID);

        AccountJpa accountJpa = mock(AccountJpa.class);
        Optional<AccountJpa> accountJpaOptional = Optional.empty();

        //Act
        Email result = accountRepository.getEmailWhenOutputAccountIDEqualsAccountAccountID(accountID);

        //Assert
        assertNull(result);
    }

    @DisplayName("getting an AccountID with a matching email")
    @Test
    void shouldReturnAccountID() {
        // Arrange
        AccountJpaRepository accountJpaRepository = mock(AccountJpaRepository.class);
        AccountDomainDataAssembler assembler = mock(AccountDomainDataAssembler.class);
        AccountRepository accountRepository = new AccountRepository(accountJpaRepository, assembler);

        AccountDDD account = mock(AccountDDD.class);
        Email email = mock(Email.class);
        when(account.getEmail()).thenReturn(email);
        when(email.toString()).thenReturn("xxx@gmail.com");

        AccountJpa accountJpa = mock(AccountJpa.class);
        Optional<AccountJpa> accountJpaOptional = Optional.of(accountJpa);

        when(accountJpaRepository.findByEmail(email.toString())).thenReturn(accountJpaOptional);
        when(assembler.toDomain(accountJpa)).thenReturn(account);

        Optional<AccountDDD> accountOptional = Optional.of(account);

        AccountID expectedId = mock(AccountID.class);
        when(account.identity()).thenReturn(expectedId);

        // Act
        AccountID result = accountRepository.getAccountIDWhenInputEmailEqualsAccountEmail(email.toString());

        // Assert
        assertEquals(expectedId, result);
    }

    @DisplayName("getting an email with an accountID returns null")
    @Test
    void shouldReturnNullAccountID() {
        //Arrange
        AccountJpaRepository accountJpaRepository = mock(AccountJpaRepository.class);
        AccountDomainDataAssembler assembler = mock(AccountDomainDataAssembler.class);
        AccountRepository accountRepository = new AccountRepository(accountJpaRepository,assembler);

        AccountDDD account = mock(AccountDDD.class);
        Email email = mock(Email.class);
        when(account.getEmail()).thenReturn(email);

        AccountJpa accountJpa = mock(AccountJpa.class);
        Optional<AccountJpa> accountJpaOptional = Optional.empty();

        //Act
        AccountID result = accountRepository.getAccountIDWhenInputEmailEqualsAccountEmail(email.toString());

        //Assert
        assertNull(result);
    }

    @DisplayName("verify is returns true")
    @Test
    void shouldReturnTrueWhenEmailExists() {
        //Assert
        AccountJpaRepository accountJpaRepository = mock(AccountJpaRepository.class);
        AccountDomainDataAssembler assembler = mock(AccountDomainDataAssembler.class);
        AccountRepository accountRepository = new AccountRepository(accountJpaRepository,assembler);
        String email = "xxx@gmail.com";

        when(accountRepository.existsByEmail(email)).thenReturn(true);

        //Act
        boolean result = accountRepository.existsByEmail(email);

        //Assert
        assertTrue(result);
    }
}