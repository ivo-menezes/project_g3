package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.DomainId;
import org.switch2022.project.ddd.ValueObject;

import java.util.Objects;
import java.util.UUID;

public class AccountID implements DomainId {

    final UUID accountID = UUID.randomUUID();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountID)) return false;
        AccountID accountID1 = (AccountID) o;
        return accountID.equals(accountID1.accountID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountID);
    }

    public UUID getAccountID() {
        return this.accountID;
    }
}
