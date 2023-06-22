package org.switch2022.project.mapper.REST;

import org.springframework.stereotype.Component;
import org.switch2022.project.mapper.NewAccountDTO;
import org.switch2022.project.model.valueobject.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountRestDTOMapper {

    public NewAccountDTO toDomainDto(AccountRestDTO restAccountDto) {
        NewAccountDTO domainDTO = new NewAccountDTO();

        domainDTO.accountID = new AccountID(restAccountDto.accountID);
        domainDTO.email = new Email(restAccountDto.email);
        domainDTO.name = new Name(restAccountDto.name);
        domainDTO.phoneNumber = new PhoneNumber(restAccountDto.phoneNumber);
        domainDTO.photo = new Photo(restAccountDto.photo);
        domainDTO.profile = new ProfileName(restAccountDto.profile);

        return domainDTO;
    }

    public AccountRestDTO toRestDto(NewAccountDTO domainDto) {

        AccountRestDTO accountRestDTO = new AccountRestDTO();

        accountRestDTO.accountID = domainDto.accountID.getId();
        accountRestDTO.email = domainDto.email.toString();
        accountRestDTO.name = domainDto.name.toString();
        accountRestDTO.phoneNumber = domainDto.phoneNumber.toString();
        accountRestDTO.photo = domainDto.photo.toString();
        accountRestDTO.profile = domainDto.profile.toString();

        return accountRestDTO;
    }

    public List<AccountRestDTO> toRestList(List<NewAccountDTO> domainDtoList) {
        List<AccountRestDTO> list = new ArrayList<>();

        for (NewAccountDTO accountDTO : domainDtoList) {
            AccountRestDTO dto = toRestDto(accountDTO);
            list.add(dto);
        } return list;
    }
}
