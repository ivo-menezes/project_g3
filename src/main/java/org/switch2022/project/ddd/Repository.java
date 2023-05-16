package org.switch2022.project.ddd;

import java.util.Optional;

public interface Repository <ID extends DomainId, T extends AggregateRoot<ID> > {

    public boolean save(T entity);

    public Iterable<T> findAll();

    public Optional<T> getByID(ID id);

    public boolean containsID(ID id);

    public void clearRepository();
}
