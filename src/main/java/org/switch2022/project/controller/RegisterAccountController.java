package org.switch2022.project.controller;

import org.switch2022.project.model.AccountList;
import org.switch2022.project.model.Profile;
import org.switch2022.project.model.ProfileList;
import org.switch2022.project.mapper.RegisterAccountDTO;

public class RegisterAccountController {
    private ProfileList profileList;
    private AccountList accountList;

    public RegisterAccountController(ProfileList profileList, AccountList accountList) {
        this.profileList = profileList;
        this.accountList = accountList;
    }

    public boolean registerAccount(RegisterAccountDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Account info (DTO) must not be null.");
        }

        Profile defaultProfile = profileList.getProfileByName("User");
        return accountList.registerAccount(dto, defaultProfile);
    }
}
