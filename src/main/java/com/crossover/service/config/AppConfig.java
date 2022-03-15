package com.crossover.service.config;

import com.crossover.service.services.IMyBackendService;
import com.crossover.service.services.MyBackendServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${data.archive.duration}")
    private long durationInMillis;

    @Bean
    public IMyBackendService myBackendService(){
        return new MyBackendServiceImpl(durationInMillis);
    }
}
