package org.switch2022.project.mapper;

import org.springframework.stereotype.Component;

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
}
