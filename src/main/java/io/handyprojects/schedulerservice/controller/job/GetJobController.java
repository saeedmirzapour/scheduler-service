package io.handyprojects.schedulerservice.controller.job;

import io.handyprojects.schedulerservice.domain.Job;
import io.handyprojects.schedulerservice.service.PlanManagementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetJobController {

    private final PlanManagementService planManagementService;

    public GetJobController(PlanManagementService planManagementService) {
        this.planManagementService = planManagementService;
    }

    @GetMapping("/api/jobs/{id}")
    public Job handle(@PathVariable Long id) {
        return planManagementService.getJob(id);
    }
}
