package io.handyprojects.schedulerservice.service;

import io.handyprojects.schedulerservice.domain.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlanService {
    Plan create(String name, String cronExpression, Boolean active);
    void remove(Long planId);
    Plan edit(Long planId, String name, String cronExpression);
    void activate(Long planId);
    void deactivate(Long planId);
    Plan get(Long planId);
    Page<Plan> getPageable(Pageable pageable);
}
