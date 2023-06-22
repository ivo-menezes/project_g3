package org.switch2022.project.controller.REST;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.mapper.NewResourceDTO;
import org.switch2022.project.mapper.REST.ResourceRestDTO;
import org.switch2022.project.mapper.REST.ResourceRestDTOMapper;
import org.switch2022.project.mapper.ResourceDTOOutput;
import org.switch2022.project.model.valueobject.AccountID;
import org.switch2022.project.model.valueobject.Email;
import org.switch2022.project.service.ResourceService;
import org.switch2022.project.service.irepositories.IAccountRepository;

import java.util.List;

@Controller
@RestController
public class ResourceController {

    private final ResourceService resourceService;
    private final ResourceRestDTOMapper mapper;
    private final IAccountRepository accountRepository;

    public ResourceController(ResourceService resourceService, ResourceRestDTOMapper mapper, IAccountRepository
                              accountRepository) {
        if (resourceService == null) {
            throw new IllegalArgumentException("ResourceService must not be null");
        }

        if (mapper == null) {
            throw new IllegalArgumentException("ResourceDTOMapper must not be null");
        }
        if (accountRepository == null) {
            throw new IllegalArgumentException("AccountRepository must not be null");
        }
        this.resourceService = resourceService;
        this.mapper = mapper;
        this.accountRepository = accountRepository;
    }

    @PostMapping(path="/projects/{projectcode}/resources")
    public ResponseEntity<?> createResource(@RequestBody ResourceRestDTO restDTO) {
        try {
            AccountID accountID = accountRepository.getAccountIDWhenInputEmailEqualsAccountEmail(restDTO.email);
            NewResourceDTO domainDTO = mapper.toDomainDto(restDTO, accountID);
            NewResourceDTO savedResourceDTO = resourceService.createResource(domainDTO);
            Email email = accountRepository.getEmailWhenOutputAccountIDEqualsAccountAccountID(savedResourceDTO.accountID);
            ResourceRestDTO savedResourceRestDTO = mapper.toRestDto(savedResourceDTO, email);

            return new ResponseEntity<>(savedResourceRestDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(restDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/projects/{projectCode}/resources")
    public ResponseEntity<?> getResource() {
        try {
            List<ResourceDTOOutput> domainDtoList = resourceService.getAllResources();
            List<ResourceRestDTO> restDtoList = mapper.toRestDTOList(domainDtoList);
            return new ResponseEntity<>(restDtoList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
