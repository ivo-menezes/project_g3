package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.DomainId;
import org.switch2022.project.ddd.ValueObject;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email implements ValueObject, Serializable {

    private final String email;

    public Email(String email) {
        if (email == null || email.isEmpty() || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null/empty/blank");
        }
        if (!ValidateEmail(email)) {
            throw new IllegalArgumentException("This is not a valid Email");
        }

        this.email = email;
    }

    private boolean ValidateEmail(String email) {
        // Regular expression to validate the phone number.
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;
        Email that = (Email) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }


}
