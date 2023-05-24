package org.switch2022.project.repository;

import org.switch2022.project.datamodel.JPA.UserStoryJpa;
import org.switch2022.project.datamodel.JPA.assemblers.UserStoryDomainDataAssembler;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.UserStoryID;
import org.switch2022.project.repository.JPA.UserStoryJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class UserStoryRepository implements Repository<UserStoryID, UserStoryDDD> {

    private final UserStoryJpaRepository userStoryJpaRepository;

    private final UserStoryDomainDataAssembler userStoryDomainDataAssembler;


    public UserStoryRepository(UserStoryJpaRepository userStoryJpaRepository, UserStoryDomainDataAssembler userStoryDomainDataAssembler) {
        this.userStoryJpaRepository = userStoryJpaRepository;
        this.userStoryDomainDataAssembler = userStoryDomainDataAssembler;
    }

    /**
     * Checks if a UserStoryID exists in the repository.
     *
     * @param id the ID of the user story
     * @return true if user story with given ID exists, false otherwise
     */
    public boolean containsID(UserStoryID id) {
        return userStoryJpaRepository.existsById(id);
    }

    /**
     * Saves a UserStory if one with the same UserStoryID does not exist in the repository.
     *
     * @param userStory to be saved
     * @return true if operation is successful, otherwise return false.
     */
    public boolean save(UserStoryDDD userStory) {
        UserStoryID userStoryID = userStory.identity();

        if (!containsID(userStoryID)) {
            UserStoryJpa userStoryJpa = userStoryDomainDataAssembler.toData(userStory);
            UserStoryJpa savedUserStoryJpa = userStoryJpaRepository.save(userStoryJpa);
            UserStoryDDD savedUserStory = userStoryDomainDataAssembler.toDomain(savedUserStoryJpa);
            return true; // TODO: should return savedUserStory
        }
        return false;
    }

    /**
     * Creates an iterable with all user stories contained in the repository.
     *
     * @return Iterable of all UserStory in the repository
     */
    public Iterable<UserStoryDDD> findAll() {
        Iterable<UserStoryJpa> allUserStoriesJpa = userStoryJpaRepository.findAll();

        List<UserStoryDDD> allUserStories = new ArrayList<>();
        for (UserStoryJpa userStoryJpa : allUserStoriesJpa) {
            allUserStories.add(userStoryDomainDataAssembler.toDomain(userStoryJpa));
        }

        return allUserStories;
    }

    /**
     * Returns UserStory with specified UserStoryID if it is contained in the repository.
     *
     * @param id the UserStoryID to be found
     * @return Optional with UserStory if found, empty otherwise
     */
    public Optional<UserStoryDDD> getByID(UserStoryID id) {
        Optional<UserStoryJpa> userStoryJpaOptional = userStoryJpaRepository.findById(id);

        if (userStoryJpaOptional.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(userStoryDomainDataAssembler.toDomain(userStoryJpaOptional.get()));
        }
    }

    @Override
    public void clearRepository() {
        userStoryJpaRepository.deleteAll();
    }

}
