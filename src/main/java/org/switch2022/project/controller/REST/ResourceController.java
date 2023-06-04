package org.switch2022.project.controller.REST;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.mapper.NewResourceDTO;
import org.switch2022.project.mapper.REST.ResourceRestDTO;
import org.switch2022.project.mapper.REST.ResourceRestDTOMapper;
import org.switch2022.project.model.valueobject.ProjectCode;
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

            ResponseEntity<ResourceRestDTO> response = new ResponseEntity<>(savedResourceRestDTO, HttpStatus.CREATED);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity<ResourceRestDTO> response = new ResponseEntity<>(restDTO, HttpStatus.BAD_REQUEST);
            return response;
        }
    }

    @GetMapping("/projects/{projectCode}/resources")
    public ResponseEntity<?> getResource(@PathVariable ProjectCode projectCode) {
        try {
            List<NewResourceDTO> domainDtoList = resourceService.getAll();
            List<ResourceRestDTO> restDtoList = mapper.toRestDTOList(domainDtoList);
            ResponseEntity<List<ResourceRestDTO>> response = new ResponseEntity<>(restDtoList, HttpStatus.OK);
            return response;

        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity<Object> response = new ResponseEntity<>( HttpStatus.NOT_FOUND);
            return response;
        }
    }
}
