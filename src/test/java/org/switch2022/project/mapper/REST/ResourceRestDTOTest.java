package org.switch2022.project.mapper.REST;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ResourceRestDTOTest {

    @DisplayName("can create dto")
    @Test
    void createDTO() {
        ResourceRestDTO dto = new ResourceRestDTO();
        assertInstanceOf(ResourceRestDTO.class, dto);
    }
}