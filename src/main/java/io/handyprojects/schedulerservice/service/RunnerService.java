package io.handyprojects.schedulerservice.service;

import io.handyprojects.schedulerservice.domain.Plan;

public interface RunnerService {
    void run(Plan plan);
    void run(Long planId);
}
