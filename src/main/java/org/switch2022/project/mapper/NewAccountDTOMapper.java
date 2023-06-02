package org.switch2022.project.mapper;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.account.AccountDDD;

@Component
public class NewAccountDTOMapper {

    public NewAccountDTO toDTO (AccountDDD account) {
        NewAccountDTO accountDTO = new NewAccountDTO();

        accountDTO.accountID = account.identity();
        accountDTO.email = account.getEmail();
        accountDTO.name = account.getName();
        accountDTO.phoneNumber = account.getPhoneNumber();
        accountDTO.photo = account.getPhoto();
        accountDTO.profile = account.getProfile();

        return accountDTO;
    }
}