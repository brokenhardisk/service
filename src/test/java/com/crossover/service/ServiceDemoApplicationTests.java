package com.crossover.service;

import com.crossover.service.services.IMyBackendService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestApplicationConfig.class)
class ServiceDemoApplicationTests {

	@Autowired
	IMyBackendService myBackendService;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(myBackendService);
	}

}
