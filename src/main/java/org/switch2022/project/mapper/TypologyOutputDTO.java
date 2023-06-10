package org.switch2022.project.mapper;

public class TypologyOutputDTO {
    public Long typologyId;
    public String typologyDesignation;

    public TypologyOutputDTO(Long typologyId, String typologyDesignation) {
        this.typologyId = typologyId;
        this.typologyDesignation = typologyDesignation;
    }
}
