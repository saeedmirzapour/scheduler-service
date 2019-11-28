package io.handyprojects.schedulerservice.service;

import io.handyprojects.schedulerservice.domain.Job;
import io.handyprojects.schedulerservice.domain.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlanManagementService {
    Plan createPlan(String name, String cronExpression, Boolean active);
    void removePlan(Long planId);
    Plan editPlan(Long planId, String name, String cronExpression);
    void activatePlan(Long planId);
    void deactivatePlan(Long planId);
    Plan getPlan(Long planId);
    Page<Plan> getPageablePlan(Pageable pageable);
    void activateJob(Long jobId);
    Job createJob(Long planId, String curlCommand, int order, boolean active);
    void deactivateJob(Long jobId);
    Job editJob(Long jobId, String curlCommand, int order);
    Job getJob(Long jobId);
    Page<Job> getPageableJobs(Pageable pageable);
    void removeJob(Long jobId);
}
