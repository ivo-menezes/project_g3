package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.NewProjectDTO;
import org.switch2022.project.mapper.NewProjectDTOMapper;
import org.switch2022.project.mapper.ProjectDTOForListDDD;
import org.switch2022.project.mapper.ProjectMapperDDD;
import org.switch2022.project.model.project.IProjectFactory;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.service.irepositories.IBusinessSectorRepository;
import org.switch2022.project.service.irepositories.ICustomerRepository;
import org.switch2022.project.service.irepositories.IProjectRepository;
import org.switch2022.project.service.irepositories.ITypologyRepository;

import java.util.List;

@Service
public class ProjectService {

    private ICustomerRepository customerRepository;
    private IBusinessSectorRepository businessSectorRepository;
    private ITypologyRepository typologyRepository;
    private IProjectFactory projectFactory;
    private IProjectRepository projectNewRepository;

    private NewProjectDTOMapper newProjectDTOMapper;
    private Repository<ProjectCode, ProjectDDD> projectRepository;
    private ProjectMapperDDD projectMapperDDD;

    public ProjectService(ICustomerRepository customerRepository, IBusinessSectorRepository businessSectorRepository, ITypologyRepository typologyRepository, IProjectFactory projectFactory, IProjectRepository projectNewRepository, NewProjectDTOMapper newProjectDTOMapper, Repository<ProjectCode, ProjectDDD> projectRepository, ProjectMapperDDD projectMapperDDD) {
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
        if (projectNewRepository == null) {
            throw new IllegalArgumentException("Project New Repository must not be null.");
        }
        if (newProjectDTOMapper == null) {
            throw new IllegalArgumentException("New Project DTO Mapper must not be null.");
        }
        if (projectRepository == null) {
            throw new IllegalArgumentException("Project Repository must not be null.");
        }
        if (projectMapperDDD == null) {
            throw new IllegalArgumentException("Project Mapper must not be null.");
        }

        this.customerRepository = customerRepository;
        this.businessSectorRepository = businessSectorRepository;
        this.typologyRepository = typologyRepository;
        this.projectFactory = projectFactory;
        this.projectNewRepository = projectNewRepository;
        this.newProjectDTOMapper = newProjectDTOMapper;
        this.projectRepository = projectRepository;
        this.projectMapperDDD = projectMapperDDD;
    }


    public NewProjectDTO createProject(NewProjectDTO projectDto) {

        ProjectDDD projectDDD = projectFactory.createProject(projectDto);

        ProjectDDD savedProject = projectNewRepository.save(projectDDD);
        NewProjectDTO dtoOut = newProjectDTOMapper.toDto(savedProject);

        return dtoOut;
    }

    @Autowired
    public void setProjectMapper(ProjectMapperDDD projectMapperDDD) {
        this.projectMapperDDD = projectMapperDDD;
    }


    public List<ProjectDTOForListDDD> listProjects() {

        Iterable<ProjectDDD> projectCollection = projectRepository.findAll();

        return projectMapperDDD.toDTOList(projectCollection);
    }
}
