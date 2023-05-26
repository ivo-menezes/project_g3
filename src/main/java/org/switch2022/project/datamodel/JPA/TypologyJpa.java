package org.switch2022.project.datamodel.JPA;

import javax.persistence.*;
import java.util.Objects;

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

    public String getTypologyDesignation() {
        return typologyDesignation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypologyJpa that = (TypologyJpa) o;
        return typologyDesignation.equals(that.typologyDesignation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typologyDesignation);
    }
}
