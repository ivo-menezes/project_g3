package org.switch2022.project.service;

import org.springframework.stereotype.Service;
import org.switch2022.project.mapper.NewResourceDTO;
import org.switch2022.project.mapper.NewResourceDTOMapper;
import org.switch2022.project.model.account.AccountDDD;
import org.switch2022.project.model.resource.IResourceFactory;
import org.switch2022.project.model.resource.ResourceDDD;
import org.switch2022.project.model.valueobject.*;
import org.switch2022.project.service.irepositories.IAccountRepository;
import org.switch2022.project.service.irepositories.IProjectRepository;
import org.switch2022.project.service.irepositories.IResourceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {

    private final IResourceFactory resourceFactory;
    private final IResourceRepository resourceRepository;
    private final IAccountRepository accountRepository;
    private final IProjectRepository projectRepository;
    private final NewResourceDTOMapper resourceDTOMapper;


    /**
     * Constructor for ResourceService.
     * @param resourceFactory injected parameter
     * @param resourceRepository injected parameter
     * @param accountRepository injected parameter
     * @param projectRepository injected parameter
     * @param resourceDTOMapper injected parameter
     */

    public ResourceService(IResourceFactory resourceFactory, IResourceRepository resourceRepository,
                           IAccountRepository accountRepository, IProjectRepository projectRepository,
                           NewResourceDTOMapper resourceDTOMapper) {
        if (resourceFactory == null) {
            throw new IllegalArgumentException("ResourceFactory must not be null.");
        }
        if (resourceRepository == null) {
            throw new IllegalArgumentException("ResourceRepository must not be null.");
        }
        if (accountRepository == null) {
            throw new IllegalArgumentException("AccountRepository must not be null.");
        }
        if (projectRepository == null) {
            throw new IllegalArgumentException("ProjectRepository must not be null.");
        }
        this.resourceFactory = resourceFactory;
        this.resourceRepository = resourceRepository;
        this.accountRepository = accountRepository;
        this.projectRepository = projectRepository;
        this.resourceDTOMapper = resourceDTOMapper;
    }

    /**
     * Creates resource from data provided by the DTO
     * @param newResourceDTO containing the necessary data
     * @return the updated NewResourceDTO with the saved resource id
     */

    public NewResourceDTO createResource (NewResourceDTO newResourceDTO) {
        AccountID accountID = newResourceDTO.accountID;
        String projectCode = newResourceDTO.projectCode.toString();

        //get account with respective email, if it really exists in the accountRepository
        Optional<AccountDDD> accountOptional = accountRepository.getByID(accountID);

        if (accountOptional.isEmpty()) {
            throw new RuntimeException("Account with given email doesn't exist");
        }
        AccountDDD account = accountOptional.get();

        //confirm if the account has the profile user
        ProfileName profile = account.getProfile();
        boolean accountHasUserProfile = account.isUser(profile);

        if(!accountHasUserProfile) {
            throw new IllegalArgumentException("This account doesn't have an user profile");
        }

        //confirm if the project with the given project code exists
        boolean projectExists = projectRepository.existsByProjectCode(projectCode);

        if(!projectExists) {
            throw new IllegalArgumentException("This project doesn't exist");
        }

        // factory is creating a resource
        ResourceDDD resource = resourceFactory.createResource(newResourceDTO);

        // save the created resource
        ResourceDDD savedResource = this.resourceRepository.save(resource);

        // transforming the savedResource to a dto
        NewResourceDTO resourceDTO = resourceDTOMapper.toDTO(savedResource);

        //updating the resourceID
        resourceDTO.resourceID = savedResource.identity();

        return resourceDTO;
    }

    public List<NewResourceDTO> getAllResources() {
        List<NewResourceDTO> resources = new ArrayList<>();
        List<ResourceDDD> resourceDDD = resourceRepository.getAll();

        for (ResourceDDD resource : resourceDDD) {

            NewResourceDTO resourceDTO = new NewResourceDTO();
            resourceDTO.resourceID = resource.identity();
            resourceDTO.accountID = resource.getAccountID();
            resourceDTO.costPerHour = resource.getCostPerHour();
            resourceDTO.role = resource.getRole();
            resourceDTO.percentageOfAllocation = resource.getPercentageOfAllocation();
            resourceDTO.projectCode = resource.getProjectCode();
            resourceDTO.timePeriod = resource.getTimePeriod();

            resources.add(resourceDTO);
        }
        return resources;
    }
}