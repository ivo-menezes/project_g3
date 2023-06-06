package org.switch2022.project.mapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewUserStoryInfoDTOTest {

    @Test
    void canCreateDto() {
        NewUserStoryInfoDTO dto = new NewUserStoryInfoDTO();
        assertInstanceOf(NewUserStoryInfoDTO.class, dto);
    }
}