package org.switch2022.project.repository;

import org.springframework.stereotype.Repository;
import org.switch2022.project.datamodel.JPA.ProjectJpa;
import org.switch2022.project.datamodel.JPA.assemblers.ProjectDomainDataAssembler;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.repository.JPA.ProjectJpaRepository;
import org.switch2022.project.service.irepositories.IProjectRepository;

@Repository
public class ProjectRepositoryForJpa implements IProjectRepository {


    private final ProjectJpaRepository projectJpaRepository;

    private final ProjectDomainDataAssembler projectDomainDataAssembler;

    public ProjectRepositoryForJpa(ProjectJpaRepository projectJpaRepository, ProjectDomainDataAssembler projectDomainDataAssembler) {
        this.projectJpaRepository = projectJpaRepository;
        this.projectDomainDataAssembler = projectDomainDataAssembler;
    }


    public ProjectDDD save(ProjectDDD project) {

        ProjectCode projectCode = project.identity();

        if (existsByProjectCode(projectCode.toString())) {
            throw new IllegalArgumentException("A project with that code already exists");
        }

        ProjectJpa projectJpa = projectDomainDataAssembler.toData(project);
        ProjectJpa savedProjectJpa = projectJpaRepository.save(projectJpa);
        ProjectDDD savedProject = projectDomainDataAssembler.toDomain(savedProjectJpa);

        return savedProject;
    }

    public boolean existsByProjectCode(String projectCode) {
        return projectJpaRepository.existsById(projectCode);
    }

}
