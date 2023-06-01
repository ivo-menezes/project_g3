package org.switch2022.project.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.switch2022.project.mapper.TypologyDTO;
import org.switch2022.project.model.typology.ITypologyFactory;
import org.switch2022.project.model.typology.TypologyDDD;
import org.switch2022.project.model.valueobject.TypologyDesignation;
import org.switch2022.project.model.valueobject.TypologyID;
import org.switch2022.project.service.irepositories.ITypologyRepository;

import java.util.ArrayList;

@Service
public class TypologyService {

    @Autowired
    private ITypologyFactory typologyFactory;

    @Autowired
    private final ITypologyRepository typologyRepository;

    /**
     * Creates a Service
     * @param typologyFactory injected parameter
     * @param typologyRepository injected parameter
     */
    public TypologyService(ITypologyFactory typologyFactory, ITypologyRepository typologyRepository) {

        if (typologyFactory == null) {
            throw new IllegalArgumentException("TypologyFactory must not be null");
        }
        if (typologyRepository == null) {
            throw new IllegalArgumentException("TypologyRepository must not be null");
        }

        this.typologyFactory = typologyFactory;
        this.typologyRepository = typologyRepository;
    }

    /**
     * Creates typology from data provided by the DTO
     * @param typologyDTO containing the necessary data
     * @return the updated TypologyDTO with the saved typology id
     */
    public TypologyDTO createTypology(TypologyDTO typologyDTO){
        //creating the value objects from the DTO
        TypologyID typologyID = typologyDTO.typologyID;
        TypologyDesignation typologyDesignation = typologyDTO.typologyDesignation;
        //creating the Typology
        TypologyDDD typology = typologyFactory.createTypology(typologyID, typologyDesignation);

        TypologyDDD savedTypology = typologyRepository.save(typology);

        //updating the typology id
        typologyDTO.typologyID = savedTypology.identity();

        return typologyDTO;
    }

    /**
     * Method responsible for return all Typologies.
     * @return ArrayList<TypologyDTO>.
     */
    public ArrayList<TypologyDTO> getAll() {
        ArrayList<TypologyDTO> Typologies = new ArrayList();
        ArrayList<TypologyDDD> typologiesDDD = typologyRepository.getAll();

        for (TypologyDDD typologyDDD : typologiesDDD) {

            TypologyDTO typologyDTO = new TypologyDTO();
            typologyDTO.typologyID = typologyDDD.identity();
            typologyDTO.typologyDesignation = typologyDDD.getTypologyDesignation();

            Typologies.add(typologyDTO);
        }

        return Typologies;
    }
}
