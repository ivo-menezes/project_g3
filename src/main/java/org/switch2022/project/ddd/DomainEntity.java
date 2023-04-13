package org.switch2022.project.ddd;

public interface DomainEntity<ID extends  DomainId> {

    public ID identity();
}
