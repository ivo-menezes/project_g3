package org.switch2022.project.mapper.REST;

import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.NewAccountDTO;
import org.switch2022.project.model.valueobject.*;

import static org.junit.jupiter.api.Assertions.*;

class AccountRestDTOMapperTest {

    @Test
    void toDomainDto() {
        AccountRestDTO restDTO = new AccountRestDTO();
        //restDTO.accountID = 1L;
        restDTO.email = "xpto@gmail.com";
        restDTO.name = "ricardo";
        restDTO.phoneNumber = "+351917830464";
        restDTO.photo = "";
        restDTO.profile = "User";

        NewAccountDTO domainDTO = new NewAccountDTO();
        //domainDTO.accountID = new AccountID(restDTO.accountID);
        domainDTO.email = new Email(restDTO.email);
        domainDTO.name = new Name(restDTO.name);
        domainDTO.phoneNumber = new PhoneNumber(restDTO.phoneNumber);
        domainDTO.photo = new Photo(restDTO.photo);
        domainDTO.profile = new ProfileName(restDTO.profile);

        AccountRestDTOMapper mapper = new AccountRestDTOMapper();

        NewAccountDTO result = mapper.toDomainDto(restDTO);

        assertInstanceOf(NewAccountDTO.class, result);
    }

    @Test
    void toRestDto() {
        NewAccountDTO domainDTO = new NewAccountDTO();
        domainDTO.accountID = new AccountID(1L);
        domainDTO.email = new Email("test@example.com");
        domainDTO.name = new Name("Ricardo");
        domainDTO.phoneNumber = new PhoneNumber("+351917830464");
        domainDTO.photo = new Photo("");
        domainDTO.profile = new ProfileName("User");

        AccountRestDTO restDTO = new AccountRestDTO();
        //restDTO.accountID = domainDTO.accountID.getId();
        restDTO.email = domainDTO.email.toString();
        restDTO.name = domainDTO.name.toString();
        restDTO.phoneNumber = domainDTO.phoneNumber.toString();
        restDTO.photo = domainDTO.photo.toString();
        restDTO.profile = domainDTO.profile.toString();

        AccountRestDTOMapper mapper = new AccountRestDTOMapper();
        AccountRestDTO result = mapper.toRestDto(domainDTO);

        assertInstanceOf(AccountRestDTO.class, result);
    }
}