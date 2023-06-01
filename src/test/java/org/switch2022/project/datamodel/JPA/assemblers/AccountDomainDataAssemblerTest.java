package org.switch2022.project.datamodel.JPA.assemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.datamodel.JPA.AccountJpa;
import org.switch2022.project.model.account.AccountDDD;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountDomainDataAssemblerTest {

    @DisplayName("ensure toData method returns a correct AccountJPA with all parameters")
    @Test
    void shouldReturnCorrectAccountJpaWithAllParameters() {
        //Arrange
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        Profile profileDouble = mock(Profile.class);
        AccountStatus accountStatusDouble = mock(AccountStatus.class);

        AccountDDD accountDouble = mock(AccountDDD.class);

        when(accountDouble.getEmail()).thenReturn(emailDouble);
        when(accountDouble.getName()).thenReturn(nameDouble);
        when(accountDouble.getPhoneNumber()).thenReturn(phoneNumberDouble);
        when(accountDouble.getPhoto()).thenReturn(photoDouble);
        when(accountDouble.getProfile()).thenReturn(profileDouble);
        when(accountDouble.getAccountStatus()).thenReturn(accountStatusDouble);

        AccountDomainDataAssembler assembler = new AccountDomainDataAssembler();

        //Act
        AccountJpa resultingAccountJPA = assembler.toData(accountDouble);

        //Assert
        assertInstanceOf(AccountJpa.class, resultingAccountJPA);
    }

    @DisplayName("ensure toDomain method returns a correct Account with all parameters")
    @Test
    void shouldReturnCorrectAccountWithAllParameters() {
        //Arrange
        AccountJpa accountJpa = mock(AccountJpa.class);

        when(accountJpa.getEmail()).thenReturn("test@gmail.com");
        when(accountJpa.getName()).thenReturn("Ricardo");
        when(accountJpa.getPhoneNumber()).thenReturn("+351912348573");
        when(accountJpa.getPhoto()).thenReturn(".");
        when(accountJpa.getProfile()).thenReturn("User");
        when(accountJpa.getAccountStatus()).thenReturn("Active");

        AccountDomainDataAssembler assembler = new AccountDomainDataAssembler();

        // Act
        AccountDDD resultingAccount = assembler.toDomain(accountJpa);

        // Assert
        assertInstanceOf(AccountDDD.class, resultingAccount);
    }
}