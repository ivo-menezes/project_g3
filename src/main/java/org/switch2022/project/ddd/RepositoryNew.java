package org.switch2022.project.ddd;

import java.util.Optional;

/**
 * Interface for generic operations on a repository.
 *
 * @param <ID> value object type used as ID of saved entities
 * @param <T>  type of saved aggregate root entities
 */
public interface RepositoryNew<ID extends DomainId, T extends AggregateRoot<ID>> {

    /**
     * Saves a given entity and returns the saved entity, as the save operation might have changed it.
     *
     * @param entity
     * @return the saved entity
     */
    T save(T entity);

    T replace(T entity);

    /**
     * Returns all saved entities.
     *
     * @return all saved entities
     */
    Iterable<T> findAll();

    /**
     * Retrieves an entity by its ID.
     *
     * @param id
     * @return Optional of the entity with the given ID, or empty if not found
     */
    Optional<T> getByID(ID id);

    /**
     * Checks whether an entity with the given ID exists.
     *
     * @param id
     * @return true if entity was found, false otherwise
     */
    boolean containsID(ID id);

    /**
     * Deletes all entities in the repository.
     */
    void clearRepository();
}
