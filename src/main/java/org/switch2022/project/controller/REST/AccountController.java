package org.switch2022.project.controller.REST;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.switch2022.project.mapper.NewAccountDTO;
import org.switch2022.project.mapper.REST.AccountRestDTO;
import org.switch2022.project.mapper.REST.AccountRestDTOMapper;
import org.switch2022.project.service.AccountService;

import java.util.List;

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

            return new ResponseEntity<>(savedAccountRestDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(restDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/accounts")
    public ResponseEntity<?> getAccounts() {
        try {
            List<NewAccountDTO> domainDtoList = accountService.getAllAccounts();
            List<AccountRestDTO> restDtoList = mapper.toRestList(domainDtoList);
            return new ResponseEntity<>(restDtoList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
