package org.switch2022.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.AccountList;
import org.switch2022.project.model.Profile;
import org.switch2022.project.model.ProfileList;
import org.switch2022.project.model.RegisterAccountDTO;

import static org.junit.jupiter.api.Assertions.*;

class RegisterAccountControllerTest {

    @Test
    @DisplayName("ensure registering an account returns true")
    void registerAccountSuccess() {
        Profile profile = new Profile("User");
        ProfileList profileList = new ProfileList();
        profileList.add(profile);

        AccountList accountList = new AccountList();

        RegisterAccountController controller = new RegisterAccountController(profileList, accountList);

        RegisterAccountDTO dto = new RegisterAccountDTO();
        dto.name = "John Doe";
        dto.phone = "760100200";
        dto.email = "jdoe@example.com";
        dto.photo = "😁";

        boolean result = controller.registerAccount(dto);
        assertTrue(result);
    }

    @Test
    @DisplayName("ensure registering an account with null DTO throws exception")
    void registerAccountNullDTOThrowsException() {
        Profile profile = new Profile("User");
        ProfileList profileList = new ProfileList();
        profileList.add(profile);

        AccountList accountList = new AccountList();

        RegisterAccountController controller = new RegisterAccountController(profileList, accountList);

        RegisterAccountDTO dto = null;
        String expectedMessage = "Account info (DTO) must not be null.";

        IllegalArgumentException result = assertThrows(IllegalArgumentException.class,
                () -> controller.registerAccount(dto));
        String resultMessage = result.getMessage();
        assertEquals(expectedMessage, resultMessage);

    }
}