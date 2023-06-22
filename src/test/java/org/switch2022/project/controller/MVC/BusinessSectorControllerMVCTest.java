package org.switch2022.project.controller.MVC;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
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
import org.switch2022.project.mapper.BusinessSectorDTO;
import org.switch2022.project.model.valueobject.BusinessSectorDesignation;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
class BusinessSectorControllerMVCTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * The class BusinessSectorDesignation does not have a serializer
     * This serializer converts the BusinessSectorDesignation object into a string representation by using the toString()
     * You can check <a href="https://www.baeldung.com/jackson-custom-serialization">...</a>
     */
    public static class ItemSerializer extends StdSerializer<BusinessSectorDTO> {

        public ItemSerializer() {
            this(null);
        }

        public ItemSerializer(Class<BusinessSectorDTO> t) {
            super(t);
        }

        @Override
        public void serialize(
                BusinessSectorDTO businessSectorDTO, JsonGenerator jsonGenerator, SerializerProvider provider)
                throws IOException {

            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("businessSectorDesignation", businessSectorDTO.businessSectorDesignation.toString());
            jsonGenerator.writeEndObject();
        }
    }

    @Test
    void returnBusinessSectorAndCreated() throws Exception {
        String businessSectorDesignation = "Information services";
        BusinessSectorDesignation businessSectorDesignationVO = new BusinessSectorDesignation(businessSectorDesignation);
        BusinessSectorDTO dto = new BusinessSectorDTO();
        dto.businessSectorDesignation = businessSectorDesignationVO;

        //register the serializer method with the ObjectMapper instance injected
        SimpleModule module = new SimpleModule();
        module.addSerializer(BusinessSectorDTO.class, new BusinessSectorControllerMVCTest.ItemSerializer());
        objectMapper.registerModule(module);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/businessSectors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
    }

    @Test
    void returnBadRequest() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/businessSectors")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertEquals("",resultContent);
    }

    @Test
    void returnBusinessSectorsAndOK() throws Exception{

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/businessSectors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        System.out.println(resultContent);
    }
}