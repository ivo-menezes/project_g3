package org.switch2022.project.datamodel.JPA.assemblers;

import org.springframework.stereotype.Component;
import org.switch2022.project.datamodel.JPA.AccountJpa;
import org.switch2022.project.model.account.AccountDDD;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.valueobject.*;

@Component
public class AccountDomainDataAssembler {

    public AccountJpa toData (AccountDDD account) {
        String emailJpa = account.getEmail().toString();
        String nameJpa = account.getName().toString();
        String phoneNumberJpa = account.getPhoneNumber().toString();
        String photoJpa = account.getPhoto().toString();
        String profileJpa = account.getProfile().toString();
        String accountStatusJpa = account.getAccountStatus().toString();

        return new AccountJpa(emailJpa, nameJpa, phoneNumberJpa, photoJpa, profileJpa, accountStatusJpa);
    }

    public AccountDDD toDomain (AccountJpa accountJPA) {
        AccountID accountID = new AccountID(accountJPA.identity());
        Email emailDomain = new Email(accountJPA.getEmail());
        Name nameDomain = new Name(accountJPA.getName());
        PhoneNumber phoneNumberDomain = new PhoneNumber(accountJPA.getPhoneNumber());
        Photo photoDomain = new Photo(accountJPA.getPhoto());
        Profile profileDomain = new Profile(accountJPA.getProfile());
        AccountStatus accountStatusDomain = AccountStatus.valueOf(accountJPA.getAccountStatus());

        return new AccountDDD(accountID, emailDomain, nameDomain, phoneNumberDomain, photoDomain, profileDomain,
                accountStatusDomain);
    }
}