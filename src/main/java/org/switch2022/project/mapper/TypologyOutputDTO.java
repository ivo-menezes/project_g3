package org.switch2022.project.mapper;

import java.util.Objects;

public class TypologyOutputDTO {
    public Long typologyId;
    public String typologyDesignation;

    public TypologyOutputDTO(Long typologyId, String typologyDesignation) {
        this.typologyId = typologyId;
        this.typologyDesignation = typologyDesignation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypologyOutputDTO)) return false;
        TypologyOutputDTO that = (TypologyOutputDTO) o;
        return Objects.equals(typologyId, that.typologyId) && Objects.equals(typologyDesignation, that.typologyDesignation);
    }
}
