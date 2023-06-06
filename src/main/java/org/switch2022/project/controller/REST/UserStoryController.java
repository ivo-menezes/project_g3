package org.switch2022.project.controller.REST;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.mapper.NewUserStoryInfoDTO;
import org.switch2022.project.mapper.REST.UserStoryRestDto;
import org.switch2022.project.mapper.REST.UserStoryRestDtoMapper;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.service.UserStoryService;

import java.util.List;

@RestController
public class UserStoryController {

    private final UserStoryService service;
    private final UserStoryRestDtoMapper dtoMapper;

    public UserStoryController(UserStoryService service, UserStoryRestDtoMapper dtoMapper) {

        if (service == null) {
            throw new IllegalArgumentException("UserStoryService must not be null");
        }

        if (dtoMapper == null) {
            throw new IllegalArgumentException("UserStoryRestDtoMapper must not be null");
        }

        this.service = service;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping("/projects/{projectCode}/productbacklog")
    public ResponseEntity<UserStoryRestDto> createUserStory(@RequestBody UserStoryRestDto restDto) {
        try {
            NewUserStoryInfoDTO domainDto = dtoMapper.toDomainDto(restDto);
            NewUserStoryInfoDTO savedUserStoryDto = service.createUserStory(domainDto);
            UserStoryRestDto savedUserStoryRestDto = dtoMapper.toRestDto(savedUserStoryDto);
            return new ResponseEntity<>(savedUserStoryRestDto, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(restDto, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/projects/{projectCode}/productbacklog")
    public ResponseEntity<List<UserStoryRestDto>> consultBacklog(@PathVariable ProjectCode projectCode) {
        try {
            List<NewUserStoryInfoDTO> domainDtoList = service.getProductBacklog(projectCode);
            List<UserStoryRestDto> restDtoList = dtoMapper.toRestDtoList(domainDtoList);
            return new ResponseEntity<>(restDtoList, HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
