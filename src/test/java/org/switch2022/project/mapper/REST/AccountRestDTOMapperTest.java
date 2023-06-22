package org.switch2022.project.mapper.REST;

import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.NewAccountDTO;
import org.switch2022.project.model.valueobject.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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

    @Test
    void toRestList() {
        List<NewAccountDTO> domainDtoList = new ArrayList<>();
        NewAccountDTO accountDTO1 = mock(NewAccountDTO.class);
        NewAccountDTO accountDTO2 = mock(NewAccountDTO.class);
        domainDtoList.add(accountDTO1);
        domainDtoList.add(accountDTO2);

        AccountID accountID = mock(AccountID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);
        ProfileName profileName = mock(ProfileName.class);

        accountDTO1.accountID = accountID;
        accountDTO1.email = email;
        accountDTO1.name = name;
        accountDTO1.phoneNumber = phoneNumber;
        accountDTO1.photo = photo;
        accountDTO1.profile = profileName;

        accountDTO2.accountID = accountID;
        accountDTO2.email = email;
        accountDTO2.name = name;
        accountDTO2.phoneNumber = phoneNumber;
        accountDTO2.photo = photo;
        accountDTO2.profile = profileName;

        List<AccountRestDTO> list = new ArrayList<>();
        AccountRestDTO dto1 = mock(AccountRestDTO.class);
        AccountRestDTO dto2 = mock(AccountRestDTO.class);

        dto1.accountID = accountDTO1.accountID.getId();
        dto1.email = accountDTO1.email.toString();
        dto1.name = accountDTO1.name.toString();
        dto1.phoneNumber = accountDTO1.phoneNumber.toString();
        dto1.photo = accountDTO1.photo.toString();
        dto1.profile = accountDTO1.profile.toString();

        dto2.accountID = accountDTO2.accountID.getId();
        dto2.email = accountDTO2.email.toString();
        dto2.name = accountDTO2.name.toString();
        dto2.phoneNumber = accountDTO2.phoneNumber.toString();
        dto2.photo = accountDTO2.photo.toString();
        dto2.profile = accountDTO2.profile.toString();

        list.add(dto1);
        list.add(dto2);

        AccountRestDTOMapper mapper = new AccountRestDTOMapper();

        //Act
        List<AccountRestDTO> result = mapper.toRestList(domainDtoList);

        //Assert
        assertInstanceOf(AccountRestDTO.class, list.get(0));
        assertEquals(2, list.size());
        assertInstanceOf(AccountRestDTO.class, result.get(0));
        assertEquals(2, result.size());
    }
}