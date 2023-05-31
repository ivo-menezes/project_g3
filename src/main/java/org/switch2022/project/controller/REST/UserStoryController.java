package org.switch2022.project.controller.REST;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.switch2022.project.mapper.NewUserStoryInfoDTO;
import org.switch2022.project.mapper.REST.UserStoryRestDto;
import org.switch2022.project.mapper.REST.UserStoryRestDtoMapper;
import org.switch2022.project.service.UserStoryService;

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
    public ResponseEntity<?> createUserStory(@RequestBody UserStoryRestDto restDto) {
        try {
            NewUserStoryInfoDTO domainDto = dtoMapper.toDomainDto(restDto);
            NewUserStoryInfoDTO savedUserStoryDto = service.createUserStory(domainDto);
            UserStoryRestDto savedUserStoryRestDto = dtoMapper.toRestDto(savedUserStoryDto);
            ResponseEntity<UserStoryRestDto> response = new ResponseEntity<>(savedUserStoryRestDto, HttpStatus.CREATED);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity<UserStoryRestDto> response = new ResponseEntity<>(restDto, HttpStatus.BAD_REQUEST);
            return response;
        }
    }
}
