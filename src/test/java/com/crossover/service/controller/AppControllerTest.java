package com.crossover.service.controller;

import com.crossover.service.TestApplicationConfig;
import com.crossover.service.TestDataFactory;
import com.crossover.service.model.ResponseContent;
import com.crossover.service.services.MyBackendServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

@SpringBootTest(classes = TestApplicationConfig.class)
@AutoConfigureMockMvc
public class AppControllerTest {
    @Autowired
    private MockMvc mockMvc;
    /*@MockBean
    MyBackendServiceImpl myBackendService;*/

    @Test
    void postHappyPath() throws Exception {
        String json = TestDataFactory.readFileFromResources("/json/postJson.json");
        mockMvc.perform(
                MockMvcRequestBuilders.post("/activity/test-key")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void getHappyPath() throws Exception {
        /*Mockito.when(myBackendService.fetchActivityValue(ArgumentMatchers.anyString()))
                .thenReturn(new ResponseContent(5));*/
        String json = TestDataFactory.readFileFromResources("/json/getJson.json");
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/activity/test-key/total"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String jsonResult = result.getResponse().getContentAsString();
        Assertions.assertTrue(json.equals(jsonResult));
    }
}
