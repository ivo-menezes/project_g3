package org.switch2022.project.controller.MVC;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
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
import org.switch2022.project.mapper.CustomerDTO;
import org.switch2022.project.model.valueobject.CustomerDesignation;
import org.switch2022.project.model.valueobject.CustomerNIF;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
class CustomerControllerMVCTest {
   @Autowired
   MockMvc mockMvc;
   @Autowired
   ObjectMapper objectMapper;

   @BeforeEach
   public void setUp() {
      MockitoAnnotations.openMocks(this);
   }

   /**
    * The class CustomerNIF does not have a serializer
    * This serializer converts the CustomerNIF object into a string representation by using the toString()
    */
   public static class CustomerNIFSerializer extends JsonSerializer<CustomerNIF> {

       @Override
       public void serialize(CustomerNIF customerNIF, JsonGenerator jsonGenerator, SerializerProvider serializer)
               throws IOException {
           jsonGenerator.writeString(customerNIF.toString());
       }
   }

    /**
     * The class CustomerNIF does not have a serializer
     * This serializer converts the CustomerNIF object into a string representation by using the toString()
     */
    public static class CustomerDesignationSerializer extends JsonSerializer<CustomerDesignation> {

        @Override
        public void serialize(CustomerDesignation customerDesignation, JsonGenerator jsonGenerator, SerializerProvider serializer)
                throws IOException {
            jsonGenerator.writeString(customerDesignation.toString());
        }
    }

   @Test
   void returnAccountAndCreated() throws Exception {

       String customerNIF = "225041898";
       CustomerNIF customerNIFVO = new CustomerNIF(customerNIF);
       String customerDesignation = "Zé";
       CustomerDesignation customerDesignationVO = new CustomerDesignation(customerDesignation);
       CustomerDTO dto = new CustomerDTO();
       dto. customerNIF = customerNIFVO;
       dto.customerDesignation = customerDesignationVO;

       //register the serializer method with the ObjectMapper instance injected
       objectMapper.registerModule(new SimpleModule().addSerializer(CustomerNIF.class,
           new CustomerControllerMVCTest.CustomerNIFSerializer()));
       objectMapper.registerModule(new SimpleModule().addSerializer(CustomerDesignation.class,
               new CustomerControllerMVCTest.CustomerDesignationSerializer()));

       MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/customers")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(dto))
               .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

       String resultContent = result.getResponse().getContentAsString();
       assertNotNull(resultContent);
   }

   @Test
   void returnBadRequest() throws Exception{
       MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/customers")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest()).andReturn();

       String resultContent = result.getResponse().getContentAsString();
       assertNotNull(resultContent);
       assertEquals("",resultContent);
   }

    @Test
    void returnBusinessSectorsAndOK() throws Exception{

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk()).andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        System.out.println(resultContent);
    }
}