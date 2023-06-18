package org.switch2022.project.repository;

import org.switch2022.project.datamodel.JPA.UserStoryJpa;
import org.switch2022.project.datamodel.JPA.UserStoryJpaId;
import org.switch2022.project.datamodel.JPA.assemblers.UserStoryDomainDataAssembler;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.UserStoryID;
import org.switch2022.project.repository.JPA.UserStoryJpaRepository;
import org.switch2022.project.service.irepositories.IUserStoryRepository;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class UserStoryRepository implements IUserStoryRepository {

    private final UserStoryJpaRepository userStoryJpaRepository;

    private final UserStoryDomainDataAssembler userStoryDomainDataAssembler;


    public UserStoryRepository(UserStoryJpaRepository userStoryJpaRepository,
                               UserStoryDomainDataAssembler userStoryDomainDataAssembler) {
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
        UserStoryJpaId jpaId = userStoryDomainDataAssembler.convertToJpaId(id);
        return userStoryJpaRepository.existsById(jpaId);
    }

    /**
     * Saves a UserStory if one with the same UserStoryID does not exist in the repository.
     *
     * @param userStory to be saved
     * @return saved UserStory entity
     */
    public UserStoryDDD save(UserStoryDDD userStory) {
        UserStoryID userStoryID = userStory.identity();

        if (containsID(userStoryID)) {
            throw new KeyAlreadyExistsException("UserStoryID already exists");
        }

        UserStoryJpa userStoryJpa = userStoryDomainDataAssembler.toData(userStory);
        UserStoryJpa savedUserStoryJpa = userStoryJpaRepository.save(userStoryJpa);
        return userStoryDomainDataAssembler.toDomain(savedUserStoryJpa);
    }

    public UserStoryDDD replace(UserStoryDDD userStory) {
        UserStoryJpa userStoryJpa = userStoryDomainDataAssembler.toData(userStory);
        UserStoryJpa savedUserStoryJpa = userStoryJpaRepository.save(userStoryJpa);
        return userStoryDomainDataAssembler.toDomain(savedUserStoryJpa);
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
        UserStoryJpaId jpaId = userStoryDomainDataAssembler.convertToJpaId(id);
        Optional<UserStoryJpa> userStoryJpaOptional = userStoryJpaRepository.findById(jpaId);

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
