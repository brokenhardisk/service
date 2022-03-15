package com.crossover.service;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@UtilityClass
public class TestDataFactory {

    public String readFileFromResources(String path){
        try {
            return IOUtils.resourceToString(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found");
        }
    }
}
