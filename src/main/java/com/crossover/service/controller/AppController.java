package com.crossover.service.controller;

import com.crossover.service.model.RequestContent;
import com.crossover.service.model.ResponseContent;
import com.crossover.service.services.IMyBackendService;
import com.crossover.service.services.MyBackendServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Slf4j
public class AppController {

    @Autowired
    IMyBackendService backendService;

    @PostMapping(value = "/activity/{key}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postActivityValue(@PathVariable("key") String activityKey,
                                                    @RequestBody RequestContent content) throws IOException {
        log.info("ActivityKey: {} ,Content: {}: ", activityKey, content);
        backendService.persistActivityValue(activityKey,content);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping(path = "/activity/{key}/total",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContent> getActivityValue(@PathVariable("key") String activityKey){
        log.info("ActivityKey: {}", activityKey);
        return ResponseEntity.ok(backendService.fetchActivityValue(activityKey));
    }

}
