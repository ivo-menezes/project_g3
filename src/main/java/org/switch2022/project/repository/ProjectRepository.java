package org.switch2022.project.repository;

import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.valueobject.ProjectCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class ProjectRepository implements Repository<ProjectCode, ProjectDDD> {

    private static final Map<ProjectCode, ProjectDDD> DATA = new HashMap<>();

    @Override
    public boolean save(ProjectDDD project) {
        ProjectCode projectCode = project.identity();

        if (!containsID(projectCode)) {
            DATA.put(projectCode, project);
            return true;
        }
        return false;
    }

    @Override
    public Iterable<ProjectDDD> findAll() {
        return DATA.values();
    }

    @Override
    public Optional<ProjectDDD> getByID(ProjectCode id) {
        if (!containsID(id)) {
            return Optional.empty();
        } else {
            return Optional.of(DATA.get(id));
        }
    }

    @Override
    public boolean containsID(ProjectCode id) {
        return DATA.containsKey(id);
    }
}
