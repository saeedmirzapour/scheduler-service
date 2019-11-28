package io.handyprojects.schedulerservice.controller.job;

import io.handyprojects.schedulerservice.domain.Job;
import io.handyprojects.schedulerservice.service.PlanManagementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetPageableJobsController {

    private final PlanManagementService planManagementService;

    public GetPageableJobsController(PlanManagementService planManagementService) {
        this.planManagementService = planManagementService;
    }

    @GetMapping("/api/jobs")
    public Page<Job> handle(Pageable pageable) {
        return planManagementService.getPageableJobs(pageable);
    }
}