package org.switch2022.project.controller.MVC;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.switch2022.project.mapper.REST.AccountRestDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
class AccountControllerMVCTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void returnAccountAndCreated() throws Exception {
        AccountRestDTO dto = new AccountRestDTO();
        dto.email = "dds44d@gmail.com";
        dto.name = "Ricardo";
        dto.phoneNumber = "+351917830464";
        dto.photo = "";
        dto.profile = "User";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
    }

    @Test
    void returnBadRequest() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertEquals("",resultContent);
    }
}