package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountIDTest {
    
    @Test
    @DisplayName("Ensure that id is returned")
    void ensureIDIsReturned() {
        long expectedId = 1;
        AccountID accountID = new AccountID(expectedId);

        long actualId = accountID.getId();
        assertEquals(expectedId, actualId);
    }

}