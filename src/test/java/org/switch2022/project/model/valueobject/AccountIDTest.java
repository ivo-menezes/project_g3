package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountIDTest {

    UUID uuid1 = UUID.randomUUID();
    UUID uuid2 = UUID.randomUUID();

    @Test
    @DisplayName("Ensure that objects with the same AccountID are equals.")
    public void checkIfObjectsWithTheSameAccountIDAreEquals() {
        AccountID accountID1 = new AccountID(uuid1);
        AccountID accountID2 = new AccountID(uuid1);

        assertEquals(accountID1, accountID2);
    }

    @Test
    @DisplayName("Ensure that objects with the same AccountID are equals.")
    public void checkIfObjectsWithTheSameAccountIDAreNotEquals() {
        AccountID accountID1 = new AccountID(uuid1);
        AccountID accountID2 = new AccountID(uuid2);

        assertNotEquals(accountID1, accountID2);
    }

    @Test
    @DisplayName("Ensure that objects with the same AccountID have the same hash code.")
    public void checkIfObjectsWithTheSameAccountIDHaveTheSameHashCode() {
        AccountID accountID1 = new AccountID(uuid1);
        AccountID accountID2 = new AccountID(uuid1);

        assertEquals(accountID1.hashCode(), accountID2.hashCode());
    }

    @Test
    @DisplayName("Ensure that objects with the same AccountID have the same hash code.")
    public void checkIfObjectsWithTheSameAccountIDHaveDifferentsHashCodes() {
        AccountID accountID1 = new AccountID(uuid1);
        AccountID accountID2 = new AccountID(uuid2);

        assertNotEquals(accountID1.hashCode(), accountID2.hashCode());
    }

    @Test
    @DisplayName("Ensure that equals returns false for different object types")
    public void shouldReturnFalseForDifferentObjectTypes() {
        AccountID accountID = new AccountID(uuid1);
        Object otherObject = new Object();

        assertNotEquals(accountID,otherObject);
    }

    @Test
    @DisplayName("Ensure that AccountID is returned")
    public void shouldReturnTheAccountID() {
        AccountID accountID = new AccountID(uuid1);

        assertEquals(uuid1, accountID.getAccountID());
    }

}