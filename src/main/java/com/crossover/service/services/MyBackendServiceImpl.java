package com.crossover.service.services;

import com.crossover.service.model.BackendActivity;
import com.crossover.service.model.RequestContent;
import com.crossover.service.model.ResponseContent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class MyBackendServiceImpl implements IMyBackendService {
    private final long durationInMillis;
    Map<String, List<BackendActivity>> activityObjectsMap = new HashMap<>();

    public void persistActivityValue(String activityKey, RequestContent content) {
        List<BackendActivity> activityObjectsList = activityObjectsMap.getOrDefault(activityKey, new ArrayList<>());
        activityObjectsList.add(new BackendActivity(content.getValue(), Instant.now()));
        activityObjectsMap.put(activityKey,activityObjectsList);
        log.info("Activity Value Persisted");
    }

    public ResponseContent fetchActivityValue(String activityKey) {
        int total;
        Instant currTime = Instant.now();

        List<BackendActivity> activityObjectsList = activityObjectsMap.getOrDefault(activityKey, new ArrayList<>());
        List<BackendActivity> updatedList = activityObjectsList.stream().filter(item ->
                Duration.between(item.getTimeStamp(), currTime).compareTo(Duration.ofMillis(durationInMillis)) <= 0)
                .collect(Collectors.toList());
        total = updatedList.stream().mapToInt(BackendActivity::getValue).sum();
        activityObjectsMap.put(activityKey,updatedList);
        log.info("Activity Values Updated");
        return new ResponseContent(total);
    }
}
