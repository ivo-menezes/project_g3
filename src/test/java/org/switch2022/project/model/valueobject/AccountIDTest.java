package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountIDTest {

    @Test
    @DisplayName("Ensure that objects with the same AccountID are equals.")
    public void checkIfObjectsWithTheSameAccountIDAreEquals() {
        AccountID accountID1 = new AccountID();
        AccountID accountID2 = accountID1;

        assertEquals(accountID1, accountID2);
    }

    @Test
    @DisplayName("Ensure that objects with the same AccountID are equals.")
    public void checkIfObjectsWithTheSameAccountIDAreNotEquals() {
        AccountID accountID1 = new AccountID();
        AccountID accountID2 = new AccountID();

        assertNotEquals(accountID1, accountID2);
    }

    @Test
    @DisplayName("Ensure that objects with the same AccountID have the same hash code.")
    public void checkIfObjectsWithTheSameAccountIDHaveTheSameHashCode() {
        AccountID accountID1 = new AccountID();
        AccountID accountID2 = accountID1;

        assertEquals(accountID1.hashCode(), accountID2.hashCode());
    }

    @Test
    @DisplayName("Ensure that objects with the same AccountID have the same hash code.")
    public void checkIfObjectsWithTheSameAccountIDHaveDifferentsHashCodes() {
        AccountID accountID1 = new AccountID();
        AccountID accountID2 = new AccountID();

        assertNotEquals(accountID1.hashCode(), accountID2.hashCode());
    }

    @Test
    @DisplayName("Ensure that equals returns false for different object types")
    public void shouldReturnFalseForDifferentObjectTypes() {
        AccountID accountID = new AccountID();
        Object otherObject = new Object();

        assertNotEquals(accountID,otherObject);
    }

    @Test
    @DisplayName("Ensure that AccountID is returned and its not equal to another AccountID")
    public void shouldReturnTheAccountID() {
        AccountID accountID = new AccountID();
        UUID uuid1 = UUID.randomUUID();

        assertNotEquals(uuid1, accountID.getAccountID());
    }
}