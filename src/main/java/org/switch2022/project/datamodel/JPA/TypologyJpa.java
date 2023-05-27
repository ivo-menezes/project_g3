package org.switch2022.project.datamodel.JPA;

import javax.persistence.*;

@Entity
@Table(name="typologies")
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
    public TypologyJpa(String typologyDesignation) {

        if (typologyDesignation != null && !typologyDesignation.isBlank() && !typologyDesignation.isEmpty()) {
            this.typologyDesignation = typologyDesignation;
        } else {
            throw new IllegalArgumentException("Typology designation must not be null");
        }
    }

    public Long getId() {
        return id;
    }

    public String getTypologyDesignation() {
        return typologyDesignation;
    }

}
