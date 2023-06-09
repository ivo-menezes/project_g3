package org.switch2022.project.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.switch2022.project.mapper.NewAccountDTO;
import org.switch2022.project.mapper.NewAccountDTOMapper;
import org.switch2022.project.model.account.AccountDDD;
import org.switch2022.project.model.account.IAccountFactory;
import org.switch2022.project.model.valueobject.*;
import org.switch2022.project.service.irepositories.IAccountRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class AccountServiceTest {

    @MockBean
    IAccountFactory accountFactory;
    @MockBean
    IAccountRepository accountRepository;
    @MockBean
    NewAccountDTOMapper accountDTOMapper;
    @Autowired
    AccountService accountService;

    @Test
    @DisplayName("Ensure exception is returned when accountFactory is null")
    void ensureExceptionWhenAccountFactoryNull() {
        // arrange
        String expectedMessage = "AccountFactory must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () ->
                new AccountService(null, accountRepository, accountDTOMapper));

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Ensure exception is returned when accountRepository is null")
    void ensureExceptionWhenAccountRepositoryNull() {
        // arrange
        String expectedMessage = "AccountRepository must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () ->
                new AccountService(accountFactory, null, accountDTOMapper));

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that creating a account succeeds")
    @Test
    void createAccountSucceeds() {
        // Arrange
        NewAccountDTO newAccountDTO = mock(NewAccountDTO.class);

        AccountID accountID = mock(AccountID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);
        ProfileName profile = mock(ProfileName.class);
        AccountDDD account = mock(AccountDDD.class);
        AccountDDD savedAccount = mock(AccountDDD.class);

        newAccountDTO.accountID = accountID;
        newAccountDTO.email = email;
        newAccountDTO.name = name;
        newAccountDTO.phoneNumber = phoneNumber;
        newAccountDTO.photo = photo;
        newAccountDTO.profile = profile;

        when(accountRepository.existsByEmail(email.toString())).thenReturn(false);
        when(accountFactory.createAccount(accountID, email, name, phoneNumber, photo, profile)).
                thenReturn(account);
        when(accountRepository.save(account)).thenReturn(savedAccount);

        // Act
        NewAccountDTO result = accountService.createAccount(newAccountDTO);

        // Assert
        assertEquals(newAccountDTO, result);
    }

    @DisplayName("assert that creating a account fails when email exists")
    @Test
    void createAccountFailsWhenEmailAlreadyExists() {
        // Arrange
        NewAccountDTO newAccountDTO = mock(NewAccountDTO.class);

        AccountID accountID = mock(AccountID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);
        ProfileName profile = mock(ProfileName.class);

        newAccountDTO.accountID = accountID;
        newAccountDTO.email = email;
        newAccountDTO.name = name;
        newAccountDTO.phoneNumber = phoneNumber;
        newAccountDTO.photo = photo;
        newAccountDTO.profile = profile;

        when(accountRepository.existsByEmail(email.toString())).thenReturn(true);

        String expected = "Account with given email already exists";

        // Act
        RuntimeException result = assertThrows(RuntimeException.class, () ->
            accountService.createAccount(newAccountDTO));
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expected, resultMessage);
    }

}