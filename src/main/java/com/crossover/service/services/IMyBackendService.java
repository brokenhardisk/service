package com.crossover.service.services;

import com.crossover.service.model.RequestContent;
import com.crossover.service.model.ResponseContent;

public interface IMyBackendService {
    void persistActivityValue(String activityKey, RequestContent content);

    ResponseContent fetchActivityValue(String activityKey);
}
