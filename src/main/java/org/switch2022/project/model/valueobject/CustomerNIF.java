package org.switch2022.project.model.valueobject;
import org.switch2022.project.ddd.ValueObject;
import java.util.Objects;

public class CustomerNIF implements ValueObject {

    private String customerNIF;

    /**
     * Constructor for customerNIF.
     * @param customerNIF is the customer's Tax ID Number.
     *
     */
    public CustomerNIF(String customerNIF) {
        if (customerNIF ==null || customerNIF.isBlank() || customerNIF.isEmpty()) {
            throw new IllegalArgumentException("customerNIF cannot be null/blank/empty");
        }

        if (!isValidPortugal(customerNIF) &&
                !isValidSpain(customerNIF) &&
                !isValidOtherCountries(customerNIF)) {
            throw new IllegalArgumentException("customerNIF is not valid");
        }

        this.customerNIF = customerNIF;
    }

    /**
     * Method to check if the NIF is valid in Portugal.
     * @param nif
     * @return If the NIF is valid it returns true, otherwise false.
     */
    private boolean isValidPortugal(String nif) {

        final int max = 9;

        //check if is numeric and has 9 numbers
        if (!nif.matches("[0-9]+") || nif.length() != max) {
            return false;
        }

        int checkSum = 0;
        //calculate checkSum
        for (int i = 0; i < max - 1; i++) {
            checkSum += (nif.charAt(i) - '0') * (max - i);
        }
        int checkDigit = 11 - (checkSum % 11);

        //if checkDigit is higher than 9 set it to zero
        if (checkDigit > 9) checkDigit = 0; {

            //compare checkDigit with the last number of NIF
            return checkDigit == nif.charAt(max - 1) - '0';
        }
    }

    /**
     * Method to check if the NIF is valid in Spain.
     * @param nif
     * @return If the NIF is valid it returns true, otherwise false.
     */
    private boolean isValidSpain(String nif) {

        //If the NIF length is different from 9, the method ends.
        if (nif.length() != 9) {
            return false;
        }

        String sequenceLettersNIF = "TRWAGMYFPDXBNJZSQVHLCKE";
        nif = nif.toUpperCase();

        //Initial position: 0 (first in the text string).
        //Length: text string minus last position. So we only get the number.
        String numberNIF = nif.substring(0, nif.length() - 1);

        //If it is a NIE, we replace the initial letter with its numerical value.
        numberNIF = numberNIF.replace("X", "0").replace("Y", "1").replace("Z", "2");

        //We obtain the letter with a char that will also serve us for the index of the sequenceLettersNIF
        char letterNIF = nif.charAt(8);
        int i = Integer.parseInt(numberNIF) % 23;
        return letterNIF == sequenceLettersNIF.charAt(i);
    }

    /**
     * Method to check if the identification number is valid in other countries.
     * @param number
     * @return If the identification number is valid it returns true, otherwise false.
     */
    private boolean isValidOtherCountries(String number) {

        //validated only in length (9 digits)
        final int max = 9;
        return (number.matches("[0-9]+") && number.length() == max);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomerNIF)) {
            return false;
        }
        CustomerNIF that = (CustomerNIF) o;
        return customerNIF.equals(that.customerNIF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerNIF);
    }

    @Override
    public String toString() {
        return this.customerNIF;
    }
}