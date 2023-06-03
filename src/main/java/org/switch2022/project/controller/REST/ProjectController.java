package org.switch2022.project.controller.REST;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.switch2022.project.mapper.NewProjectDTO;
import org.switch2022.project.mapper.REST.ProjectRestDto;
import org.switch2022.project.mapper.REST.ProjectRestDtoMapper;
import org.switch2022.project.service.ProjectService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
//@RequestMapping(path = "/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectRestDtoMapper dtoMapper;


    public ProjectController(ProjectService projectService, ProjectRestDtoMapper dtoMapper) {

        if (projectService == null) {
            throw new IllegalArgumentException("Project Service must not be null");
        }

        if (dtoMapper == null) {
            throw new IllegalArgumentException("Project Rest Dto Mapper must not be null");
        }
        this.projectService = projectService;
        this.dtoMapper = dtoMapper;
    }


    @PostMapping("/projects")
    public ResponseEntity<?> createProject(@RequestBody ProjectRestDto restDto) {
        try {
            NewProjectDTO domainDto = dtoMapper.toDomainDto(restDto);
            NewProjectDTO savedProjectDto = projectService.createProject(domainDto);
            ProjectRestDto savedProjectRestDto = dtoMapper.toRestDto(savedProjectDto);
            ResponseEntity<ProjectRestDto> response = new ResponseEntity<>(savedProjectRestDto, HttpStatus.CREATED);
            return response;

        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity<ProjectRestDto> response = new ResponseEntity<>(restDto, HttpStatus.BAD_REQUEST);
            return response;
        }
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectRestDto>> getAllProjects() {
        try {
            List<NewProjectDTO> projectDtoList = projectService.getAllProjects();
            List<ProjectRestDto> projectRestDtoList = new ArrayList<>();
            for (NewProjectDTO newProjectDTO : projectDtoList) {
                projectRestDtoList.add(dtoMapper.toRestDto(newProjectDTO));
            }
            return new ResponseEntity<>(projectRestDtoList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

