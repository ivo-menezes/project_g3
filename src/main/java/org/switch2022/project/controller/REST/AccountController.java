package org.switch2022.project.controller.REST;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.switch2022.project.mapper.NewAccountDTO;
import org.switch2022.project.mapper.NewResourceDTO;
import org.switch2022.project.mapper.REST.AccountRestDTO;
import org.switch2022.project.mapper.REST.AccountRestDTOMapper;
import org.switch2022.project.mapper.REST.ResourceRestDTO;
import org.switch2022.project.service.AccountService;

@RestController
public class AccountController {

    private final AccountService accountService;
    private final AccountRestDTOMapper mapper;

    public AccountController(AccountService accountService, AccountRestDTOMapper mapper) {
        if (accountService == null) {
            throw new IllegalArgumentException("AccountService must not be null");
        }

        if (mapper == null) {
            throw new IllegalArgumentException("AccountRestDTOMapper must not be null");
        }
        this.accountService = accountService;
        this.mapper = mapper;
    }

    @PostMapping(path="/accounts")
    public ResponseEntity<?> createAccount(@RequestBody AccountRestDTO restDTO) {
        try {
            NewAccountDTO domainDTO = mapper.toDomainDto(restDTO);
            NewAccountDTO savedAccountDTO = accountService.createAccount(domainDTO);
            AccountRestDTO savedAccountRestDTO = mapper.toRestDto(savedAccountDTO);

            ResponseEntity<AccountRestDTO> response = new ResponseEntity<>(savedAccountRestDTO, HttpStatus.CREATED);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity<AccountRestDTO> response = new ResponseEntity<>(restDTO, HttpStatus.BAD_REQUEST);
            return response;
        }
    }
}
