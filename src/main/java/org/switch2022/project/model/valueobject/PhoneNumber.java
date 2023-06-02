package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumber implements ValueObject, Serializable {

    private final String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank() || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null/blank/empty");
        }
        if (!ValidatePhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("This is not a valid phone number");

        }
        this.phoneNumber = phoneNumber;

    }

    private boolean ValidatePhoneNumber(String phoneNumber) {
        // Regular expression to validate the phone number.
        String regex = "^\\+351\\d{9}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneNumber)) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }
    @Override
    public String toString() {
        return phoneNumber;
    }
}
