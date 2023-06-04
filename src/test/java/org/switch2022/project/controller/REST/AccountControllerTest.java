package org.switch2022.project.controller.REST;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.switch2022.project.mapper.NewAccountDTO;
import org.switch2022.project.mapper.NewResourceDTO;
import org.switch2022.project.mapper.REST.AccountRestDTO;
import org.switch2022.project.mapper.REST.AccountRestDTOMapper;
import org.switch2022.project.mapper.REST.ResourceRestDTO;
import org.switch2022.project.service.AccountService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class AccountControllerTest {

    @MockBean
    AccountService accountService;

    @MockBean
    AccountRestDTOMapper mapper;

    @Autowired
    AccountController controller;

    @DisplayName("ensure that create a account correctly and HTTP status 201 - Created")
    @Test
    void shouldReturnCorrectDtoStatusCreated() {
        //Arrange
        AccountRestDTO restDTO = mock(AccountRestDTO.class);
        NewAccountDTO domainDTO = mock(NewAccountDTO.class);
        NewAccountDTO savedDomainDTO = mock(NewAccountDTO.class);
        AccountRestDTO savedRestDTO = mock(AccountRestDTO.class);

        when(mapper.toDomainDto(restDTO)).thenReturn(domainDTO);
        when(accountService.createAccount(domainDTO)).thenReturn(savedDomainDTO);
        when(mapper.toRestDto(savedDomainDTO)).thenReturn(savedRestDTO);

        //Act
        ResponseEntity<?> response = controller.createAccount(restDTO);

        //Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(savedRestDTO, response.getBody());
    }

    @DisplayName("ensure that create a resource account and HTTP status 400 - Bad Request")
    @Test
    void ensureResourceCreationFailsAndStatusBadRequest() {
        //Arrange
        AccountRestDTO restDTO = mock(AccountRestDTO.class);
        NewAccountDTO domainDTO = mock(NewAccountDTO.class);

        when(mapper.toDomainDto(restDTO)).thenReturn(domainDTO);
        when(accountService.createAccount(domainDTO)).thenThrow(RuntimeException.class);

        //Act
        ResponseEntity<?> response = controller.createAccount(restDTO);

        //Assert
        assertEquals(400, response.getStatusCodeValue());
        assertEquals(restDTO, response.getBody());
    }

}