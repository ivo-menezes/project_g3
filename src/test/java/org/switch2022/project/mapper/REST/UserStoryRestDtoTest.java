package org.switch2022.project.mapper.REST;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryRestDtoTest {
    @Test
    void createDto() {
        UserStoryRestDto dto = new UserStoryRestDto();
        assertInstanceOf(UserStoryRestDto.class, dto);
    }
}