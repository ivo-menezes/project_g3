package org.switch2022.project.repository;

import org.switch2022.project.datamodel.JPA.SprintJPA;
import org.switch2022.project.datamodel.JPA.SprintJpaID;
import org.switch2022.project.datamodel.JPA.UserStoryInSprintIDJpa;
import org.switch2022.project.datamodel.JPA.UserStoryInSprintJPA;
import org.switch2022.project.datamodel.JPA.assemblers.SprintAssemblerData;
import org.switch2022.project.datamodel.JPA.assemblers.UserStoryInSprintDataAssembler;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.sprint.UserStoryInSprint;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.UserStoryInSprintID;
import org.switch2022.project.repository.JPA.SprintJPARepository;
import org.switch2022.project.repository.JPA.UserStoryInSprintJpaRepository;
import org.switch2022.project.service.irepositories.ISprintRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class SprintRepository implements ISprintRepository {

    /***
     * Injection of the SprintJPARepository in the Sprint Repository, for persistence
     * of created data.
     */
    private final SprintJPARepository sprintJpaRepository;
    /***
     * Injection of the SprintAssemblerData in the Sprint Repository, for
     * conversion from domain to jpa object.
     */
    private final SprintAssemblerData sprintAssemblerData;

    public SprintRepository(SprintJPARepository sprintJpaRepository,
                            SprintAssemblerData sprintAssemblerData,
                            UserStoryInSprintDataAssembler userStoryInSprintDataAssembler,
                            UserStoryInSprintJpaRepository userStoryInSprintJpaRepository
    ){
        this.sprintJpaRepository = sprintJpaRepository;
        this.sprintAssemblerData = sprintAssemblerData;
        this.userStoryInSprintDataAssembler = userStoryInSprintDataAssembler;
        this.userStoryInSprintJpaRepository = userStoryInSprintJpaRepository;
    }

    private final UserStoryInSprintDataAssembler userStoryInSprintDataAssembler;
    private final UserStoryInSprintJpaRepository userStoryInSprintJpaRepository;

    /***
     * This will check whether the Repository already has a sprint with the id we pass
     * into this method.
     * @param id As SprintID object
     * @return boolean value of whether it contains the ID or not
     */
    @Override
    public boolean containsID(SprintID id) {
        SprintJpaID jpaID = sprintAssemblerData.convertToSprintJpaID(id);
        return sprintJpaRepository.existsById(jpaID);
    }

    /***
     * This method will get the identity of the sprint object and save it in the
     * repository, will throw exception if the ID already exists
     * @param sprint Object to save into the Repository
     * @return the saved SprintDDD
     */

    @Override
    public SprintDDD save(SprintDDD sprint) {
        SprintID sprintID = sprint.identity();

        if(containsID(sprintID)){
            throw new IllegalArgumentException("Sprint already exists with this ID");
        }else{
            SprintJPA sprintJpa = sprintAssemblerData.toData(sprint);
            SprintJPA savedSprintJPA = sprintJpaRepository.save(sprintJpa);
            return sprintAssemblerData.toDomain(savedSprintJPA);
        }
    }
    /***
     * This method returns the collection of values of the Repository,
     * by creating an iterable list of Sprint objects
     * @return iterable collection of Sprint values in the repository
     */
    @Override
    public Iterable<SprintDDD> findAll() {
        Iterable<SprintJPA> allSprintsJPA = sprintJpaRepository.findAll();

        List<SprintDDD> allSprints = new ArrayList<>();
        for(SprintJPA sprintJPA : allSprintsJPA){
            allSprints.add(sprintAssemblerData.toDomain(sprintJPA));
        }
        return allSprints;
    }

    /***
     * This method will return a sprint if there is one in the repository
     * with the SprintID passed by the user. Otherwise, it will return optional
     * @param id of the SprintID Object Type
     * @return optional sprint object or empty optional object
     */
    @Override
    public Optional<SprintDDD> getByID(SprintID id) {
        SprintJpaID jpaID = sprintAssemblerData.convertToSprintJpaID(id);
        Optional<SprintJPA> sprintJPAOptional = sprintJpaRepository.findById(jpaID);

        if(sprintJPAOptional.isEmpty()) {
            return Optional.empty();
        }else {
            return Optional.of(sprintAssemblerData.toDomain(sprintJPAOptional.get()));
        }
    }
    /**
     * Used to clear the repository
     */
    @Override
    public void clearRepository() {
        sprintJpaRepository.deleteAll();
    }

    /** This method should return a sprint list with the project Code provided.
     * @param projectCode of the project we want to list
     * @return list of SprintDDD objects
     */
    @Override
    public List<SprintDDD> findByProjectCode(ProjectCode projectCode) {
        String code = projectCode.toString();

        Iterable<SprintJPA> listJPA = sprintJpaRepository.findAllByProjectCode(code);

        List<SprintDDD> allSprints = new ArrayList<>();
        for(SprintJPA sprintJPA : listJPA){
            allSprints.add(sprintAssemblerData.toDomain(sprintJPA));
        }
        return allSprints;
    }

    /**
     * Method responsible for replacing a sprint with new changes in the repository.
     * @param sprint
     * @return sprint object
     */
    @Override
    public SprintDDD replace(SprintDDD sprint) {
        SprintJPA sprintJPA = sprintAssemblerData.toData(sprint);
        SprintJPA savedSprintJpa = sprintJpaRepository.save(sprintJPA);
        SprintDDD savedSprint = sprintAssemblerData.toDomain(savedSprintJpa);

        return savedSprint;
    }

    /**
     * Finds the last sprint associated with a project based on its code
     * @param projectCode the project code
     * @return an Optional containing the last sprint, if found, or an empty optional, otherwise
     */

    public Optional<SprintDDD> findLastSprintByProjectCode(ProjectCode projectCode) {

        Optional<SprintJPA> sprintJpaOptional =
                sprintJpaRepository.
                        findTopBySprintID_ProjectCodeOrderBySprintID_SprintNumberDesc(projectCode.toString());
        if (sprintJpaOptional.isPresent()) {
            SprintJPA sprintJPA = sprintJpaOptional.get();
            SprintDDD lastSprint = sprintAssemblerData.toDomain(sprintJPA);
            return Optional.of(lastSprint);
        } else {
            return Optional.empty();
        }
    }

    public Optional<SprintDDD> findSprintBySprintID(SprintID sprintID) {
        //String projectCode = sprintID.getProjectCode().toString();
        //int sprintNumber = sprintID.getSprintNumber().getSprintNumber();
        SprintJpaID sprintJpaID = sprintAssemblerData.convertToSprintJpaID(sprintID);

        Optional<SprintJPA> sprintJPAOptional =
                sprintJpaRepository.findById(sprintJpaID);
        if (sprintJPAOptional.isPresent()) {
            SprintJPA sprintJPA = sprintJPAOptional.get();
            SprintDDD sprint = sprintAssemblerData.toDomain(sprintJPA);
            return Optional.of(sprint);
        } else {
            return Optional.empty();
        }
    }

    public Optional<UserStoryInSprint> getUserStoriesFromSprint(UserStoryInSprintID id) {
        UserStoryInSprintIDJpa jpaId = userStoryInSprintDataAssembler.convertToJpaId(id);
        Optional<UserStoryInSprintJPA> userStoryInSprintOptional =  userStoryInSprintJpaRepository.findById(jpaId);

        if (userStoryInSprintOptional.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(userStoryInSprintDataAssembler.toDomain(userStoryInSprintOptional.get()));
        }
    }
    /**
     * Method responsible for replacing a UserStoryInSprint with new changes in the repository.
     * @param usInSprint
     * @return UserStoryInSprint object
     */
    @Override
    public UserStoryInSprint replaceUsInSprint(UserStoryInSprint usInSprint) {
        UserStoryInSprintJPA userStoryInSprintJPA = userStoryInSprintDataAssembler.toData(usInSprint);
        UserStoryInSprintJPA savedUsInSprintJpa = userStoryInSprintJpaRepository.save(userStoryInSprintJPA);
        UserStoryInSprint savedUsInSprint = userStoryInSprintDataAssembler.toDomain(savedUsInSprintJpa);

        return savedUsInSprint;
    }
}
