package org.switch2022.project.service;

import org.springframework.stereotype.Service;
import org.switch2022.project.mapper.NewProjectDTO;
import org.switch2022.project.mapper.NewProjectDTOMapper;
import org.switch2022.project.model.project.IProjectFactory;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.valueobject.BusinessSectorID;
import org.switch2022.project.model.valueobject.CustomerID;
import org.switch2022.project.model.valueobject.TypologyID;
import org.switch2022.project.service.irepositories.IBusinessSectorRepository;
import org.switch2022.project.service.irepositories.ICustomerRepository;
import org.switch2022.project.service.irepositories.IProjectRepository;
import org.switch2022.project.service.irepositories.ITypologyRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    private final ICustomerRepository customerRepository;
    private final IBusinessSectorRepository businessSectorRepository;
    private final ITypologyRepository typologyRepository;
    private final IProjectFactory projectFactory;
    private final IProjectRepository projectRepository;
    private final NewProjectDTOMapper newProjectDTOMapper;


    public ProjectService(ICustomerRepository customerRepository,
                          IBusinessSectorRepository businessSectorRepository,
                          ITypologyRepository typologyRepository,
                          IProjectFactory projectFactory,
                          IProjectRepository projectRepository,
                          NewProjectDTOMapper newProjectDTOMapper) {
        if (customerRepository == null) {
            throw new IllegalArgumentException("Customer Repository must not be null.");
        }
        if (businessSectorRepository == null) {
            throw new IllegalArgumentException("Business Sector Repository must not be null.");
        }
        if (typologyRepository == null) {
            throw new IllegalArgumentException("Typology Repository must not be null.");
        }
        if (projectFactory == null) {
            throw new IllegalArgumentException("Project Factory must not be null.");
        }
        if (projectRepository == null) {
            throw new IllegalArgumentException("Project New Repository must not be null.");
        }
        if (newProjectDTOMapper == null) {
            throw new IllegalArgumentException("New Project DTO Mapper must not be null.");
        }

        this.customerRepository = customerRepository;
        this.businessSectorRepository = businessSectorRepository;
        this.typologyRepository = typologyRepository;
        this.projectFactory = projectFactory;
        this.projectRepository = projectRepository;
        this.newProjectDTOMapper = newProjectDTOMapper;
    }


    public NewProjectDTO createProject(NewProjectDTO projectDto) {

        CustomerID customerID = projectDto.customerID;
        if (!customerRepository.containsID(customerID)) {
            throw new IllegalArgumentException("There is no Customer with that ID.");
        }

        BusinessSectorID businessSectorID = projectDto.businessSectorID;
        if (!businessSectorRepository.containsID(businessSectorID)) {
            throw new IllegalArgumentException("There is no Business sector with that ID.");
        }

        TypologyID typologyID = projectDto.typologyID;
        if (!typologyRepository.containsID(typologyID)) {
            throw new IllegalArgumentException("There is no Typology with that ID.");
        }

        ProjectDDD projectDDD = projectFactory.createProject(projectDto);

        ProjectDDD savedProject = projectRepository.save(projectDDD);

        return newProjectDTOMapper.toDto(savedProject);
    }

    public List<NewProjectDTO> getAllProjects() {

        List<ProjectDDD> projectList = projectRepository.getAllProjects();
        List<NewProjectDTO> projectDtoList = new ArrayList<>();

        for (ProjectDDD project : projectList) {
            projectDtoList.add(newProjectDTOMapper.toDto(project));
        }
        return projectDtoList;
    }
}

