package org.switch2022.project.model.account;

import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.valueobject.*;

public interface IAccountFactory {

    AccountDDD createAccount (AccountID accountID, Email email, Name name, PhoneNumber phoneNumber, Photo photo,
                              Profile profile);

    AccountDDD createAccount (AccountID accountID, Email email, Name name, PhoneNumber phoneNumber, Profile profile);
}
