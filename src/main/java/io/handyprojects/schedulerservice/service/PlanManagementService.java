package io.handyprojects.schedulerservice.service;

import io.handyprojects.schedulerservice.domain.Job;
import io.handyprojects.schedulerservice.domain.Plan;
import io.handyprojects.schedulerservice.repository.specification.PlanSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlanManagementService {
    Plan createPlan(String name, String cronExpression, Boolean active);
    void removePlan(Long planId);
    Plan editPlan(Long planId, String name, String cronExpression);
    Plan activatePlan(Long planId);
    Plan deactivatePlan(Long planId);
    Plan getPlan(Long planId);
    Page<Plan> getPageablePlan(PlanSpecification planSpecification, Pageable pageable);
    Job activateJob(Long jobId);
    Job createJob(Long planId, String curlCommand, int order, boolean active);
    Job deactivateJob(Long jobId);
    Job editJob(Long jobId, String curlCommand, int order);
    Job getJob(Long jobId);
    Page<Job> getPageableJobs(Pageable pageable);
    void removeJob(Long jobId);
    List<Plan> getAllWithJobs();
    void runPlan(Long planId);
}
