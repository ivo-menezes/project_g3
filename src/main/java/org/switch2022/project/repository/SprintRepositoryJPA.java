package org.switch2022.project.repository;

import org.switch2022.project.datamodel.JPA.SprintJPA;
import org.switch2022.project.datamodel.JPA.assemblers.SprintAssemblerData;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.repository.JPA.SprintJPARepository;
import org.switch2022.project.service.irepositories.ISprintRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class SprintRepositoryJPA implements ISprintRepository {

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

    public SprintRepositoryJPA(SprintJPARepository sprintJpaRepository, SprintAssemblerData sprintAssemblerData){
        this.sprintJpaRepository = sprintJpaRepository;
        this.sprintAssemblerData = sprintAssemblerData;
    }

    /***
     * This will check whether the Repository already has a sprint with the id we pass
     * into this method.
     * @param id As SprintID object
     * @return boolean value of whether it contains the ID or not
     */
    @Override
    public boolean containsID(SprintID id) {
        return sprintJpaRepository.existsById(id);
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
     * This method returns the collection of values of the hashmap,
     * by using the values() method, as an iterable object
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
        Optional<SprintJPA> sprintJPAOptional = sprintJpaRepository.findById(id);

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
}
