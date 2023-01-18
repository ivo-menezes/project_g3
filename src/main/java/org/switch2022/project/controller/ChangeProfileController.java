package org.switch2022.project.controller;

import org.switch2022.project.model.Account;
import org.switch2022.project.model.AccountList;
import org.switch2022.project.model.Profile;
import org.switch2022.project.model.ProfileList;


public class ChangeProfileController {
    private AccountList accountList;
    private ProfileList profileList ;

    /***
     *
     */
    public ChangeProfileController(AccountList accountList, ProfileList profileList){
        this.accountList = accountList;
        this.profileList = profileList;
    }

    /***
     * Fetches the account through the email, the new profile and changes the Profile of the wanted account.
     */
    public boolean changeProfile(String email, String newProfileName){
        if(email == null || newProfileName == null)
            throw new IllegalArgumentException("Input values cannot be null.");
        Account account = accountList.getAccount(email);

        Profile profile = profileList.getProfileByName(newProfileName);

        account.setProfile(profile);
        return true;
    }
}
