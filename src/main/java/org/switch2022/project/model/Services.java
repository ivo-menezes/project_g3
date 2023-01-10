package org.switch2022.project.model;

public class Services {
    private AccountList accountList;
    private ProfileList profileList;

    public Services() {
        this.accountList = new AccountList();
        this.profileList = new ProfileList();
    }

    /**
     *
     * @param email
     * @param newProfileName
     * @return
     */
    public boolean changeProfile(String email, String newProfileName) {
        if (email == null || newProfileName == null) {
            throw new IllegalArgumentException();
        }

        // way of returning false so this is a coherent boolean method
        try {
            Account account = accountList.getAccount(email);
            Profile profile = profileList.getProfile(newProfileName);
            account.setProfile(profile);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
