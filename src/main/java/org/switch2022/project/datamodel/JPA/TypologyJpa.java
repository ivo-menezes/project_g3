package org.switch2022.project.datamodel.JPA;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "typologies")
public class TypologyJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typologyDesignation;

    /**
     * Default constructor used by the persistence framework
     */
    protected TypologyJpa() {
    }

    /**
     * Constructor to instantiate a TypologyJpa
     *
     * @param typologyDesignation of the typology
     */
    public TypologyJpa(@NonNull String typologyDesignation) {

        this.typologyDesignation = typologyDesignation;
    }

    public Long getId() {
        return id;
    }

    public String getTypologyDesignation() {
        return typologyDesignation;
    }

}
