package org.switch2022.project.controller.REST;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.mapper.NewResourceDTO;
import org.switch2022.project.mapper.REST.ResourceRestDTO;
import org.switch2022.project.mapper.REST.ResourceRestDTOMapper;
import org.switch2022.project.service.ResourceService;

import java.util.List;

@Controller
@RestController
public class ResourceController {

    private final ResourceService resourceService;
    private final ResourceRestDTOMapper mapper;

    public ResourceController(ResourceService resourceService, ResourceRestDTOMapper mapper) {
        if (resourceService == null) {
            throw new IllegalArgumentException("ResourceService must not be null");
        }

        if (mapper == null) {
            throw new IllegalArgumentException("ResourceDTOMapper must not be null");
        }

        this.resourceService = resourceService;
        this.mapper = mapper;
    }

    @PostMapping(path="/projects/{projectcode}/resources")
    public ResponseEntity<?> createResource(@RequestBody ResourceRestDTO restDTO) {
        try {
            NewResourceDTO domainDTO = mapper.toDomainDto(restDTO);
            NewResourceDTO savedResourceDTO = resourceService.createResource(domainDTO);
            ResourceRestDTO savedResourceRestDTO = mapper.toRestDto(savedResourceDTO);

            return new ResponseEntity<>(savedResourceRestDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(restDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/projects/{projectCode}/resources")
    public ResponseEntity<?> getResource() {
        try {
            List<NewResourceDTO> domainDtoList = resourceService.getAllResources();
            List<ResourceRestDTO> restDtoList = mapper.toRestDTOList(domainDtoList);
            return new ResponseEntity<>(restDtoList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
