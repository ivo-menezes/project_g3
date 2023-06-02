package org.switch2022.project.repository;

import org.springframework.stereotype.Repository;
import org.switch2022.project.datamodel.JPA.ResourceJPA;
import org.switch2022.project.datamodel.JPA.assemblers.ResourceDomainAssemblerData;
import org.switch2022.project.model.resource.ResourceDDD;
import org.switch2022.project.repository.JPA.ResourceRepositoryJPA;
import org.switch2022.project.service.irepositories.IResourceRepository;

@Repository
public class ResourceRepository implements IResourceRepository {

    private ResourceRepositoryJPA resourceRepositoryJPA;

    private ResourceDomainAssemblerData resourceDomainAssemblerData;

    public ResourceRepository(ResourceRepositoryJPA resourceRepositoryJPA, ResourceDomainAssemblerData resourceDomainAssemblerData) {
        this.resourceRepositoryJPA = resourceRepositoryJPA;
        this.resourceDomainAssemblerData = resourceDomainAssemblerData;
    }

    public ResourceDDD save(ResourceDDD resourceDDD) {

        ResourceJPA resourceJPA = resourceDomainAssemblerData.toData(resourceDDD);
        ResourceJPA savedResourceJPA = resourceRepositoryJPA.save(resourceJPA);
        return resourceDomainAssemblerData.toDomain(savedResourceJPA);
    }

    public boolean isRoleOccupied(ResourceDDD resourceDDD) {

        String roleInUse = resourceDDD.getRole().toString();
        return resourceRepositoryJPA.existsByRole(roleInUse);
    }
}