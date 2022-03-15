package com.crossover.service.services;

import com.crossover.service.model.RequestContent;
import com.crossover.service.model.ResponseContent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyBackendServiceImplTest {
    private static MyBackendServiceImpl myBackendService;

    @BeforeEach
    void setup() {
        myBackendService = new MyBackendServiceImpl(3000L);
    }

    @Test
    void activityAddedTest() {
        myBackendService.persistActivityValue("test-key", new RequestContent(5));
        ResponseContent response = myBackendService.fetchActivityValue("test-key");
        Assertions.assertNotNull(response);
        Assertions.assertEquals(5, response.getValue());
    }

    @Test
    void activityRetrievedTest() throws InterruptedException {
        myBackendService.persistActivityValue("test-key", new RequestContent(5));
        Thread.sleep(3000L);
        myBackendService.persistActivityValue("test-key", new RequestContent(10));
        ResponseContent response = myBackendService.fetchActivityValue("test-key");
        Assertions.assertNotNull(response);
        Assertions.assertEquals(10, response.getValue());
    }
}
