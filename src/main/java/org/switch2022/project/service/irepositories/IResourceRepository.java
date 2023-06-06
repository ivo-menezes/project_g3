package org.switch2022.project.service.irepositories;

import org.switch2022.project.model.resource.ResourceDDD;

import java.util.ArrayList;

public interface IResourceRepository  {
    ResourceDDD save(ResourceDDD resourceDDD);

    ArrayList<ResourceDDD> getAll();


}
