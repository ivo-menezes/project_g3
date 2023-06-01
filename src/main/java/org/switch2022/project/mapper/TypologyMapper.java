package org.switch2022.project.mapper;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class TypologyMapper {
    /**
     * Method responsible for converting DTO in a DTO with primitive properties.
     * @param typology
     * @return TypologyOutputDTO object.
     */
    public TypologyOutputDTO toOutputDTO(TypologyDTO typology) {
        if (typology.typologyID != null)
            return new TypologyOutputDTO(typology.typologyID.getId(), typology.typologyDesignation.toString());
        else
            return new TypologyOutputDTO(null, typology.typologyDesignation.toString());
    }

    /**
     * Method responsible for converting ArrayList of DTO in a ArrayList of DTO with primitive properties.
     * @return TypologyOutputDTO object.
     */
    public ArrayList<TypologyOutputDTO> toOutputDTO(ArrayList<TypologyDTO> Typologies) {
        ArrayList<TypologyOutputDTO> TypologiesOutput = new ArrayList();

        for (TypologyDTO typologyDTO : Typologies) {

            TypologyOutputDTO typologyOutputDTO = new TypologyOutputDTO(typologyDTO.typologyID.getId(), typologyDTO.typologyDesignation.toString());

            TypologiesOutput.add(typologyOutputDTO);
        }

        return TypologiesOutput;
    }
}
