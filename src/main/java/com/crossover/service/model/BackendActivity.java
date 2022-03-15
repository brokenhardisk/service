package com.crossover.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class BackendActivity {
    Integer value;
    Instant timeStamp;
}
