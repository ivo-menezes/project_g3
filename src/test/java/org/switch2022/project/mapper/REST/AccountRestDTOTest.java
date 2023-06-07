package org.switch2022.project.mapper.REST;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountRestDTOTest {

    @DisplayName("can create dto")
    @Test
    void createDTO() {
        AccountRestDTO dto = new AccountRestDTO();
        assertInstanceOf(AccountRestDTO.class, dto);
    }

}