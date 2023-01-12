package org.switch2022.project.controller;

import org.switch2022.project.model.Account;
import org.switch2022.project.model.AccountList;
import org.switch2022.project.model.Profile;
import org.switch2022.project.model.ProfileList;

import java.util.List;


public class ChangeProfileController {
    private static AccountList accountList;
    private static ProfileList profileList ;

    public ChangeProfileController(AccountList accountList, ProfileList profileList){
        ChangeProfileController.accountList = accountList;
        ChangeProfileController.profileList = profileList;
    }

    public static void changeProfile(String email, String newProfileName){
        Account account = accountList.getAccount(email);
        Profile profile = profileList.getProfile(newProfileName);
        account.setProfile(profile);

    }
}
