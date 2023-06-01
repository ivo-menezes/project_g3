package org.switch2022.project.mapper;

import org.switch2022.project.model.valueobject.TypologyID;
import org.switch2022.project.model.valueobject.TypologyDesignation;

import java.util.Objects;


public class TypologyDTO {

    public TypologyID typologyID;
    public TypologyDesignation typologyDesignation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypologyDTO)) return false;
        TypologyDTO that = (TypologyDTO) o;
        return Objects.equals(typologyID, that.typologyID) && Objects.equals(typologyDesignation, that.typologyDesignation);
    }
}
